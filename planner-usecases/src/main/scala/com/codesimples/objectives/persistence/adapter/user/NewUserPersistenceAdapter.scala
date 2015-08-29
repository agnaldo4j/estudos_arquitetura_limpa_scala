package com.codesimples.objectives.persistence.adapter.user

trait NewUserPersistenceAdapter {
  def saveUser(map: Map[String, AnyRef])
  def findUserById(id: String): Map[String, AnyRef]
}
