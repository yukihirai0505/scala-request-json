package com.yukihirai0505.com.scala

import com.ning.http.client.cookie.Cookie
import com.yukihirai0505.com.scala.model.Response
import dispatch.Defaults._
import dispatch._
import play.api.libs.json._

object Request {

  /**
    * Get Base request with cookies
    *
    * @param requestUrl
    * @param cookies
    */
  def getBaseReq(requestUrl: String, cookies: List[Cookie] = List.empty[Cookie]): Req = {
    addCookies(cookies, url(requestUrl))
  }

  /**
    * Send the prepared request to an URL and parse the response to an appropriate case class.
    *
    * @param request Req, the dispatch request ready to by sent
    * @tparam T represent the type of the facebook data requested
    * @return a Future of Response[T]
    */
  def sendRequestJson[T](request: Req, charset: String = "UTF-8")(implicit r: Reads[T]): Future[Response[T]] = {
    Http(request).map { resp =>
      val response = resp.getResponseBody(charset)
      try {
        if (resp.getStatusCode == 500) throw new Exception(response)
        Json.parse(response).validate[T] match {
          case JsError(e) =>
            val errorMessage = s"----ErrorMessage: ${e.toString}\n----url: ${request.url}\n----Response: $response\n"
            throw new Exception(errorMessage)
          case JsSuccess(value, _) => value match {
            case None => Response[T](None, resp)
            case _ => Response[T](Some(value), resp)
          }
        }
      } catch {
        case e: Exception =>
          val errorMessage = s"----ErrorMessage: ${e.getMessage}\n----url: ${request.url}\n----Response: $response\n"
          throw new Exception(errorMessage)
      }
    }
  }

  /**
    * Add cookies to Request
    *
    * @param cookies
    * @param req
    * @return
    */
  private def addCookies(cookies: List[Cookie], req: Req): Req = {
    if (cookies.isEmpty) req
    else addCookies(cookies.tail, req.addCookie(cookies.head))
  }

}