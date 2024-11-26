package com.tez.app.rest.service;

import com.tez.app.rest.DTO.OrgDTO;
import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.Model.Admin;
import com.tez.app.rest.Model.Organization;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Repo.OrganizationRepo;
import com.tez.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrgService {

    @Autowired
    OrganizationRepo repo;
    @Autowired
    private UserRepo userRepo;


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

    public List<UserDTO> getAdmins(String org) {
        long fetch = repo.fetchId(org);
        List<Admin> admins = userRepo.getAdminsByOrg(fetch);
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for(int i = 0; i < admins.size(); i++){
            userDTOS.add(FactoryService.createUserDTO());
            userDTOS.get(i).id = admins.get(i).getId();
            userDTOS.get(i).name = admins.get(i).getUserName();
            userDTOS.get(i).email = admins.get(i).getEmail();
            userDTOS.get(i).password = admins.get(i).getPassword();
            userDTOS.get(i).orgName = org;
        }
        return userDTOS;
    }
}
