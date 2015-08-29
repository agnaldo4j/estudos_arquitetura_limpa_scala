package com.codesimples.jpa.adapter.user

import com.codesimples.jpa.JPATemplate
import com.codesimples.jpa.domain.User
import com.codesimples.jpa.repositories.UserRepository
import com.codesimples.objectives.persistence.adapter.user.NewUserPersistenceAdapter
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

class NewUserPersistenceAdapterJPA(val userRepository: UserRepository) extends JPATemplate with NewUserPersistenceAdapter {
  val logger = Logger( LoggerFactory.getLogger( this.getClass ) )
  val entityManager = userRepository.entityManager

  override def saveUser(map: Map[String, AnyRef]) = {
    def user = new User(map)
    userRepository.save(user)
  }

  override def findUserById(id: String): Map[String, AnyRef] = {
    val user = userRepository.getOne(id)
    Map[String, AnyRef](
      "id" -> user.id,
      "persistenceType" -> user.persistenceType
    )
  }
}
