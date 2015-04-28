package com.sizebay.algorithm.restapi.analysis

import com.sizebay.algorithm.restapi.basic.BasicBehaviour

class AnalysisSpec extends BasicBehaviour  {

  "The recommendation endpoint" should {
    args(sequential=true)
    "Execute a test request for success command" ! recommendationForTest().executeTestRequestSuccessCommand()
  }

  case class recommendationForTest() {

    def executeTestRequestSuccessCommand() = {
      /*
      println(AnalysisMessage.happyDay("68", "77", "175"))
      val response = sendToEndpoint(AnalysisMessage.endpoint(), AnalysisMessage.happyDay("68", "77", "175"))
      println(response)
      verifyResponseCodeOk( response )
      */
      pending
    }
  }
}
