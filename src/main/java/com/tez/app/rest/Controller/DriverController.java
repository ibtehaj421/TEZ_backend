package com.tez.app.rest.Controller;


import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    //@Autowired

    @GetMapping(path = "/get/drivers/{org}")
    public List<UserDTO> getDrivers(@PathVariable long org){
        return driverService.getDrivers(org);
    }
}
