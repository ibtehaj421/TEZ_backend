package com.tez.app.rest.service;

import com.tez.app.rest.DTO.OrgDTO;
import com.tez.app.rest.Model.Organization;
import com.tez.app.rest.Repo.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    @Autowired
    OrganizationRepo repo;


    public boolean registerOrganization(OrgDTO org) {
        if(org == null) {
            return false;
        }
        Organization add = FactoryService.createOrganization(org.type);
        add.setName(org.name);
        if(!repo.existsByName(add.getName())){
            repo.save(add);
            return true;
        }
       return false;
    }

    public OrgDTO orgLogin(OrgDTO org) {
        if(org == null) {
            return null;
        }
        if(repo.existsByName(org.name)){
            org.type = repo.getRole(org.name);
            return org;
        }
        return null;
    }
}
