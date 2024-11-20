package com.tez.app.rest.Component;

import com.tez.app.rest.Model.User;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Repo.AdminRepo;
import com.tez.app.rest.Repo.DriverRepo;
import com.tez.app.rest.Repo.UserRepo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

//public class CustomAuthProvider implements AuthenticationProvider {
//
//    private final UserRepo userRepo;
//    private final AdminRepo adminRepo;
//    private final DriverRepo driverRepo;
//
//    private final PasswordEncoder passwordEncoder;
//
//    public CustomAuthProvider(UserRepo uRepo,PasswordEncoder passwordEncoder) {
//        this.userRepo = uRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public CustomAuthProvider(AdminRepo uRepo,PasswordEncoder passwordEncoder) {
//        this.adminRepo = uRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public CustomAuthProvider(DriverRepo uRepo,PasswordEncoder passwordEncoder) {
//        this.driverRepo = uRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws BadCredentialsException {
//        String email = authentication.getName();  // Get email from token (username)
//        String password = (String) authentication.getCredentials();  // Get password from token
//
//        // Fetch user from the repository based on email
//        UserBase user = userRepo.findByemail(email);
//
//        // Check if user exists and validate password
//        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
//            throw new BadCredentialsException("Invalid email or password");
//        }
//
//        // If valid, return a fully authenticated token (you can add authorities/roles here)
//        return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//}
