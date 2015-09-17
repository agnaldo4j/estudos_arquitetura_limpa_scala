package com.codesimples.jdbc

import com.typesafe.scalalogging.Logger
import org.springframework.transaction.support.{TransactionCallbackWithoutResult, TransactionTemplate}
import org.springframework.transaction.{PlatformTransactionManager, TransactionStatus}

trait JDBCTemplate {
  implicit val platformTransactionManager: PlatformTransactionManager
  implicit val logger: Logger

  def withTransaction( callback:  => Unit ) = {
    val transactionCallback: TransactionCallbackWithoutResult = transactionalCallback(callback)
    new TransactionTemplate(platformTransactionManager).execute(transactionCallback)
  }

  def transactionalCallback(callback: => Unit): TransactionCallbackWithoutResult = {
    new TransactionCallbackWithoutResult {
      override def doInTransactionWithoutResult(transactionStatus: TransactionStatus) = {
        try {
          callback
          transactionStatus.flush()
        } catch {
          case e: Exception => {
            transactionStatus.setRollbackOnly()
            logger.error("Transaction Error: ", e)
          }
        }
      }
    }
  }
}
