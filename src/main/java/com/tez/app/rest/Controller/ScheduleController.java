package com.tez.app.rest.Controller;

import com.tez.app.rest.DTO.ScheduleDTO;
import com.tez.app.rest.Model.Schedule;
import com.tez.app.rest.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping(path = "/admin/schedule/add")
    public String addSchedule(@RequestBody ScheduleDTO schedule) {
        return scheduleService.addSchedule(schedule);
    }

    @PutMapping(path = "/admin/schedule/timings/{id}/{from_to}")
    public String setScheduleTimings(@PathVariable int id, @PathVariable String from_to) {
        return scheduleService.updateTimings(id,from_to);
    }

    @GetMapping(path = "/public/schedule/get/{id}")
    public List<ScheduleDTO> getOrgSchedules(@PathVariable long id) {
        //feed org id into the path and returns a list of the schedules.
        return scheduleService.getAllSchedules(id);
    }
}
