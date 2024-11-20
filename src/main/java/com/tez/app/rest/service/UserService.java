package com.tez.app.rest.service;

import com.tez.app.rest.Model.User;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Model.UserPrinicipal;
import com.tez.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public String registerUser(User user) {
        System.out.println(user);
        if(user == null){
            return "Cannot register user";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        String ret = "User " + user.getUserName() + " registered";
        return ret;
    }

    public String verify(UserBase user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(auth.isAuthenticated())
            return "User verified";
        else return "User not verified";
    }
}
