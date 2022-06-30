package com.ironhack.midtermproject.model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {


    public Admin() {
    }

    public Admin(String name, String password) {
        super(name, password);
    }
}