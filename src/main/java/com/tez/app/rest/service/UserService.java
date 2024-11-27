package com.tez.app.rest.service;

import com.resend.core.exception.ResendException;
import com.tez.app.rest.DTO.BusTicketDTO;
import com.tez.app.rest.DTO.PaymentDTO;
import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.DTO.getLoginDTO;
import com.tez.app.rest.Model.*;
import com.tez.app.rest.Repo.OrganizationRepo;
import com.tez.app.rest.Repo.SeatRepo;
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
    @Autowired
    private SeatRepo seatRepo;
    @Autowired
    private OrganizationRepo organizationRepo;


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

    public getLoginDTO verify(UserBase user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(auth.isAuthenticated()) {
            getLoginDTO userdeets = FactoryService.getLoginDTO();
            long fetchID = userRepo.findIDByemail(user.getEmail());
            User retUser = userRepo.findUserByid(fetchID);
            if(retUser != null){
                //the user is a basic user.
                userdeets.setUser(userRepo.findIDByemail(retUser.getEmail()),retUser.getUserName(),retUser.getEmail(),user.getPassword(),retUser.getRole(),jwtService.generateToken(user.getEmail()));
                return userdeets;
            }
            //check now whether the value could be an admin.
            Admin retAdmin = userRepo.findAdminByid(fetchID);
            if(retAdmin != null){
                //the user was an admin
                userdeets.setAdmin(userRepo.findIDByemail(retAdmin.getEmail()),retAdmin.getUserName(),retAdmin.getEmail(),user.getPassword(),jwtService.generateToken(user.getEmail()),retAdmin.getOrgID());
                Role role = userRepo.findRolebyid(fetchID);
                userdeets.role = role;
                return userdeets;
            }
            Driver retDriver = userRepo.findDriverByid(fetchID);
            if(retDriver != null){
                userdeets.setAdmin(userRepo.findIDByemail(retDriver.getEmail()),retDriver.getUserName(),retDriver.getEmail(),user.getPassword(),jwtService.generateToken(user.getEmail()),retDriver.getOrgID());
                userdeets.level = retDriver.getLevel();
                userdeets.role = Role.DRIVER;
                return userdeets;
            }
            return null;
//            System.out.println();
//            return jwtService.generateToken(user.getEmail());
        }
        else {
            return null;
        }
    }
    public UserDTO setuserDTO(UserBase user) {
        UserDTO userDTO = FactoryService.createUserDTO();
        userDTO.name = user.getUserName();
        userDTO.email = user.getEmail();
        return userDTO;
    }
    public String generatePass(long id, String org) throws Exception {
            if(id < 0 || org == null){
                return "Invalid request.";
            }
            long ordid = organizationRepo.fetchByName(org);
            long val = passService.generateNewPass(id,ordid);
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
        UserBase fetch = userRepo.findByid(Long.parseLong(req.userid));
        mailingService.paymentEmail(fetch.getEmail(), fetch.getUserName(),payment);
        passService.changeToPaid(fetch.getId());
        return "Pass payment successful.";

    }

    public String reserveSeat(BusTicketDTO req) throws Exception {
        if(req == null){
            return "Invalid request.";
        }
        //check if seat is reserved or not.
        //fetch list of all seats and find the first available one with the said bus id.
        System.out.println(req.busid);
        Seat first = seatRepo.findFirstAvailableSeat(Long.parseLong(req.busid));
        if(first == null){
            return "No available seats";
        }
        seatService.setStatus(first.getBusID(), "occupied",first.getSeatNumber());
        BusTicket val =  busTicketService.getTicket(req, first.getId());
        UserBase fetch = userRepo.findByid(val.getUserID());
        mailingService.sendReservationMail(fetch.getEmail(),fetch.getUserName(),val);

        return "Success.";
//        if(!seatService.checkStatus(req.busid,req.seat)){
//            return "The seat cannot be reserved as it is already occupied.";
//        }
//
//        if(val.getId() > 0){
//            //proceed with sending email prompting to pay.
//            //this means a seat has been reserved.
//
//
//
//            return "Successfully reserved seat payment pending.";
//        }
//        return "Cannot be processed at this time.";
    }

    public String payForSeat(PaymentDTO req,long seat,long ticket) throws Exception {
        if(req == null){
            return "Invalid request.";
        }
        Payment payment = paymentService.makeSeatPayment(req,seat);
        if(payment == null){
            return "Invalid request.Could not be processed";
        }
        UserBase fetch = userRepo.findByid(Long.parseLong(req.userid));
        mailingService.paymentEmail(fetch.getEmail(), fetch.getUserName(),payment);
        busTicketService.setStatus(ticket,"paid");
        return "Ticket payment successful.";

    }

    public long fetchID(String email) {
        return userRepo.findIDByemail(email);
    }
}
