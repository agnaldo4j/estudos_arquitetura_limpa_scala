package com.codesimples.simple.restapi

import javax.servlet.ServletContext

import akka.actor.{ActorSelection, ActorSystem}
import com.typesafe.config.Config
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {
	  val system = ActorSystem("sizebayRestAPIControllerSystem", Boot.config)

	  val sizebayAPIConfig:Config = Boot.restApiConfig

    val sizebayRequestBalancerControllerActor = getSizebayRequestBalancerControllerActor(system)

    val dataSource = Boot.dataSource

    context mount (new CodeSimplesAPI(system, sizebayAPIConfig, sizebayRequestBalancerControllerActor, dataSource), "/api/*")

    context.initParameters("org.scalatra.environment") = "production"
  }

  def getSizebayRequestBalancerControllerActor(system:ActorSystem):ActorSelection = {
    val sizebayRequestBalancerConfig = Boot.sizebayRequestBalancerConfig
    system.actorSelection("akka.tcp://"+ sizebayRequestBalancerConfig.getString("actorSystem") +"@"+ sizebayRequestBalancerConfig.getString("host") +":"+ sizebayRequestBalancerConfig.getInt("port") +"/user/"+sizebayRequestBalancerConfig.getString("actorName"))
  }
}