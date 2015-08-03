package com.codesimples.jpa.adapter.user

import javax.persistence.EntityTransaction

import com.codesimples.objectives.persistence.adapter.user.TransactionAdapter

class TransactionAdapterJPA(val entityTransaction: EntityTransaction) extends TransactionAdapter {
  override def commit() = {
    entityTransaction.commit()
  }

  override def rollback() = {
    entityTransaction.rollback()
  }
}
