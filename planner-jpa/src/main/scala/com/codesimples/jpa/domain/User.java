package com.codesimples.jpa.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User {
    @Id
    //@GeneratedValue(generator = "uuid2")
    //@GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    public String id;

    @Column(name = "persistence_type", columnDefinition = "CHAR(10)")
    public String persistenceType;

    public User() {}

    public User(Map<String, Object> state) {
        this.id = (String)state.get("id");
        this.persistenceType = (String)state.get("persistenceType");
    }
}