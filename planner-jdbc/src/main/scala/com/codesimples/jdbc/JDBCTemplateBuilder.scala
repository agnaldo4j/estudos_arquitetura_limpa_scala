package com.codesimples.jdbc

import javax.sql.DataSource

import org.springframework.jdbc.core.JdbcTemplate

trait JDBCTemplateBuilder {
  implicit val dataSource:DataSource

  protected def jdbcTemplate : JdbcTemplate = new JdbcTemplate(dataSource)
}
