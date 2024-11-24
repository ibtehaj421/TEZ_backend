package com.tez.app.rest.service;


import com.tez.app.rest.DTO.BusTicketDTO;
import com.tez.app.rest.Model.BusTicket;
import com.tez.app.rest.Repo.BusTicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusTicketService {

    @Autowired
    BusTicketRepo repo;

    public BusTicket getTicket(BusTicketDTO req) {
        BusTicket ticket = FactoryService.createTicket();
        ticket.setSeatID(req.seatID);
        ticket.setUserID(req.userId);
        ticket.setStatus("reserved");
        return repo.save(ticket);
    }

    public boolean setStatus(long ticketID,String status){
        int val = repo.updateStatus(ticketID, status);
        if(val > 0){
            return true;
        }
        return false;
    }
}
