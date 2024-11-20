package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends UserBase {

    @Column(nullable = false,length = 20)
    private String adminID;
    @Column(nullable = false,length = 5)
    private int orgID;

    public String getUserName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
}
