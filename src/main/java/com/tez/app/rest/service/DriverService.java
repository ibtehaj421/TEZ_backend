package com.tez.app.rest.service;


import com.tez.app.rest.DTO.DriverRegDTO;
import com.tez.app.rest.Model.Admin;
import com.tez.app.rest.Model.Driver;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    UserRepo userRepo;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MailingService mailingService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public String registerUser(DriverRegDTO req,long org) throws Exception {
        if(req == null){
            return "Cannot register user";
        }
        if(!userRepo.existsByemail(req.email)) {
            Driver driver = FactoryService.createDriver();
            driver.setPassword(encoder.encode(req.password));
            driver.setEmail(req.email);
            driver.setName(req.name);
            driver.setLevel(Integer.parseInt(req.level));
            driver.setOrgID(org);
            userRepo.save(driver);
            mailingService.sendAdminMail(driver.getUserName(), driver.getEmail(), req.password);
            String ret = "User " + driver.getUserName() + " registered";
            return ret;
        }
        return "Cannot register user.Already exists.";
    }

    public String verify(UserBase user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(auth.isAuthenticated())
            return "User verified";
        else return "User not verified";
    }
}
