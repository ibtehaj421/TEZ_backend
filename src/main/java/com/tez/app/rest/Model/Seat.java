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

    public void setDetails( long busID, int seatNumber) {
        //this.id = id;
        this.busID = busID;
        this.seatNumber = seatNumber;
        this.status = "available";
    }
    public long getId() {
        return id;
    }

    public long getBusID() {
        return busID;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBusID(long busID) {
        this.busID = busID;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
