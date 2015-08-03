package com.codesimples.objectives.persistence.adapter.user

trait TransactionAdapter {
  def commit()

  def rollback()
}