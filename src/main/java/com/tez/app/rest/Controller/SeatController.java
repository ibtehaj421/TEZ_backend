package com.tez.app.rest.Controller;


import com.tez.app.rest.Model.Seat;
import com.tez.app.rest.Repo.SeatRepo;
import com.tez.app.rest.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController {

    @Autowired
    SeatService seatService;

    @PutMapping(path = "/driver/seat/status/{id}/{status}")
    public String updateSeatStatus(@PathVariable long id, @PathVariable String status) {
            return seatService.setStatus(id, status);
    }

    @GetMapping(path = "public/bus/seat/{bus}")
    public List<Seat> getSeatsByBus(@PathVariable long bus) {
        return seatService.getBusSeats(bus);
    }
}
