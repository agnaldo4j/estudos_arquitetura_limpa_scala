package com.codesimples.simple.restapi.deduction

import com.codesimples.simple.restapi.basic.BasicBehaviour

class DeductionSpec extends BasicBehaviour  {

  "The recommendation endpoint" should {
    args(sequential=true)
    //"Execute a test request for success command" ! recommendationForTest().executeTestRequestSuccessCommand()
    "Execute a test request woman for success command" ! recommendationForTest().executeTestRequestWomanSuccessCommand()
  }

  case class recommendationForTest() {

    def executeTestRequestSuccessCommand() = {
      println( DeductionMessage.happyDay("M", 172, 100, 31, 0, 2, 2) )
      val response = sendToEndpoint(DeductionMessage.endpoint(), DeductionMessage.happyDay("M", 172, 100, 31, 0, 2, 2) )
      println(response)
      verifyResponseCodeOk( response )
    }

    def executeTestRequestWomanSuccessCommand() = {
      /*
      println( DeductionMessage.happyDay("F", 172, 100, 31, 2, 0, 1) )
      val response = sendToEndpoint(DeductionMessage.endpoint(), DeductionMessage.happyDay("F", 172, 100, 31, 2, 0, 1) )
      println(response)
      verifyResponseCodeOk( response )
      */
      pending
    }
  }
}
