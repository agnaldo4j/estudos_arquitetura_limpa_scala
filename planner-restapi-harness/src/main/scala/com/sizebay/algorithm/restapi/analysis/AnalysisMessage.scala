package com.sizebay.algorithm.restapi.analysis

import com.sizebay.algorithm.restapi.BasicMessage

object AnalysisMessage extends BasicMessage {

  override def endpoint() = "/api/analysisRegularSize"

  def happyDay(userId: String, userProfileId:String, productId:String) = {
    "{"+
      "\"request_header\": {"+
        "\"timestamp\": "+timestamp+","+
        "\"key\": \""+publicKeyBase64()+"\""+
      "},"+
      "\"request_body\": {"+
        "\"user_id\": "+userId+","+
        "\"user_profile_id\": "+userProfileId+","+
        "\"product_id\": "+productId+
      "}"+
    "}"
  }
}