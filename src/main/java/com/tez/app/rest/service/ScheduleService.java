package com.tez.app.rest.service;


import com.tez.app.rest.DTO.ScheduleDTO;
import com.tez.app.rest.Model.Schedule;
import com.tez.app.rest.Repo.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepo repo;


    public String addSchedule(ScheduleDTO req) {
        if(req == null) {
            return null;
        }
        Schedule schedule = FactoryService.createSchedule();
        schedule.setTime(req.timeOfDay);
        schedule.setOrgID(req.orgID);
        schedule.setFromAndTo(req.from_and_to);
        if(repo.checkForDuplicate(req.from_and_to, req.orgID, req.timeOfDay)) {
            return "duplicate schedule. remove or edit existing or add new";
        }
        repo.save(schedule);
        return "Success.";
    }

    public String updateTimings(int id, String fromTo) {
        if(id < 0) {
            return "Invalid schedule id.";
        }

        //check for what time of day to be applied.
        String timeval = "";
        Pattern pattern = Pattern.compile("(\\d{2}:\\d{2} [ap]m) to (\\d{2}:\\d{2} [ap]m)");
        Matcher matcher = pattern.matcher(fromTo);

        if (matcher.find()) {
            String startTimeString = matcher.group(1);
            String endTimeString = matcher.group(2);

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime startTime = LocalTime.parse(fromTo.split(" to ")[0], formatter);
                LocalTime endTime = LocalTime.parse(fromTo.split(" to ")[1], formatter);

                if (startTime.isBefore(LocalTime.of(12, 0)) && endTime.isBefore(LocalTime.of(12, 0))) {
                    timeval = "morning";
                } else if (startTime.isAfter(LocalTime.of(11, 59)) && endTime.isBefore(LocalTime.of(17, 0))) {
                    timeval = "afternoon";
                } else if (startTime.isAfter(LocalTime.of(16, 59)) && endTime.isBefore(LocalTime.of(20, 0))) {
                    timeval = "evening";
                } else {
                    timeval = "night";
                }
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing time: " + e.getMessage());
                return "Invalid time range";
            }


            // Determine the time of day based on the start and end times

        } else {
            return "Cannot set Timings. Format mismatch.";
        }
        repo.updateTimings(fromTo, id);
        repo.updateTimeOfDay(timeval, id);
        return "Success.";
    }

    public List<ScheduleDTO> getAllSchedules(long id) {
            List<Schedule> schedules = repo.findByOrgID(id);
            List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
            for (int i=0; i<schedules.size(); i++) {
                scheduleDTOS.add(FactoryService.createScheduleDTO());
                scheduleDTOS.get(i).setDetails(schedules.get(i).getId(),schedules.get(i).getFromAndTo(),schedules.get(i).getOrgID(),schedules.get(i).getTime());
            }
            return scheduleDTOS;
    }
}
