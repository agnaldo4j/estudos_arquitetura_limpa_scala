package com.sizebay.algorithm.restapi.validate

object ValidateSuccessCommand {
  def execute(): Map[String,Any] = {
    Map[String,Any](
      "response_header" -> Map[String,Any](
        "timestamp" ->1390480115,
        "code"-> 200,
        "message"-> "OK"
      ),
      "response_body"-> Map[String,Any](
        "timestamp" ->1390480115
      )
    )
  }
}
