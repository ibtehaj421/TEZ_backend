package com.tez.app.rest.Model;

import com.tez.app.rest.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends UserBase {

    @Column(unique = false,nullable = true,length = 30)
    private long pass;
    @Enumerated(EnumType.STRING)
    private Role role;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



}
