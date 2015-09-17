package com.codesimples.simple.restapi

import java.io.File
import javax.sql.DataSource
import com.codesimples.jdbc.DataSourceBuilder
import com.typesafe.config.{Config, ConfigFactory}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object Boot {
  val config:Config = getConfig()

  val restApiConfig:Config = config.getConfig("restAPI")

  val sizebayRequestBalancerConfig:Config = config.getConfig("sizebayRequestBalancerController")

  val dataSource = getDataSource()
  
  def main(args: Array[String]) {
    val port = restApiConfig.getInt("port")

    println(restApiConfig.getString("resourceBase"))
    val server = new Server(port)
    val context = new WebAppContext()
    context setContextPath "/"
    context.setResourceBase(restApiConfig.getString("resourceBase"))
    context.setInitParameter(ScalatraListener.LifeCycleKey, "com.sizebay.algorithm.restapi.ScalatraBootstrap")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet],"/")

    server.setHandler(context)

    server.start
    server.join
  }

  def getConfig():Config = {
    ConfigFactory.parseFile(new File("/usr/share/sizebay/sizebayRestAPIControllerSystem.conf")).getConfig("sizebayRestAPIControllerSystem")
  }

  def getDataSource(): DataSource = {
    val jdbcConfig = config.getConfig("jdbcConfig")
    DataSourceBuilder.buildDataSourceWith( jdbcConfig )
  }
}
