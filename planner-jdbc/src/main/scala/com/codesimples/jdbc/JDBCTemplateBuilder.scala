package com.codesimples.jdbc

import com.typesafe.scalalogging.Logger
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.{TransactionStatus, PlatformTransactionManager}
import org.springframework.transaction.support.{TransactionCallbackWithoutResult, TransactionTemplate}

trait JDBCTemplateBuilder {
  implicit val platformTransactionManager: PlatformTransactionManager
  implicit val logger: Logger

  protected def jdbcTemplate : JdbcTemplate = new JdbcTemplate(platformTransactionManager.asInstanceOf[DataSourceTransactionManager].getDataSource)

  protected def jdbcTransactionalTemplate() : TransactionTemplate = new TransactionTemplate(platformTransactionManager)

  def withTransaction( callback:  => Unit ) = {
    jdbcTransactionalTemplate().execute(new TransactionCallbackWithoutResult {
      override def doInTransactionWithoutResult(transactionStatus: TransactionStatus) = {
        try {
          callback
          transactionStatus.flush()
        } catch {
          case e:Exception => {
            transactionStatus.setRollbackOnly()
            logger.error("Transaction Error: ", e)
          }
        }
      }
    })
  }
}
