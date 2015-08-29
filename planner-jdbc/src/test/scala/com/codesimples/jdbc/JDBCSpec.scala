package com.codesimples.jdbc

import java.util.UUID
import javax.sql.DataSource

import com.codesimples.jdbc.DataSourceBuilder
import com.codesimples.jdbc.user.{SelectingUserById, SelectingMeasuresByUser}
import com.codesimples.jdbc.user.SelectingUserById
import com.typesafe.config.ConfigFactory
import org.specs2.mutable.Specification
import org.springframework.jdbc.datasource.DataSourceTransactionManager

import scala.collection.JavaConversions._

class JDBCSpec extends Specification {

  val datasource = newDatasource()

  val processDatasource = newProcessDatasource()

  "The selectin products by id" should {
    args(sequential=true)
    "Execute SelectingUserById" ! jdbcSpecForTest().executeSelectingUserById()
    "Execute SelectingMeasuresByUser" ! jdbcSpecForTest().executeSelectingMeasuresByUser()
  }

  private def newDatasource(): DataSource = {
    val mapConfig = Map[String, AnyRef](
      "driver" -> "com.mysql.jdbc.Driver",
      "url" -> "jdbc:mysql://sizebay-alpha.cbolyybvvg70.sa-east-1.rds.amazonaws.com:3306/sizebay_alpha",
      "username" -> "sizebay",
      "password" -> "sizebay0z6g1t1t"
    )

    val config = ConfigFactory.parseMap( mapConfig )
    DataSourceBuilder.buildDataSourceWith( config )
  }

  private def newProcessDatasource(): DataSource = {
    val mapConfig = Map[String, AnyRef](
      "driver" -> "com.mysql.jdbc.Driver",
      "url" -> "jdbc:mysql://sizebay-alpha.cbolyybvvg70.sa-east-1.rds.amazonaws.com:3306/sizebay_process_alpha",
      "username" -> "sizebay",
      "password" -> "sizebay0z6g1t1t"
    )

    val config = ConfigFactory.parseMap( mapConfig )
    DataSourceBuilder.buildDataSourceWith( config )
  }

  case class jdbcSpecForTest() {
    def executeSelectingUserById() = {
      val transactionManager = new DataSourceTransactionManager( datasource )
      val result = SelectingUserById.buildNewWith( transactionManager ).execute(1)
      result.getOrElse("usr_genero", 0).asInstanceOf[Long] must be equalTo 1
    }

    def executeSelectingMeasuresByUser() = {
      val transactionManager = new DataSourceTransactionManager( datasource )
      val result = SelectingMeasuresByUser.buildNewWith( transactionManager ).execute(1, 1, "f")
      result.size must be equalTo 9
    }
  }
}
