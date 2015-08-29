package com.codesimples.jpa

import javax.persistence.EntityManager

import com.typesafe.scalalogging.Logger

trait JPATemplate {
  implicit val logger: Logger
  implicit val entityManager: EntityManager

  def withTransaction( callback:  => Unit ) = {
    val transaction = entityManager.getTransaction
    transaction.begin()
    try {
      callback
      transaction.commit()
    } catch {
      case e: Exception => {
        if(transaction.isActive) transaction.rollback()
        logger.error("Transaction Error: ", e)
      }
    }
  }
}
