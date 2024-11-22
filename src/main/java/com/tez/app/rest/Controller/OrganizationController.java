package com.tez.app.rest.Controller;


import com.tez.app.rest.DTO.OrgDTO;
import com.tez.app.rest.Model.Organization;
import com.tez.app.rest.Repo.OrganizationRepo;
import com.tez.app.rest.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationController {


    @Autowired
    OrgService orgService;

    @PostMapping(path = "/org/add")
    public String addOrganization(@RequestBody OrgDTO org) {
        if(orgService.registerOrganization(org)){
            return "success";
        }
        else {
            return "failed to add organization or organization already exists";
        }
    }

    @PostMapping(path = "org/login")
    public OrgDTO loginOrganization(@RequestBody OrgDTO org) {
        return orgService.orgLogin(org);
    }
}
