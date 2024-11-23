package com.tez.app.rest.service;


import com.tez.app.rest.DTO.BusDTO;
import com.tez.app.rest.DTO.getBusDTO;
import com.tez.app.rest.Model.Bus;
import com.tez.app.rest.Model.Seat;
import com.tez.app.rest.Repo.BusRepo;
import com.tez.app.rest.Repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {

    @Autowired
    BusRepo repo;

    @Autowired
    SeatRepo seatRepo;

    public String createBus(BusDTO req){
        if(req == null){
            return "Empty object";
        }
        Bus add = FactoryService.createBus();
        //List<Seat> seats = new ArrayList<Seat>();
        if(!repo.existsByLicPlateNumber(req.licNum)){
            //create the bus and assign the seats.
            add.setDetails(req.model,req.licNum,req.capacity,req.orgID);
            repo.save(add);
            //add seats through a loop.
            for(int i = 1; i<= req.capacity;i++){
                Seat seat = FactoryService.createSeat();
                seat.setDetails(add.getId(),i);
                seatRepo.save(seat);
            }

            //if no errors occured
            return "Bus created";
        }
        return "License number already exists..";
    }

    public List<getBusDTO> listBuses(long id) {
        List<getBusDTO> list = new ArrayList<>();
        List<Bus> buses = repo.findByID(id);
        for(int i = 0; i < buses.size(); i++){
            list.add(new getBusDTO());
            list.get(i).bus = new BusDTO();
            list.get(i).bus.extractBus(buses.get(i));
            //add all seat details by searching for the list of seats under a bus.
            List<Seat> seatList = seatRepo.findByBusID(buses.get(i).getId());
            list.get(i).seats = new ArrayList<>();
            list.get(i).seats.addAll(seatList);
        }
        return list;
    }

    public getBusDTO getBus(long id) {
        Bus bus = repo.findByDriverID(id);
        getBusDTO dto = new getBusDTO();
        dto.bus = new BusDTO();
        dto.bus.extractBus(bus);
        List<Seat> seatList = seatRepo.findByBusID(bus.getId());
        dto.seats = new ArrayList<>();
        dto.seats.addAll(seatList);
        return dto;
    }
    public String setDriver(long busid,long driverid){
        //update the driver id value in the bus.
        int i = repo.updateDriver(driverid,busid);
        if(i == 0){
            return "Driver not found/Unsuccessful";
        }
        return "Driver updated.";
    }
}
