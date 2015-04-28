package com.sizebay.algorithm.restapi.validate

import com.sizebay.algorithm.restapi.basic.BasicBehaviour

class ValidateSpec extends BasicBehaviour  {

  "The validate endpoint endpoint" should {
    args(sequential=true)
    "Execute a test request for fail command" ! validateForTest().executeTestRequestFailCommand()
    "Execute a test request for success command" ! validateForTest().executeTestRequestSuccessCommand()
  }

  case class validateForTest() {

    def executeTestRequestFailCommand() = {
      verifyResponseCode( sendToEndpoint(ValidateMessage.endpoint(), ValidateMessage.happyDay("fail")), 500 )
    }

    def executeTestRequestSuccessCommand() = {
      verifyResponseCodeOk( sendToEndpoint(ValidateMessage.endpoint(), ValidateMessage.happyDay("success")) )
    }
  }
}
