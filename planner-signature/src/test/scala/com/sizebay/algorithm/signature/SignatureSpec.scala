package com.sizebay.algorithm.signature

import java.util.Date

import org.specs2.mutable.Specification

class SignatureSpec extends Specification {

  val default_signature = "4b5fd18fae8a211ea592ccbd7be7156a0953f5da89567c6835706c10753e804e"
  val default_private_key = "1234567890"
  val date_baseline = new Date(1415640746139L)

  "The Deactivate download endpoint" should {
    args(sequential=true)
    "Generate a single signature" ! authenticationSpecForTest().generateASingleSignature()
    "Valid signature happy day" ! authenticationSpecForTest().validSignaturaHappyDay()
    "invalid signature time" ! authenticationSpecForTest().invalidSignatureTime()
  }

  private def defaultRequestObject(timeAgo:Long = 0L): String = {
    val timestamp = ((date_baseline.getTime) - timeAgo)
    "{"+
      "\"request_header\": {"+
        "\"timestamp\": "+timestamp+","+
        "\"key\": \"0987654321\""+
      "},"+
      "\"request_body\": {"+
        "\"timestamp\": "+timestamp+","+
        "\"type_simulation\": \"success\""+
      "}"+
    "}"
  }

  private def minutesInMilis(minutes:Int): Long = {
    minutes * 60 * 1000
  }

  case class authenticationSpecForTest() {
    def generateASingleSignature() = {
      val authentication = new Signature()
      val signature = authentication.generateSignature("post", "http://127.0.0.1:8080/validate", defaultRequestObject(), default_private_key)
      signature must beEqualTo(default_signature)
    }

    def validSignaturaHappyDay() = {
      val authentication = new Signature()

      val json = defaultRequestObject()

      val signature = authentication.generateSignature("post", "http://127.0.0.1:8080/validate", json, default_private_key)
      val valid = authentication.signatureVerification("post", "http://127.0.0.1:8080/validate", json, default_private_key, signature, date_baseline)
      valid must beTrue
    }

    def invalidSignatureTime() = {
      val authentication = new Signature()

      val json = defaultRequestObject( minutesInMilis(2) )

      val signature = authentication.generateSignature("post", "http://127.0.0.1:8080/validate", json, default_private_key)
      val timeValid = authentication.isValidSignatureTime( JsonUtil.toMap[Map[String,Any]](json), date_baseline )
      timeValid must beFalse

      val sigValid = authentication.isValidSignature("post", "http://127.0.0.1:8080/validate", json, default_private_key, signature)
      sigValid must beTrue

      println(json)
      val valid = authentication.signatureVerification("post", "http://127.0.0.1:8080/validate", json, default_private_key, signature, date_baseline)
      valid must beFalse
    }
  }
}
