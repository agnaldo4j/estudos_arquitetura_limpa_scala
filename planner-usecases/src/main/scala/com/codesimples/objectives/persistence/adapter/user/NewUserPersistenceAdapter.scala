package com.codesimples.objectives.persistence.adapter.user

trait NewUserPersistenceAdapter extends PersistenceAdapter {
  def saveUser(map: Map[String, AnyRef]): Map[String, AnyRef]
}
