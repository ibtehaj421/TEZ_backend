package com.tez.app.rest.Controller;


import com.tez.app.rest.DTO.FeedbackDTO;
import com.tez.app.rest.DTO.ScheduleDTO;
import com.tez.app.rest.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedbackController {

    @Autowired
    FeedbackService service;

    @PostMapping(path = "/submit/feedback")
    public String submit(@RequestBody FeedbackDTO req){
        return service.addFeedback(req);
    }

    @PostMapping(path = "/submit/user/feedback")
    public String submitUserFeedback(@RequestBody FeedbackDTO req){
        return service.addFeedbackUser(req);
    }
    @GetMapping(path ="/feedback/view/{org}")
    public List<FeedbackDTO> getFeedback(@PathVariable long org){
        return service.getOrgFeedback(org);
    }
}
