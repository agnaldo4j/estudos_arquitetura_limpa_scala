package com.codesimples.jdbc.user

import com.codesimples.jdbc.JDBCTemplateBuilder
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.PlatformTransactionManager

import scala.collection.JavaConversions._

object SelectingUserById {
  val SELECT_USER:String = "SELECT " +
      "usuario.usr_id, " +
      "perfil.psr_id_gnr usr_genero " +
    "FROM " +
      "szb_usuario_consumidor usuario, " +
      "szb_perfil_usuario perfil " +
    "where " +
      "perfil.psr_usr_id = usuario.usr_id and "+
      "usuario.usr_id = #1"

  def buildNewWith(platformTransactionManager: PlatformTransactionManager): SelectingUserById = new SelectingUserById(platformTransactionManager)
}

class SelectingUserById(val platformTransactionManager: PlatformTransactionManager) extends JDBCTemplateBuilder {
  val logger = Logger( LoggerFactory.getLogger( this.getClass ) )

  def execute(userId: Long): java.util.Map[String, AnyRef] = {
    logger.debug(SelectingUserById.SELECT_USER.replaceAll("#1", userId.toString))
    val results = jdbcTemplate.queryForList(SelectingUserById.SELECT_USER.replaceAll("#1", userId.toString)).toList
    if (results.isEmpty) new java.util.HashMap[String, AnyRef]()
    else results.head
  }
}