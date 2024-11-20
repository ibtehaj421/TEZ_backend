package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "origin", length = 50)
    private String origin;
    @Column(name = "passes",length = 50)
    private String passes;
    @Column(name = "destination", length = 50)
    private String destination;
    @Column(name = "schedule", length = 50)
    private String schedule;
    @Column(name = "discription",length = 255)
    private String discription;
}
