package com.tez.app.rest.service;


import com.tez.app.rest.DTO.FeedbackDTO;
import com.tez.app.rest.Model.Feedback;
import com.tez.app.rest.Repo.FeedbackRepo;
import com.tez.app.rest.Repo.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepo repo;
    @Autowired
    private OrganizationRepo organizationRepo;


    public String addFeedback(FeedbackDTO req) {
        Feedback feedback = FactoryService.createFeedback();
        if(req == null) {
            return "Invalid request";
        }
        feedback.setUserID(Long.parseLong(req.userId));
        feedback.setOrgID(Long.parseLong(req.orgid));
        feedback.setContent(req.content);
        repo.save(feedback);
        return "Feedback saved";
    }

    public List<FeedbackDTO> getOrgFeedback(long org) {
        //long fetch = organizationRepo.fetchId(org);
        List<Feedback> feedbacks = repo.findByOrgID(org);
        List<FeedbackDTO> feedbackDTOS = new ArrayList<FeedbackDTO>();
        for(int i = 0; i < feedbacks.size(); i++) {
            feedbackDTOS.add(FactoryService.createFeedbackDTO());
            feedbackDTOS.get(i).id = feedbacks.get(i).getId();
            feedbackDTOS.get(i).userId = String.valueOf(feedbacks.get(i).getUserID());
            feedbackDTOS.get(i).orgid = String.valueOf(feedbacks.get(i).getOrgID());
            feedbackDTOS.get(i).content = feedbacks.get(i).getContent();
        }
        return feedbackDTOS;
    }

    public String addFeedbackUser(FeedbackDTO req) {
        Feedback feedback = FactoryService.createFeedback();
        if(req == null) {
            return "Invalid request";
        }
        long org = organizationRepo.fetchByName(req.orgid);
        feedback.setOrgID(org);
        feedback.setContent(req.content);
        feedback.setUserID(Long.parseLong(req.userId));
        repo.save(feedback);
        return "Feedback saved";
    }
}
