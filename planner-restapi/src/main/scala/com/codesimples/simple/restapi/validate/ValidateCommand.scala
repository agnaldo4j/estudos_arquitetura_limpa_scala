package com.codesimples.simple.restapi.validate

object ValidateCommand {

  def execute(jsonObject:Map[String,Any]): Map[String,Any] = {
    val requestBody = jsonObject.getOrElse("request_body", Map[String,Any]()).asInstanceOf[Map[String,Any]]
    val typeSymulation = requestBody.getOrElse("type_simulation", "fail")

    typeSymulation match {
      case "fail" => ValidateFailCommand.execute()
      case "success" => ValidateSuccessCommand.execute()
    }
  }
}
