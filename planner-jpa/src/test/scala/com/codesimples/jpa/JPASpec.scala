package com.codesimples.jpa

import javax.persistence.EntityManagerFactory

import com.codesimples.jpa.adapter.user.NewUserPersistenceAdapterJPA
import com.codesimples.jpa.domain.User
import com.codesimples.jpa.repositories.UserRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

import scala.collection.JavaConversions._
import com.typesafe.config.ConfigFactory
import org.specs2.mutable.Specification

class JPASpec extends Specification {

  val entityManagerFactory = newEntityManagerFactory()

  "The selectin products by id" should {
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

      userPersistenceAdapter.withTransaction {
        userPersistenceAdapter.saveUser(Map[String,AnyRef]())
      }

      entityManager.close()
      success
    }
  }
}
