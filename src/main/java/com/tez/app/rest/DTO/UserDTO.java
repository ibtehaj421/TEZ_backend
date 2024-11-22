package com.tez.app.rest.DTO;


public class UserDTO {
    //data transfer object for user after login.
    public long id;
    public String name;
    public String email;
    public String password;
    public int passLen;
    public String role; //the admins will get their roles from the organization admin.
    public int level; //organization id sent back
    public String admID;
    public String orgName;
}
