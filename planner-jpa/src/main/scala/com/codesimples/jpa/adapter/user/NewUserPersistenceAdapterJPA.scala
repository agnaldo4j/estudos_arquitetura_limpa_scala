package com.codesimples.jpa.adapter.user

import com.codesimples.jpa.domain.User
import com.codesimples.jpa.repositories.UserRepository
import com.codesimples.objectives.persistence.adapter.user.NewUserPersistenceAdapter
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

class NewUserPersistenceAdapterJPA(val userRepository: UserRepository) extends NewUserPersistenceAdapter {
  val logger = Logger( LoggerFactory.getLogger( this.getClass ) )

  def withTransaction( callback:  => Unit ) = {
    val transaction = userRepository.entityManager.getTransaction
    transaction.begin()
    try {
      callback
      transaction.commit()
    } catch {
      case e: Exception => {
        if(transaction.isActive) transaction.rollback()
        logger.error("Transaction Error: ", e)
      }
    }
  }

  override def saveUser(map: Map[String, AnyRef]) = {
    def user = new User(map)
    userRepository.save(user)
  }
}
