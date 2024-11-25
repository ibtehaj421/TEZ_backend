package com.tez.app.rest.DTO;

import com.tez.app.rest.Role;

public class getLoginDTO {
    public long id;
    public String name;
    public String email;
    public String password;
    public Role role; //type of the user.
    public long orgid;
    public String jwt;
    public int level;

    public void setUser(long id, String name, String email, String password, Role role, String jwt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.jwt = jwt;
    }

    public void setAdmin(long id, String name, String email, String password, String jwt, long orgid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        //this.role = role;
        this.jwt = jwt;
        this.orgid = orgid;
    }
}
