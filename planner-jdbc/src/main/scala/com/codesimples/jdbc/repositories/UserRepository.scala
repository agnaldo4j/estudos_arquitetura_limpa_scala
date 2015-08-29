package com.codesimples.jdbc.repositories

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager

import scala.collection.JavaConversions._

class UserRepository(val platformTransactionManager: PlatformTransactionManager) extends JdbcTemplate(platformTransactionManager.asInstanceOf[DataSourceTransactionManager].getDataSource) {
  val myLogger = Logger( LoggerFactory.getLogger( this.getClass ) )
  val INSERT_NEW_USER:String = "INSERT INTO planner.User (id, persistence_type) VALUES ('#1', '#2');"
  val SELECT_USER_BY_ID:String = "SELECT * FROM planner.User WHERE id = '#1';"

  def save(user: Map[String, AnyRef]) = {
    myLogger.debug(INSERT_NEW_USER.replaceAll("#1", user.getOrElse("id", "").asInstanceOf[String]).replaceAll("#2", user.getOrElse("persistenceType", "").asInstanceOf[String]))
    execute(INSERT_NEW_USER.replaceAll("#1", user.getOrElse("id", "").asInstanceOf[String]).replaceAll("#2", user.getOrElse("persistenceType", "").asInstanceOf[String]))
  }

  def getOne(id: String): Map[String, AnyRef] = {
    myLogger.debug(SELECT_USER_BY_ID.replaceAll("#1", id))
    queryForMap(SELECT_USER_BY_ID.replaceAll("#1", id)).toMap[String,AnyRef]
  }
}
