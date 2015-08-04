package com.codesimples.jpa.adapter.user

import scala.collection.JavaConversions._
import com.codesimples.jpa.domain.User
import com.codesimples.jpa.repositories.UserRepository
import com.codesimples.objectives.persistence.adapter.user.{TransactionAdapter, NewUserPersistenceAdapter}

class NewUserPersistenceAdapterJPA(val userRepository: UserRepository) extends NewUserPersistenceAdapter {

  def withTransaction( callback:  => Unit ) = {
    val transaction = userRepository.entityManager.getTransaction
    transaction.begin()
    callback
    transaction.commit()
  }

  override def saveUser(map: Map[String, AnyRef]): Map[String, AnyRef] = {
    def user = new User(map)
    userRepository.save(user)
    user.toMap().toMap
  }

  override def openTransaction(): TransactionAdapter = {
    val transaction = userRepository.entityManager.getTransaction
    transaction.begin()
    new TransactionAdapterJPA(transaction)
  }
}
