package com.tez.app.rest.Controller;


import com.tez.app.rest.DTO.BusDTO;
import com.tez.app.rest.DTO.getBusDTO;
import com.tez.app.rest.service.BusService;
import com.tez.app.rest.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusController {

    @Autowired
    BusService busService;
    @Autowired
    DriverService driverService;

    @PostMapping(path = "/admin/bus/add")
    public String addBus(@RequestBody BusDTO bus){
        return busService.createBus(bus);
    }

    @GetMapping(path = "/admin/bus/get")
    public List<getBusDTO> getBus(@RequestParam("id") long id){
        //fetch the buses under an organization id.
        return busService.listBuses(id);
    }

    @PutMapping(path = "/admin/bus/driver/{id}")
    public String updateDriver(@PathVariable long id,@RequestBody long driverid) {
        return busService.setDriver(id, driverid);
    }

    @GetMapping(path = "/driver/bus/{id}") //return the bus object assigned to the driver.
    public getBusDTO getDriverBus(@PathVariable long id){

        return busService.getBus(id);
    }
}
