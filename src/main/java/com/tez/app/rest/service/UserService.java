package com.tez.app.rest.service;

import com.resend.core.exception.ResendException;
import com.tez.app.rest.DTO.BusTicketDTO;
import com.tez.app.rest.DTO.PaymentDTO;
import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.Model.*;
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

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Autowired
     BusPassService passService;

    @Autowired
    MailingService mailingService;

    @Autowired
    BusTicketService busTicketService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private SeatService seatService;


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
    public String generatePass(long id, long org) throws Exception {
            if(id < 0 || org < 0){
                return "Invalid request.";
            }
            long val = passService.generateNewPass(id,org);
            if(val > 0){
                UserBase fetch = userRepo.findByid(id);

                mailingService.sendPassGenMail(fetch.getEmail(),fetch.getUserName(),val, LocalDate.now());
                return "Successfully generated pass payment pending.";
            }
            return "Error generating pass.";
    }

    public String payForPass(PaymentDTO req) throws Exception {
        //for now there are no checks on the amount a user has it will only process the payment.
        if(req == null){
            return "Invalid request.";
        }
        Payment payment = paymentService.makePassPayment(req);
        if(payment == null){
            return "Invalid request.Could not be processed";
        }
        UserBase fetch = userRepo.findByid(req.userid);
        mailingService.paymentEmail(fetch.getEmail(), fetch.getUserName(),payment);

        return "Pass payment successful.";

    }

    public String reserveSeat(BusTicketDTO req) throws Exception {
        if(req == null){
            return "Invalid request.";
        }
        //check if seat is reserved or not.
        if(!seatService.checkStatus(req.seatID)){
            return "The seat cannot be reserved as it is already occupied.";
        }
        BusTicket val =  busTicketService.getTicket(req);
        if(val.getId() > 0){
            //proceed with sending email prompting to pay.
            //this means a seat has been reserved.
            UserBase fetch = userRepo.findByid(val.getUserID());
            seatService.setStatus(req.seatID, "occupied");
            mailingService.sendReservationMail(fetch.getEmail(),fetch.getUserName(),val);
            return "Successfully reserved seat payment pending.";
        }
        return "Cannot be processed at this time.";
    }

    public String payForSeat(PaymentDTO req,long seat,long ticket) throws Exception {
        if(req == null){
            return "Invalid request.";
        }
        Payment payment = paymentService.makeSeatPayment(req,seat);
        if(payment == null){
            return "Invalid request.Could not be processed";
        }
        UserBase fetch = userRepo.findByid(req.userid);
        mailingService.paymentEmail(fetch.getEmail(), fetch.getUserName(),payment);
        busTicketService.setStatus(ticket,"paid");
        return "Ticket payment successful.";

    }
}
