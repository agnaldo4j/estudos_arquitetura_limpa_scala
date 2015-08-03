package com.codesimples.objectives.persistence.adapter.user

trait PersistenceAdapter {
  def openTransaction(): TransactionAdapter
}
