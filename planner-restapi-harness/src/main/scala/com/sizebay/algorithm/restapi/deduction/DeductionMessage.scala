package com.sizebay.algorithm.restapi.deduction

import com.sizebay.algorithm.restapi.BasicMessage

object DeductionMessage extends BasicMessage {

  override def endpoint() = "/api/deductionSize"

  def happyDay(gender: String, height: Int, weight: Int, age: Int, shapeChest: Int, shapeWaist: Int, shapeHip: Int) = {
    "{"+
      "\"request_header\": {"+
        "\"timestamp\": "+timestamp+","+
        "\"key\": \""+publicKeyBase64()+"\""+
      "},"+
      "\"request_body\": {"+
        "\"gender\": \""+gender+"\","+
        "\"height\": "+height+","+
        "\"weight\": "+weight+","+
        "\"age\": "+age+","+
        "\"shapeChest\": "+shapeChest+","+
        "\"shapeWaist\": "+shapeWaist+","+
        "\"shapeHip\": "+shapeHip+
      "}"+
    "}"
  }
}