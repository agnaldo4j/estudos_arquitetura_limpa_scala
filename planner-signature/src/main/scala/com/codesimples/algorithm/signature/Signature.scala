package com.codesimples.algorithm.signature

import java.util.Date
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

import com.github.nscala_time.time.Imports._
import org.apache.commons.codec.binary.{Base64, Hex}

class Signature {

  def signatureVerification(httpMethod: String, serverEndpoint:String, json: String, plainSecret: String, currentSignature:String, baseline_date:Date): Boolean = {
    val mapObject = JsonUtil.toMap[Map[String,Any]](json)
    (isValidSignatureTime(mapObject, baseline_date) && isValidSignature(httpMethod, serverEndpoint, json, plainSecret, currentSignature:String))
  }

  def isValidSignature(httpMethod: String, serverEndpoint:String, json: String, plainSecret: String, currentSignature:String): Boolean = {
    val signature = generateSignature(httpMethod, serverEndpoint, json, plainSecret)
    signature.equals(currentSignature)
  }

  def isValidSignatureTime(mapObject: Map[String,Any], baseline_date:Date): Boolean = {
    val invalid_time = (new DateTime( baseline_date.getTime ).minusMinutes(5)).toDate.getTime
    val baseline_time = (new DateTime( baseline_date.getTime ).minusMinutes(1)).toDate.getTime
    val request_header = mapObject.getOrElse("request_header", Map[String,Any]()).asInstanceOf[Map[String,Any]]
    val request_timestamp = request_header.getOrElse("timestamp", invalid_time).asInstanceOf[Long]
    (request_timestamp >= baseline_time)
  }

  def generateSignature(httpMethod: String, serverEndpoint:String, json: String, plainSecret: String): String = {
    val data = buildSignatureData(httpMethod, serverEndpoint, json, plainSecret)
    signData(data, plainSecret)
  }

  private def signData(data: String, plainSecret: String, algorithm: String = "HmacSHA256") = {
    val secretKey = new SecretKeySpec(plainSecret.getBytes("UTF-8"), algorithm)
    val mac = Mac.getInstance(algorithm)
    mac.init(secretKey)
    Hex.encodeHexString(mac.doFinal(data.getBytes("UTF-8")))
  }

  private def buildSignatureData(httpMethod:String, serverEndpoint: String, json: String, plainSecret: String): String = {
    httpMethod.toUpperCase+"\n"+serverEndpoint+"\n"+json+"\n"+encodeBase64(plainSecret)
  }

  private def encodeBase64(plainString:String): String = Base64.encodeBase64String(plainString.getBytes())
}
