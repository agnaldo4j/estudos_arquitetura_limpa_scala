package com.sizebay.algorithm.restapi.model

case class AnalysisRecommendationRequestObject(jsonObject:Map[String,Any]) {

  def userId(): Int = {
    requestBody().getOrElse("user_id", 0).asInstanceOf[Int]
  }

  def userProfileId(): Int = {
    requestBody().getOrElse("user_profile_id", 0).asInstanceOf[Int]
  }

  def productId(): Int = {
    requestBody().getOrElse("product_id", 0L).asInstanceOf[Int]
  }

  def productGtin(): String = {
    requestBody().getOrElse("product_gtin", "").asInstanceOf[String]
  }

  def productPermlink(): String = {
    requestBody().getOrElse("product_permlink", "").asInstanceOf[String]
  }

  def requestBody(): Map[String, Any] = {
    jsonObject.getOrElse("request_body", Map[String,Any]()).asInstanceOf[Map[String,Any]]
  }
}
