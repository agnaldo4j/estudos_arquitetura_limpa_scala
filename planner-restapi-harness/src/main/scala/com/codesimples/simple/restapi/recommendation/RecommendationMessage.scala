package com.codesimples.simple.restapi.recommendation

import com.codesimples.simple.restapi.BasicMessage

object RecommendationMessage extends BasicMessage {

  override def endpoint() = "/api/recommendRegularSize"

  def happyDay(userId: String, userProfileId:String, productIds:String) = {
    "{"+
      "\"request_header\": {"+
        "\"timestamp\": "+timestamp+","+
        "\"key\": \""+publicKeyBase64()+"\""+
      "},"+
      "\"request_body\": {"+
        "\"user_id\": "+userId+","+
        "\"user_profile_id\": "+userProfileId+","+
        "\"product_ids\": ["+productIds+"]"+
      "}"+
    "}"
  }
}