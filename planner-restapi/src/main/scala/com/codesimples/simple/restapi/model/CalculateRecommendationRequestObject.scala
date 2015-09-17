package com.codesimples.simple.restapi.model

case class CalculateRecommendationRequestObject(jsonObject:Map[String,Any]) {

  def userId(): Long = {
    requestBody().getOrElse("user_id", 0).asInstanceOf[Int].toLong
  }

  def userProfileId(): Long = {
    requestBody().getOrElse("user_profile_id", 0).asInstanceOf[Int].toLong
  }

  def productIds(): List[Long] = {
    requestBody().getOrElse("product_ids", List[Long]()).asInstanceOf[List[Long]]
  }

  def productGtins(): List[String] = {
    requestBody().getOrElse("product_gtins", List[String]()).asInstanceOf[List[String]]
  }

  def productPermlinks(): List[String] = {
    requestBody().getOrElse("product_permlinks", List[String]()).asInstanceOf[List[String]]
  }

  def requestBody(): Map[String, Any] = {
    jsonObject.getOrElse("request_body", Map[String,Any]()).asInstanceOf[Map[String,Any]]
  }
}
