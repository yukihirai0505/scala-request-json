package com.yukihirai0505.com.scala.model

/**
  * author Yuki Hirai on 2017/06/08.
  */
case class Response[T](data: Option[T], response: com.ning.http.client.Response)