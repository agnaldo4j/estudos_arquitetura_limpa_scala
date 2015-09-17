package com.codesimples.simple.restapi.basic

import java.io.File

import com.sizebay.algorithm.signature.{HttpSender, JsonUtil}
import com.typesafe.config.{Config, ConfigFactory}
import org.specs2.mutable.Specification

class BasicBehaviour extends Specification {
  val config:Config = ConfigFactory.parseFile(new File("/usr/share/sizebay/sizebayRestAPIHarnessSystem.conf")).getConfig("httpConfig")
  val httpSender = new HttpSender(config)

  def sendToEndpoint(endpoint:String, json:String): String = {
    httpSender.sendToEndpoint(endpoint, json)
  }

  def response(json:String): Map[String,Any] = {
    JsonUtil.toMap[Map[String,Any]](json)
  }

  def responseHeader(responseObject:Map[String,Any]): Map[String,Any] = {
    responseObject.getOrElse("response_header", Map[String,Any]()).asInstanceOf[Map[String,Any]]
  }

  def verifyResponseCodeOk(json:String) = {
    val responseHeaderObject = responseHeader( response(json) )
    responseCode(responseHeaderObject) must be equalTo(200)
  }

  def verifyResponseCode(json:String, intentCode:Int) = {
    val responseHeaderObject = responseHeader( response(json) )
    responseCode(responseHeaderObject) must be equalTo(intentCode)
  }

  def responseCode(responseHeaderObject:Map[String,Any]): Int = responseHeaderObject.getOrElse("code", 0).asInstanceOf[Int]
}
