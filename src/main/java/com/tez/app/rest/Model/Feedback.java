package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "orgID",nullable = false)
    private long orgID;
    @Column(nullable = false,length = 255)
    private String content;
    @Column(nullable = false)
    private long userID;
}
