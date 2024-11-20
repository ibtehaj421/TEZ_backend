package com.tez.app.rest.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver extends UserBase {
    @Column(nullable = false,length = 5)
    private int level; //specifies whether the driver is new or old based on experience ranging 1 to 5;

    public String getUserName() {
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
}
