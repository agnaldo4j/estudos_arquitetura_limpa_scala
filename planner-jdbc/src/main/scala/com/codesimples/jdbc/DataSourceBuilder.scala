package com.codesimples.jdbc

import javax.sql.DataSource

import com.typesafe.config.Config
import org.springframework.jdbc.datasource.DriverManagerDataSource

object DataSourceBuilder {

  def buildDataSourceWith(config:Config): DataSource = {
    val dataSourceOrigin: DriverManagerDataSource = new DriverManagerDataSource();
    dataSourceOrigin.setDriverClassName( config.getString("driver") );
    dataSourceOrigin.setUrl( config.getString("url") );
    dataSourceOrigin.setUsername( config.getString("username") );
    dataSourceOrigin.setPassword( config.getString("password") );
    dataSourceOrigin
  }
}