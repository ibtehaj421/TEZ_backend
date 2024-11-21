package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends UserBase {

    @Column(nullable = false,length = 20)
    private String adminid;
    @Column(nullable = false,length = 5)
    private int orgID;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getadminID(){
        return adminid;
    }
    public int getOrgID(){
        return orgID;
    }

    public void setadminID(String adminID) {
        this.adminid = adminID;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }
}
