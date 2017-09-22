package com.yukihirai0505.com.scala

import play.api.libs.json._

import com.ning.http.client.cookie.Cookie
import com.yukihirai0505.com.scala.model.Response
import dispatch.Defaults._
import dispatch._

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
  def sendRequestJson[T](request: Req)(implicit r: Reads[T]): Future[Response[T]] = {
    Http(request).map { resp =>
      val response = resp.getResponseBody
      if (resp.getStatusCode != 200) throw new Exception(response.toString)
      Json.parse(response).validate[T] match {
        case JsError(e) => throw new Exception(e.toString())
        case JsSuccess(value, _) => value match {
          case None => Response[T](None, resp)
          case _ => Response[T](Some(value), resp)
        }
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