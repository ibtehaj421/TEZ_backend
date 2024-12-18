package com.tez.app.rest.Controller;

//import com.tez.app.rest.Model.User;
//import com.tez.app.rest.Repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
import com.tez.app.rest.DTO.*;
import com.tez.app.rest.service.BusPassService;
import com.tez.app.rest.service.MailingService;
import com.tez.app.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

//import java.util.List;

@RestController
public class UserController {

//    @Autowired
//    private UserRepo userRepo;

    @Autowired
    MailingService mailingService;

    @Autowired
    UserService userService;
    @Autowired
    private BusPassService busPassService;

    @GetMapping(value = "/")
    public String getPage(){

        return "Are we ready to TEZ yet?? ";
    }

    @GetMapping(path = "/testmail")
    public void getTestMail() throws Exception {
        mailingService.sendMail("sarcasticreaper21@gmail.com","mr.saboor ahmed");
    }


    //add a bus pass to the user
    @PostMapping(path = "/user/pass/add/{id}")
    public String assignPass(@PathVariable long id,@RequestBody NameDTO org) throws Exception {
        return userService.generatePass(id,org.name);
    }
    //pay for the bus pass
    @PostMapping(path = "/user/pass/pay")
    public String payPass(@RequestBody PaymentDTO req) throws Exception {
        return userService.payForPass(req);
    }

    //reserve a seat.
    @PostMapping(path = "/user/seat/reserve")
    public String reserveSeat(@RequestBody BusTicketDTO req) throws Exception {
        return userService.reserveSeat(req);
    }

    @PostMapping(path = "/user/seat/pay/{seat}/{ticket}")
    public String paySeat(@PathVariable("seat")long seat,@PathVariable("ticket") long ticket,@RequestBody PaymentDTO req) throws Exception {
        return userService.payForSeat(req,seat,ticket);
    }

    @GetMapping(path = "/user/id/{email}")
    public long getUserID(@PathVariable String email) throws Exception {
        return userService.fetchID(email);
    }

    @GetMapping(path = "/user/pass/get/{id}")
    public PassDTO getPass(@PathVariable long id) throws Exception {
        return busPassService.getPass(id);
    }
}
