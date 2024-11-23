package com.tez.app.rest.service;

import com.resend.core.exception.ResendException;
import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.Model.User;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Model.UserPrinicipal;
import com.tez.app.rest.Repo.UserRepo;
import com.tez.app.rest.Role;
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

    @Autowired
    JWTService jwtService;

    @Autowired
    MailingService mailingService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public boolean registerUser(UserDTO req) throws Exception {
        //System.out.println(user);
        if(req == null){
            return false;
        }
        User user = FactoryService.createStudent();
        //save user credentials.
        user.setName(req.name);
        user.setEmail(req.email);
        user.setPassword(encoder.encode(req.password));
        user.setPass(0);
        Role role = getRole(req.role);
        user.setRole(role);
        if(!userRepo.existsByemail(user.getEmail())){
            userRepo.save(user);
            mailingService.sendMail(user.getEmail(), user.getUserName());
            //String ret = "User " + user.getUserName() + " registered";
            return true;
        }
        return false;
    }

    private Role getRole(String role) {
        if(role.equals("STUDENT")){
            return Role.STUDENT;
        }
        return Role.BASIC;
    }

    public String verify(UserBase user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(auth.isAuthenticated())
            return jwtService.generateToken(user.getEmail());
        else return null;
    }
    public UserDTO setuserDTO(UserBase user) {
        UserDTO userDTO = FactoryService.createUserDTO();
        userDTO.name = user.getUserName();
        userDTO.email = user.getEmail();
        return userDTO;
    }
}
