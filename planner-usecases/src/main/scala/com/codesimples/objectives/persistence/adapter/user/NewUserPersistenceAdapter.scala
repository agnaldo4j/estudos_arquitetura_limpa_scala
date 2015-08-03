package com.codesimples.objectives.persistence.adapter.user

trait NewUserPersistenceAdapter extends PersistenceAdapter {
  def save(map: Map[String, AnyRef]): Map[String, AnyRef]
}
