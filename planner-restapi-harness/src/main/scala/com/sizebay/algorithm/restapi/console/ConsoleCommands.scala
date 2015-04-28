package com.sizebay.algorithm.restapi.console

import java.io.File

import com.sizebay.algorithm.restapi.analysis.AnalysisMessage
import com.sizebay.algorithm.restapi.deduction.DeductionMessage
import com.sizebay.algorithm.restapi.recommendation.RecommendationMessage
import com.sizebay.algorithm.signature.HttpSender
import com.typesafe.config.{ConfigFactory, Config}

class ConsoleCommands(val environmentConfigPath:String) {
  val config:Config = ConfigFactory.parseFile(new File(environmentConfigPath)).getConfig("httpConfig")
  val httpSender = new HttpSender(config)

  def sendMessageToAnalysis(user:String, perfil:String, product: String): String = {
    sendToEndpoint(AnalysisMessage.endpoint(), AnalysisMessage.happyDay(user, perfil, product))
  }

  def sendMessageToRecommendation(user:String, perfil:String, product: String): String = {
    sendToEndpoint(RecommendationMessage.endpoint(), RecommendationMessage.happyDay(user, perfil, product))
  }

  def sendMessageToDeduction(gender: String, height: Int, weight: Int, age: Int, shapeChest: Int, shapeWaist: Int, shapeHip: Int): String = {
    sendToEndpoint(DeductionMessage.endpoint(), DeductionMessage.happyDay(gender, height, weight, age, shapeChest, shapeWaist, shapeHip) )
  }

  private def sendToEndpoint(endpoint:String, json:String): String = {
    httpSender.sendToEndpoint(endpoint, json)
  }
}