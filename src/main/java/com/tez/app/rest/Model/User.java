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
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }


}
