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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRouteID() {
        return routeID;
    }

    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }

    public String getLocationURL() {
        return locationURL;
    }

    public void setLocationURL(String locationURL) {
        this.locationURL = locationURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getSeatID() {
        return seatID;
    }

    public void setSeatID(long seatID) {
        this.seatID = seatID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
