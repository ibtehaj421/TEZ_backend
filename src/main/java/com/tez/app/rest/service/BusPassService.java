package com.tez.app.rest.service;


import com.tez.app.rest.DTO.PassDTO;
import com.tez.app.rest.Model.BusPass;
import com.tez.app.rest.Repo.BusPassRepo;
import com.tez.app.rest.Repo.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BusPassService {

    @Autowired
    BusPassRepo repo;
    @Autowired
    private OrganizationRepo organizationRepo;


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


    public void changeToPaid(long id) {
        repo.changeToPaid(id);
    }

    public PassDTO getPass(long id) {
        BusPass pass = repo.getByUserId(id);
        if(pass != null){
            PassDTO passDTO = FactoryService.createPassDTO();
            passDTO.id = pass.getId();
            passDTO.status = pass.getStatus();
            passDTO.orgId = organizationRepo.fetchName(pass.getOrgId());
            passDTO.userId = String.valueOf(pass.getUserId());
            passDTO.issued = pass.getIssued().toString();
            return passDTO;
        }
        return null;
    }
}
