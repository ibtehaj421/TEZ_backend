package com.tez.app.rest.service;


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

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public String registerUser(Driver driver) {
        if(driver == null){
            return "Cannot register user";
        }
        driver.setPassword(encoder.encode(driver.getPassword()));
        userRepo.save(driver);
        String ret = "User " + driver.getUserName() + " registered";
        return ret;
    }

    public String verify(UserBase user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(auth.isAuthenticated())
            return "User verified";
        else return "User not verified";
    }
}
