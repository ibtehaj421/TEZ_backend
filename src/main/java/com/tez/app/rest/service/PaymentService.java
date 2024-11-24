package com.tez.app.rest.service;


import com.tez.app.rest.DTO.PaymentDTO;
import com.tez.app.rest.Model.Payment;
import com.tez.app.rest.Repo.PaymentRepo;
import com.tez.app.rest.Repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo repo;

    @Autowired
    SeatRepo seatRepo;

    public Payment makePassPayment(PaymentDTO req){
        //process the payment for the pass and then update its status to paid.
        Payment payment = FactoryService.createPayment();
        payment.setUserID(req.userid);
        payment.setOrgID(req.orgid);
        payment.setAmount(req.amount);
        payment.setIssued(LocalDate.now());
        //but first check if the user even hass that amount..will implement later.
        Payment test = repo.save(payment);
        if(test.getId() > 0){
            return test;
        }
        return null;
    }

    public Payment makeSeatPayment(PaymentDTO req,long seatID) {
        Payment payment = FactoryService.createPayment();
        payment.setUserID(req.userid);
        payment.setOrgID(seatRepo.fetchOrgID(seatID));
        payment.setAmount(req.amount);
        payment.setIssued(LocalDate.now());
        Payment test = repo.save(payment);
        if(test.getId() > 0){
            return test;
        }
        return null;
    }
}
