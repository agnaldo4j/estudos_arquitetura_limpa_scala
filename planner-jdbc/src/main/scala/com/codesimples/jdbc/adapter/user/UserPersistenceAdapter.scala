package com.codesimples.jdbc.adapter.user

import com.codesimples.jdbc.JDBCTemplate
import com.codesimples.jdbc.repositories.UserRepository
import com.codesimples.objectives.persistence.adapter.user.NewUserPersistenceAdapter
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.PlatformTransactionManager

class UserPersistenceAdapter(val userRepository: UserRepository) extends JDBCTemplate with NewUserPersistenceAdapter {
  val logger = Logger( LoggerFactory.getLogger( this.getClass ) )
  implicit val platformTransactionManager: PlatformTransactionManager = userRepository.platformTransactionManager

  override def saveUser(map: Map[String, AnyRef]) = {
    userRepository.save(map)
  }

  override def findUserById(id: String): Map[String, AnyRef] = {
    userRepository.getOne(id)
  }
}
