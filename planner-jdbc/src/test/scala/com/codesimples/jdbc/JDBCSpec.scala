package com.codesimples.jdbc

import java.util.UUID
import javax.sql.DataSource

import com.codesimples.jdbc.user.UserPersistenceAdapter
import com.typesafe.config.ConfigFactory
import org.specs2.execute.Result
import org.specs2.mutable.Specification
import org.springframework.jdbc.datasource.DataSourceTransactionManager

import scala.collection.JavaConversions._

class JDBCSpec extends Specification {

  val datasource = newDatasource()

  "The select products by id" should {
    args(sequential=true)
    "Add user with UserPersistenceAdapter" ! jdbcSpecForTest().addUserWithUserPersistenceAdapter()
  }

  private def newDatasource(): DataSource = {
    val mapConfig = Map[String, AnyRef](
      "driver" -> "com.mysql.jdbc.Driver",
      "url" -> "jdbc:mysql://localhost:3306/planner",
      "username" -> "root",
      "password" -> ""
    )

    val config = ConfigFactory.parseMap( mapConfig )
    DataSourceBuilder.buildDataSourceWith( config )
  }

  case class jdbcSpecForTest() {
    def addUserWithUserPersistenceAdapter() = {
      val transactionManager = new DataSourceTransactionManager( datasource )
      val userPersistenceAdapter = UserPersistenceAdapter.buildNewWith(transactionManager)

      val user = Map[String,AnyRef](
        "id" -> UUID.randomUUID().toString,
        "persistenceType" -> "jdbc"
      )

      userPersistenceAdapter.withTransaction {
        userPersistenceAdapter.saveUser(user)
      }

      pending
    }
  }
}
