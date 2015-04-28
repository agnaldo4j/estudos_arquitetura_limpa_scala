package com.sizebay.algorithm.restapi

import java.util.Date

import org.apache.commons.codec.binary.Base64

class BasicMessage {
  def endpoint() = "/api/validate"

  def timestamp(): Long = {
    new Date().getTime()
  }

  def publicKeyBase64() : String = {
    Base64.encodeBase64String( publicKey().getBytes )
  }

  def publicKey() : String = {
    "0987654321"
  }

  def privateKey(): String = {
    "1234567890"
  }
}