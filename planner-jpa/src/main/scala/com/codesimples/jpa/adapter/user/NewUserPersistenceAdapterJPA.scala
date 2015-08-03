package com.codesimples.jpa.adapter.user

import scala.collection.JavaConversions._
import com.codesimples.jpa.domain.User
import com.codesimples.jpa.repositories.UserRepository
import com.codesimples.objectives.persistence.adapter.user.{TransactionAdapter, NewUserPersistenceAdapter}

class NewUserPersistenceAdapterJPA(val userRepository: UserRepository) extends NewUserPersistenceAdapter {

  override def save(map: Map[String, AnyRef]): Map[String, AnyRef] = {
    def user = new User(map)
    userRepository.save(user)
    user.toMap().toMap
  }

  override def openTransaction(): TransactionAdapter = {
    new TransactionAdapterJPA(userRepository.entityManager.getTransaction)
  }
}
