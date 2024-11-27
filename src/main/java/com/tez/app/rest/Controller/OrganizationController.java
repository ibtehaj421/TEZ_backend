package com.tez.app.rest.Controller;


import com.tez.app.rest.DTO.NameDTO;
import com.tez.app.rest.DTO.OrgDTO;
import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.Model.Organization;
import com.tez.app.rest.Repo.OrganizationRepo;
import com.tez.app.rest.service.OrgService;
import com.tez.app.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationController {


    @Autowired
    OrgService orgService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationRepo organizationRepo;

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

    @GetMapping(path = "org/admins/{org}")
    public List<UserDTO> getAllAdmins(@PathVariable("org") String org) {
        return orgService.getAdmins(org);
    }

    @GetMapping(path = "org/user/{email}")
    public long getUserID(@PathVariable("email") String email) {
        return userService.fetchID(email);
    }

    @GetMapping(path = "/fetch/all")
    public List<OrgDTO> getAllOrganizations() {
        return orgService.getAll();
    }

    @GetMapping(path = "org/id")
    public long getOrganizationID(@RequestBody NameDTO req) {
        return organizationRepo.fetchByName(req.name);
    }
}
