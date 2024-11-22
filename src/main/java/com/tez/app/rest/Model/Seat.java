package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long busID;
    @Column(nullable = false)
    private int seatNumber;
    @Column(nullable = false)
    private String status; //occupied , available.
}
