package com.codesimples.simple.restapi

import javax.sql.DataSource

import akka.actor.{ActorSelection, ActorSystem}
import com.codesimples.simple.restapi.validate.ValidateCommand
import com.sizebay.algorithm.signature.JsonUtil
import com.typesafe.config.Config
import org.scalatra._


class CodeSimplesAPI(val system: ActorSystem, val restApiConfig: Config, val requestBalancerControllerActor: ActorSelection, val dataSource: DataSource) extends ScalatraServlet with Authentication {

  get("/validate") {
    contentType = "application/json"
    authenticated { jsonObject =>
      writeOkResponse( ValidateCommand.execute(jsonObject) )
    }
  }

  post("/validate") {
    contentType = "application/json"
    authenticated { jsonObject =>
      writeOkResponse( ValidateCommand.execute(jsonObject) )
    }
  }

  post("/recommendRegularSize") {
    contentType = "application/json"
    authenticated { jsonObject =>
      writeOkResponse( calculateRecommentadion(jsonObject) )
    }
  }

  post("/analysisRegularSize") {
    contentType = "application/json"
    authenticated { jsonObject =>
      writeOkResponse( analysisRecommentadion(jsonObject) )
    }
  }

  post("/deductionSize") {
    contentType = "application/json"
    authenticated { jsonObject =>
      writeOkResponse( calculateDeduction(jsonObject) )
    }
  }

  def calculateRecommentadion(jsonObject:Map[String, Any]): String =  {
    //implicit val timeout = Timeout(10 seconds)
    //val requestObject = CalculateRecommendationRequestObject(jsonObject)
    //val message = CalculateRecommendation(requestObject.userId(), requestObject.userProfileId(), requestObject.productIds(), requestObject.productGtins(), requestObject.productPermlinks())
    //val future = requestBalancerControllerActor ? message
    //val response = Await.result(future, timeout.duration).asInstanceOf[Response]
    //response.json
    ""
  }

  def analysisRecommentadion(jsonObject:Map[String, Any]): String =  {
    /*
    implicit val timeout = Timeout(10 seconds)
    val requestObject = AnalysisRecommendationRequestObject(jsonObject)
    val message = AnalysisRecommendation(requestObject.userId(), requestObject.userProfileId(), requestObject.productId(), requestObject.productGtin(), requestObject.productPermlink())
    val future = requestBalancerControllerActor ? message
    val response = Await.result(future, timeout.duration).asInstanceOf[Response]
    response.json
    */
    ""
  }

  def calculateDeduction(jsonObject:Map[String, Any]): String =  {
    /*
    implicit val timeout = Timeout(10 seconds)
    val requestObject = CalculateDeductionRequestObject(jsonObject)
    if (requestObject.validate()) {
      val message = CalculateDeduction(requestObject.gender(), requestObject.height(), requestObject.weight(), requestObject.age(), requestObject.shapeChest(), requestObject.shapeWaist(), requestObject.shapeHip())
      val future = requestBalancerControllerActor ? message
      val response = Await.result(future, timeout.duration).asInstanceOf[Response]
      response.json
    } else {
      requestObject.invalidJSON()
    }
    */
    ""
  }

  private def writeOkResponse(map: Map[String,Any]) {
    halt(200, JsonUtil.toJson(map))
  }

  private def writeOkResponse(json:String) {
    halt(200, json)
  }
}