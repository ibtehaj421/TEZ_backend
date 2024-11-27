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

    @PatchMapping(path = "/driver/seat/status/{id}/{status}/{num}")
    public String updateSeatStatus(@PathVariable long id, @PathVariable String status,@PathVariable int num) {
            return seatService.setStatus(id, status,num);
    }

    @GetMapping(path = "public/bus/seat/{bus}")
    public List<Seat> getSeatsByBus(@PathVariable long bus) {
        return seatService.getBusSeats(bus);
    }
}
