package com.tez.app.rest.service;


import com.tez.app.rest.Model.Admin;
import com.tez.app.rest.Model.UserBase;
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

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public String registerUser(Admin admin) {
        System.out.println(admin);
        if(admin == null){
            return "Cannot register user";
        }
        admin.setPassword(encoder.encode(admin.getPassword()));
        userRepo.save(admin);
        String ret = "User " + admin.getUserName() + " registered";
        return ret;
    }

    public String verify(UserBase user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(auth.isAuthenticated())
            return "User verified";
        else return "User not verified";
    }
}
