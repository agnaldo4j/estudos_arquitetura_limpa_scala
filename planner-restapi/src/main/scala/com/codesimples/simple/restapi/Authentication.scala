package com.codesimples.simple.restapi

import java.util.Date
import javax.sql.DataSource

import com.codesimples.algorithm.signature.{Signature, JsonUtil}
import org.apache.commons.codec.binary.Base64
import org.scalatra.ScalatraBase

import scala.io.Source

trait Authentication extends ScalatraBase{

  val dataSource:DataSource

  def authenticated( callback: (Map[String, AnyRef]) => Unit ) {
    val signature = request.getParameter("sig")

    val json = Source.fromInputStream( request.getInputStream ).mkString("")

    def jsonObject = JsonUtil.toMap[Map[String,AnyRef]]( json )

    val publicKey = getPublicKeyFromRequestHeader(jsonObject)

    val privateKey = getPrivateKeyByPublicKey(publicKey)

    val isValid = new Signature().signatureVerification(request.getMethod, request.getRequestURL.toString, json, privateKey, signature, new Date())

    if (isValid) callback(jsonObject)
    else halt(401, JsonUtil.toJson(Map[String,AnyRef]()))
  }

  private def getPrivateKeyByPublicKey(publicKey: String): String = {
    //val result = SelectingSecretKeyByPublicKey.buildNewWith(dataSource).execute(publicKey)
    //result.getOrElse("private_key", "undefined_private_key").asInstanceOf[String]
    ""
  }

  private def getPublicKeyFromRequestHeader(jsonObject: Map[String, AnyRef]): String = {
    val requestObject = jsonObject.getOrElse("request_header", Map[String, AnyRef]()).asInstanceOf[Map[String,AnyRef]]
    val encodedPublicKey = requestObject.getOrElse("key", "undefined").asInstanceOf[String]
    new String( Base64.decodeBase64(encodedPublicKey.getBytes()) )
  }
}
