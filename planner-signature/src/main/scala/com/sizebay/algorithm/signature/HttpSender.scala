package com.sizebay.algorithm.signature

import java.net.{HttpURLConnection, URL}

import com.typesafe.config.Config

import scala.io.Source

class HttpSender(httpConfig:Config) {

  val WAIT_TIME = 60 * 20 * 1000
  val signature = new Signature()

  def sendToEndpoint(endpoint:String, json: String): String = {
    val baseUrl = httpConfig.getString("baseUrl")
    val completeUrl = baseUrl+endpoint
    val sig = signature.generateSignature("post", completeUrl, json, httpConfig.getString("plainSecretKey"))
    executePerformancePost(completeUrl+"?sig="+sig, json)
  }

  private def executePerformancePost(completeURL: String, json: String): String = {
    try {
      val connection = buildConnection(completeURL)
      configureConnectionToPostMessage(connection)
      try {
        writePostMessage(connection, json)
        val resultJSON = readPostResponse(connection)
        connection.disconnect()

        resultJSON
      } catch {
        case error: Exception => {
          error.printStackTrace()
          "{\"error\": \"true\"}"
        }
      }
    } catch {
      case error: Exception => {
        error.printStackTrace()
        "{\"error\": \"true\"}"
      }
    }
  }

  private def readPostResponse(connection: HttpURLConnection): String = {
    val inputStream = getInputOrErrorStream(connection)
    val resultJSON = Source.fromInputStream(inputStream).mkString("")
    inputStream.close()
    resultJSON
  }

  private def buildConnection(completeUrl:String): HttpURLConnection = {
    val url = new URL(completeUrl)
    url.openConnection().asInstanceOf[HttpURLConnection]
  }

  private def configureConnectionToPostMessage(connection: HttpURLConnection) = {
    connection.setRequestMethod("POST")
    connection.setRequestProperty("Content-Type", "application/json")
    connection.setRequestProperty("Accept", "application/json")
    //connection.setRequestProperty("Connection", "close")
    connection.setConnectTimeout(WAIT_TIME)
    connection.setReadTimeout(WAIT_TIME)
    connection.setDoOutput(true)
    connection.setDoInput(true)
    connection.setInstanceFollowRedirects(true)
  }

  private def writePostMessage(connection: HttpURLConnection, json: String) {
    val outputStream = connection.getOutputStream()
    outputStream.write(json.getBytes("UTF-8"))
    outputStream.flush()
    outputStream.close()
  }

  private def getInputOrErrorStream(connection: HttpURLConnection): java.io.InputStream = {
    // handle exceptions
    try {
      connection.getInputStream()
    } catch {
      case exception: java.io.IOException => {
        exception.printStackTrace()
        connection.getErrorStream()
      }
    }
  }
}