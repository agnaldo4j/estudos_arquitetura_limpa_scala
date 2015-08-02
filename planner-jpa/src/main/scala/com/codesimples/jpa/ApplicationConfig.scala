package com.codesimples.jpa

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

import com.typesafe.config.Config
import org.springframework.context.annotation.{Configuration, Bean}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.orm.jpa.{LocalContainerEntityManagerFactoryBean, JpaTransactionManager}
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
class ApplicationConfig {

  @Bean
  def dataSource(config:Config): DataSource = {
    val dataSourceOrigin: DriverManagerDataSource = new DriverManagerDataSource()
    dataSourceOrigin.setDriverClassName( config.getString("driver") )
    dataSourceOrigin.setUrl( config.getString("url") )
    dataSourceOrigin.setUsername( config.getString("username") )
    dataSourceOrigin.setPassword( config.getString("password") )
    return dataSourceOrigin
  }

  @Bean
  def entityManagerFactory(config:Config): EntityManagerFactory = {
    val vendorAdapter:HibernateJpaVendorAdapter  = new HibernateJpaVendorAdapter()
    vendorAdapter.setGenerateDdl(true);

    val factory:LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean()
    factory.setJpaVendorAdapter(vendorAdapter)
    factory.setPackagesToScan("com.codesimples.jpa.domain")
    factory.setDataSource(dataSource(config))
    factory.afterPropertiesSet()

    return factory.getObject()
  }

  @Bean
  def transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager = {
    val txManager:JpaTransactionManager = new JpaTransactionManager()
    txManager.setEntityManagerFactory(entityManagerFactory)
    return txManager
  }
}
