package com.tez.app.rest.Model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long userID;
    @Column(name = "issued")
    private LocalDate issued;
    @Column(nullable = false)
    private double amount;
}
