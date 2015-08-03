package com.codesimples.jpa.repositories

import javax.persistence.EntityManager

import com.codesimples.jpa.domain.User
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

class UserRepository(val entityManager:EntityManager) extends SimpleJpaRepository[User, String](classOf[User], entityManager) {

}
