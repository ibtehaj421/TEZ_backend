package com.tez.app.rest.service;


import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.Model.Admin;
import com.tez.app.rest.Model.Organization;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Repo.OrganizationRepo;
import com.tez.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    OrganizationRepo orgRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private MailingService mailingService;


    public String registerUser(UserDTO req) throws Exception {
        //System.out.println(admin);
        if(req == null){
            return "Cannot register user";
        }
        Admin admin = new Admin();
        admin.setName(req.name);
        admin.setEmail(req.email);
        System.out.println(admin.getEmail().length());
        admin.setadminID(generateAdminID(req.orgName));
        admin.setOrgID((int)getOrgID(req.orgName));
        admin.setPassword(encoder.encode(req.password));
        if(!userRepo.existsByemail(req.email)){
            userRepo.save(admin);
            mailingService.sendAdminMail(admin.getUserName(),admin.getEmail(),req.password);
            return "User " + admin.getUserName() + " registered";
        }
        return "User cannot be created.May already exist.";
    }

    private String generateAdminID(String name) {
        Organization org = orgRepo.findByName(name);
        return org.getName() + "67";
    }

    private long getOrgID(String name) {
        Organization org = orgRepo.findByName(name);
        return org.getId();
    }

    public String verify(UserBase user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(auth.isAuthenticated())
            return "User verified";
        else return "User not verified";
    }
}
