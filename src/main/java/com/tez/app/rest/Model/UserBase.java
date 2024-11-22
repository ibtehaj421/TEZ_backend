package com.tez.app.rest.Model;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(unique = false, nullable = false,length = 50)
    protected String name;
    @Column(unique = true,nullable = false,length = 50)
    protected String email;
    @Column(nullable = false)
    protected String password;

    public abstract String getUserName();
    public abstract String getPassword();
    public abstract long getId();
    public abstract void setName(String name);
    public abstract void setEmail(String email);
    public abstract String getEmail();

    public abstract void setPassword(String password);

}
