package com.tez.app.rest.Model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bus_pass")
public class BusPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long userId;
    @Column(nullable = true,length = 10)
    private String status;
    @Column(name = "issued")
    private LocalDate issued;

}
