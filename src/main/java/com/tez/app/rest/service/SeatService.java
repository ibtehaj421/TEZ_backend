package com.tez.app.rest.service;


import com.tez.app.rest.Model.Seat;
import com.tez.app.rest.Repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    SeatRepo repo;

    public String setStatus(long busId,String status,int num) {
        //System.out.println(status);
        repo.changeStatus(status, busId,num);
        return "Seat status updated.";
    }

    public boolean checkStatus(long busid,int num) {
        String check = repo.findStatus(busid,num);
        if(check.equals("available")) {
            return true;
        }
        return false;
    }

    public List<Seat> getBusSeats(long bus) {
        return repo.findAllByBusID(bus);
    }

    //get list of seats for a specific bus,

}
