package com.codesimples.jdbc.user

import javax.sql.DataSource

import com.codesimples.jdbc.JDBCTemplateBuilder
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

object SelectingMeasuresByUser {
  val SELECT_MEASURES_OF_USER:String = "SELECT " +
      "rmpu.id_tipo_medida, " +
      "tmc.id id_tipo_medida_corpo, " +
      "tmcu.id id_tipo_medida_corpo_usuario, " +
      "tmc.tmc_abrev_campo descricao, " +
      "tmc.tipo_medida, " +
      "rmpu.id_folga_geral, " +
      "tmcu.valor_medida_inicial, " +
      "tmcu.valor_medida_final " +
    "FROM " +
      "szb_folga_geral sfg, " +
      "szb_tipo_medida_corpo tmc, " +
      "szb_tipo_medida_corpo_consumidor tmcu, " +
      "szb_relacao_medida_produto_consumidor rmpu " +
    "WHERE " +
      "sfg.id = rmpu.id_folga_geral and " +
      "rmpu.id_tipo_medida_corpo = tmc.id and " +
      "tmc.id = tmcu.id_tipo_medida_corpo and " +
      "tmcu.status_medida = 1 and "+
      "sfg.genero = '#1' and " +
      "tmcu.id_usuario_portal = #2 and "+
      "tmcu.tmcu_psr_id = #3"

  def buildNewWith(dataSource:DataSource): SelectingMeasuresByUser = new SelectingMeasuresByUser(dataSource)
}

class SelectingMeasuresByUser(val dataSource:DataSource) extends JDBCTemplateBuilder {
  val logger = Logger( LoggerFactory.getLogger( this.getClass ) )
  def execute(userId: Long, userprofileId:Long, gender: String): List[java.util.Map[String, AnyRef]] = {
    logger.debug(SelectingMeasuresByUser.SELECT_MEASURES_OF_USER.replaceAll("#1", gender).replaceAll("#2", userId.toString).replaceAll("#3", userprofileId.toString))
    jdbcTemplate.queryForList(SelectingMeasuresByUser.SELECT_MEASURES_OF_USER.replaceAll("#1", gender).replaceAll("#2", userId.toString).replaceAll("#3", userprofileId.toString)).toList
  }
}
