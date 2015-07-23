package com.codesimples.jdbc.user

import javax.sql.DataSource

import com.codesimples.jdbc.JDBCTemplateBuilder
import com.codesimples.objectives.persistence.adapter.user.NewUserPersistenceAdapter
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory


object UserPersistenceAdapter {
  val INSERT_NEW_USER:String = ""

  def buildNewWith(dataSource:DataSource): UserPersistenceAdapter = new UserPersistenceAdapter(dataSource)
}

class UserPersistenceAdapter(val dataSource:DataSource) extends JDBCTemplateBuilder with NewUserPersistenceAdapter {
  val logger = Logger( LoggerFactory.getLogger( this.getClass ) )
  
  override def save(map: Map[String, AnyRef]): Map[String, AnyRef] = {
    logger.debug(UserPersistenceAdapter.INSERT_NEW_USER)
    jdbcTemplate.execute(UserPersistenceAdapter.INSERT_NEW_USER)
    Map[String,AnyRef]("teste" -> "teste")
  }
}
