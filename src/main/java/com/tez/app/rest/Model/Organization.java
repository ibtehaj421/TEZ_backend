package com.tez.app.rest.Model;


import com.tez.app.rest.Role;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(nullable = false,length = 255)
    protected String name;
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    protected Role role;
}
