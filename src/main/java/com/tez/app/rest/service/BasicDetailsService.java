package com.tez.app.rest.service;


import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Model.UserPrinicipal;
import com.tez.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BasicDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserBase user = userRepo.findByemail(email);
               // .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(user == null) {
            throw new UsernameNotFoundException("The user was not found "+email);
        }
        //System.out.println(user.getUserName());
        return new UserPrinicipal(user);
    }
}
