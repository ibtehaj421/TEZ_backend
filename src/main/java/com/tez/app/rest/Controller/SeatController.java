package com.tez.app.rest.Controller;


import com.tez.app.rest.Repo.SeatRepo;
import com.tez.app.rest.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatController {

    @Autowired
    SeatService seatService;

    @PutMapping(path = "/driver/seat/status/{id}/{status}")
    public String updateSeatStatus(@PathVariable long id, @PathVariable String status) {
            return seatService.setStatus(id, status);
    }
}
