package com.codesimples.jdbc.user

import com.codesimples.jdbc.JDBCTemplateBuilder
import com.codesimples.objectives.persistence.adapter.user.NewUserPersistenceAdapter
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.PlatformTransactionManager


object UserPersistenceAdapter {
  val INSERT_NEW_USER:String = "INSERT INTO planner.User (id, persistence_type) VALUES ('#1', '#2');"

  def buildNewWith(platformTransactionManager: PlatformTransactionManager): UserPersistenceAdapter = new UserPersistenceAdapter(platformTransactionManager)
}

class UserPersistenceAdapter(val platformTransactionManager: PlatformTransactionManager) extends JDBCTemplateBuilder with NewUserPersistenceAdapter {
  val logger = Logger( LoggerFactory.getLogger( this.getClass ) )

  override def saveUser(map: Map[String, AnyRef]) = {
    logger.debug(UserPersistenceAdapter.INSERT_NEW_USER.replaceAll("#1", map.getOrElse("id", "").asInstanceOf[String]).replaceAll("#2", map.getOrElse("persistenceType", "").asInstanceOf[String]))
    jdbcTemplate.execute(UserPersistenceAdapter.INSERT_NEW_USER.replaceAll("#1", map.getOrElse("id", "").asInstanceOf[String]).replaceAll("#2", map.getOrElse("persistenceType", "").asInstanceOf[String]))
  }
}
