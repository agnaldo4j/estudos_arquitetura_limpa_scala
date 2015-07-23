package com.codesimples.objectives.persistence.adapter.user

trait NewUserPersistenceAdapter {
  def save(map: Map[String, AnyRef]): Map[String, AnyRef]
}
