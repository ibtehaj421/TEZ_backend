package com.tez.app.rest.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "bus_ticket")
public class BusTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private long routeID;
    @Column(name ="liveLocation",nullable = true)
    private String locationURL;
    @Column(name = "status")
    private String status; //reserved , canceled , paid.
    @Column(nullable = false)
    private long seatID;
    @Column(nullable = false)
    private long userID; //the one who made the reservation.
}
