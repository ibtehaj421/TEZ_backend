package com.tez.app.rest.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 50)
    private String ModelName;
    @Column(unique = true, nullable = false)
    private String licPlateNumber;
    @Column(nullable = false,length = 10)
    private int capacity;
    @Column(nullable = true,length = 10)
    private long driverID;
}
