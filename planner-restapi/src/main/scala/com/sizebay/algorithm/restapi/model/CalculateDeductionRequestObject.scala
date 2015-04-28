package com.sizebay.algorithm.restapi.model

case class CalculateDeductionRequestObject(jsonObject:Map[String,Any]) {

  def gender(): String = {
    requestBody().getOrElse("gender", "").asInstanceOf[String]
  }

  def height(): Int = {
    requestBody().getOrElse("height", 0).asInstanceOf[Int]
  }

  def weight(): Int = {
    requestBody().getOrElse("weight", 0).asInstanceOf[Int]
  }

  def age(): Int = {
    requestBody().getOrElse("age", Int).asInstanceOf[Int]
  }

  def shapeChest(): Int = {
    requestBody().getOrElse("shapeChest", Int).asInstanceOf[Int]
  }

  def shapeWaist(): Int = {
    requestBody().getOrElse("shapeWaist", Int).asInstanceOf[Int]
  }

  def shapeHip(): Int = {
    requestBody().getOrElse("shapeHip", Int).asInstanceOf[Int]
  }

  def requestBody(): Map[String, Any] = {
    jsonObject.getOrElse("request_body", Map[String,Any]()).asInstanceOf[Map[String,Any]]
  }

  def validate(): Boolean = {
    this.gender().toLowerCase match {
      case "m" => {
        ((40 to 199).contains(this.weight()) &&
          (13 to 99).contains(this.age()) &&
          (152 to 204).contains(this.height()))
      } case "f" => {
        ((45 to 120).contains(this.weight()) &&
          (14 to 99).contains(this.age()) &&
          (145 to 190).contains(this.height()))
      } case _ => throw new IllegalStateException("invalid gender!")
    }
  }

  def invalidJSON(): String = {
    "{\"response_header\":{" +
      "\"timestamp\":"+System.currentTimeMillis()+"," +
      "\"code\":500," +
      "\"message\":\"Internal Server Error\"," +
      "\"code\":1," +
      "\"message\":\"Invalid Parameters\"" +
     "}}"
  }
}
