package com.codesimples.simple.restapi.validate

object ValidateFailCommand {
  def execute(): Map[String,Any] = {
    Map[String,Any](
      "response_header" -> Map[String, Any](
        "timestamp" -> 1390480115,
        "code"-> 500,
        "message"-> "Internal Server Error",
        "error_code"-> 1001,
        "error_message" -> "Erro ao calcular tamanho cintura"
      )
    )
  }
}
