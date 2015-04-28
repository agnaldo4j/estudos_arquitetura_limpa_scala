package com.sizebay.algorithm.restapi.recommendation

import com.sizebay.algorithm.restapi.basic.BasicBehaviour

class RecommendationSpec extends BasicBehaviour  {

  "The recommendation endpoint" should {
    args(sequential=true)
    //"Execute a test request for success command" ! recommendationForTest().executeTestRequestSuccessCommand()
    //"Execute a Superior Thiago's test request command" ! recommendationForTest().executeThiagoSuperiorRequestCommand()
    //"Execute a Superior Fábio's test request command" ! recommendationForTest().executeFabioSuperiorTestRequestCommand()
    //"Execute a Superior Elaine's test request command" ! recommendationForTest().executeElaineSuperiorTestRequestCommand()
    //"Execute a Inferior Elaine's test request command" ! recommendationForTest().executeElaineInferiorTestRequestCommand()
    //"Execute a Superior Patricia's test request command" ! recommendationForTest().executePatriciaSuperiorTestRequestCommand()
    "Execute a Inferior Elaine's test request command" ! recommendationForTest().executePatriciaInferiorTestRequestCommand()
  }

  case class recommendationForTest() {

    def executeTestRequestSuccessCommand() = {
      val response = sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay("40", "24", "1"))
      println(response)
      verifyResponseCodeOk( response )
    }

    def executeThiagoSuperiorRequestCommand() = {
      println( RecommendationMessage.happyDay("40", "24", "36") )
      val response = sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay("40", "24", "36"))
      println(response)
      verifyResponseCodeOk( response )
    }

    def executeAlyssonInferiorRequestCommand() = {
      val response = sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay("35", "24", "13"))
      println(response)
      verifyResponseCodeOk( response )
    }

    def executeFabioSuperiorTestRequestCommand() = {
      val response = sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay("42", "24", "36"))
      println(response)
      verifyResponseCodeOk( response )
    }

    def executeElaineSuperiorTestRequestCommand() = {
      val response = sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay("44", "24", "42"))
      println(response)
      verifyResponseCodeOk( response )
    }

    def executeElaineInferiorTestRequestCommand() = {
      val response = sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay("44", "24", "52"))
      println(response)
      verifyResponseCodeOk( response )
    }


    def executePatriciaSuperiorTestRequestCommand() = {
      println( RecommendationMessage.happyDay("67", "75", "95") )
      val response = sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay("67", "75", "95"))
      println(response)
      verifyResponseCodeOk( response )
    }

    def executePatriciaInferiorTestRequestCommand() = {
      /*
      val response = sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay("68", "77", "68"))
      println(response)
      verifyResponseCodeOk( response )
      */
      pending
    }
  }
}
