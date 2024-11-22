package com.tez.app.rest.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver extends UserBase {
    @Column(nullable = false,length = 5)
    private int level; //specifies whether the driver is new or old based on experience ranging 1 to 5;
    @Column(nullable = true,length = 5)
    private long orgID; //to see which org the driver belongs to.
    public String getUserName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword(){
        return password;
    }

    @Override
    public long getId() {
        return 0;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String encode) {
        password = encode;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
