package com.codesimples.simple.restapi.validate

import com.codesimples.simple.restapi.BasicMessage

object ValidateMessage extends BasicMessage {

  override def endpoint() = "/api/validate"

  def happyDay(typeSymulation:String) = {
    "{"+
      "\"request_header\": {"+
        "\"timestamp\": "+timestamp+","+
        "\"key\": \""+publicKeyBase64()+"\""+
      "},"+
      "\"request_body\": {"+
        "\"timestamp\": "+timestamp+","+
        "\"type_simulation\": \""+typeSymulation+"\""+
      "}"+
    "}"
  }
}
