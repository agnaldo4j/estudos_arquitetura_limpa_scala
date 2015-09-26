package com.codesimples.simple.restapi.console

import java.io.File

import com.codesimples.algorithm.signature.HttpSender
import com.codesimples.simple.restapi.analysis.AnalysisMessage
import com.codesimples.simple.restapi.deduction.DeductionMessage
import com.codesimples.simple.restapi.recommendation.RecommendationMessage
import com.typesafe.config.{Config, ConfigFactory}

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