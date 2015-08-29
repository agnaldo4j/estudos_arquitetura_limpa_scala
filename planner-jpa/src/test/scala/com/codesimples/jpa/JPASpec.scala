package com.codesimples.jpa

import java.util.UUID
import javax.persistence.EntityManagerFactory

import com.codesimples.jpa.adapter.user.NewUserPersistenceAdapterJPA
import com.codesimples.jpa.repositories.UserRepository
import com.typesafe.config.ConfigFactory
import org.specs2.mutable.Specification

import scala.collection.JavaConversions._

class JPASpec extends Specification {

  val entityManagerFactory = newEntityManagerFactory()

  "The select products by id" should {
    args(sequential=true)
    "Execute SelectingUserById" ! jpaSpecForTest().executeSelectingUserById()
  }

  private def newEntityManagerFactory(): EntityManagerFactory = {
    val mapConfig = Map[String, AnyRef](
      "driver" -> "com.mysql.jdbc.Driver",
      "url" -> "jdbc:mysql://localhost:3306/planner",
      "username" -> "root",
      "password" -> ""
    )

    val config = ConfigFactory.parseMap( mapConfig )
    new ApplicationConfig().entityManagerFactory(config)
  }

  case class jpaSpecForTest() {
    def executeSelectingUserById() = {
      val entityManager = entityManagerFactory.createEntityManager()
      val userRepository = new UserRepository(entityManager)
      val userPersistenceAdapter = new NewUserPersistenceAdapterJPA(userRepository)

      val user = Map[String,AnyRef](
        "id" -> UUID.randomUUID().toString,
        "persistenceType" -> "jpa"
      )

      userPersistenceAdapter.withTransaction {
        userPersistenceAdapter.saveUser(user)
      }

      val result = userPersistenceAdapter.findUserById(user.getOrElse("id", "").asInstanceOf[String])
      entityManager.close()
      result.getOrElse("id", "").asInstanceOf[String] must beEqualTo(user.getOrElse("id", "").asInstanceOf[String])
    }
  }
}
