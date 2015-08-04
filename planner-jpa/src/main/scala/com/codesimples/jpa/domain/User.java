package com.codesimples.jpa.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    public User() {}

    public User(Map<String, Object> state) {}

    public Map<String, Object> toMap() {
        return new HashMap<String, Object>();
    }
}
