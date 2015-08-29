package com.codesimples.jdbc.user

import com.codesimples.jdbc.{JDBCTemplateBuilder, JDBCTransactionAdapter}
import com.codesimples.objectives.persistence.adapter.user.{NewUserPersistenceAdapter, TransactionAdapter}
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.support.TransactionCallback
import org.springframework.transaction.{PlatformTransactionManager, TransactionStatus}


object UserPersistenceAdapter {
  val INSERT_NEW_USER:String = ""

  def buildNewWith(platformTransactionManager: PlatformTransactionManager): UserPersistenceAdapter = new UserPersistenceAdapter(platformTransactionManager)
}

class UserPersistenceAdapter(val platformTransactionManager: PlatformTransactionManager) extends JDBCTemplateBuilder with NewUserPersistenceAdapter {
  val logger = Logger( LoggerFactory.getLogger( this.getClass ) )
  
  override def saveUser(map: Map[String, AnyRef]): Map[String, AnyRef] = {
    logger.debug(UserPersistenceAdapter.INSERT_NEW_USER)
    //jdbcTemplate.execute(UserPersistenceAdapter.INSERT_NEW_USER)
    jdbcTransactionalTemplate().execute(new TransactionCallback[Map[String,AnyRef]] {
      override def doInTransaction(transactionStatus: TransactionStatus): Map[String, AnyRef] = {
        Map[String,AnyRef]()
      }
    })
    Map[String,AnyRef]("teste" -> "teste")
  }

  override def openTransaction(): TransactionAdapter = {
    val transactionAdapter = new JDBCTransactionAdapter()
    transactionAdapter
  }
}
