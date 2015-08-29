package com.codesimples.objectives.usecases.user

import com.codesimples.objectives.persistence.adapter.user.NewUserPersistenceAdapter

class NewUserUsecase(persistenceAdapter: NewUserPersistenceAdapter) {
  def saveNewUser(user:Map[String, AnyRef]): Map[String, AnyRef] = {
    persistenceAdapter.saveUser(user)
  }
}
