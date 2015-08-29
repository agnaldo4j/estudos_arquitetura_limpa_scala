package com.codesimples.jdbc

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

trait JDBCTemplateBuilder {
  implicit val platformTransactionManager: PlatformTransactionManager

  protected def jdbcTemplate : JdbcTemplate = new JdbcTemplate(platformTransactionManager.asInstanceOf[DataSourceTransactionManager].getDataSource)

  protected def jdbcTransactionalTemplate() : TransactionTemplate = new TransactionTemplate(platformTransactionManager)
}
