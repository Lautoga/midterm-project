package com.ironhack.midtermproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(long id, String name, String hashedKey) {
        this.id = id;
        this.name = name;
        this.hashedKey = hashedKey;
    }

    public long id() {
        return id;
    }

    public void id(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
