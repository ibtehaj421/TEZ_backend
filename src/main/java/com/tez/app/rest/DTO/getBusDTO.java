package com.tez.app.rest.DTO;

import com.tez.app.rest.Model.Seat;

import java.util.List;

public class getBusDTO {
    //uses the busDTO and seats objects in a list
    public BusDTO bus;
    public List<Seat> seats;

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
