package com.tez.app.rest.service;


import com.tez.app.rest.Repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    SeatRepo repo;

    public String setStatus(long seatId,String status) {
        System.out.println(status);
        repo.changeStatus(status, seatId);
        return "Seat status updated.";
    }
}
