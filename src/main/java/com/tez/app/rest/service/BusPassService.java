package com.tez.app.rest.service;


import com.tez.app.rest.Model.BusPass;
import com.tez.app.rest.Repo.BusPassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BusPassService {

    @Autowired
    BusPassRepo repo;


    public long generateNewPass(long id,long org){
        BusPass pass = FactoryService.createBusPass();
        pass.setUserId(id);
        pass.setOrgId(org);
        pass.setStatus("pending");
        pass.setIssued(LocalDate.now());
        BusPass saved = repo.save(pass);

        if(saved.getId() != 0){
            return saved.getId();
        }
        return 0;
    }


}
