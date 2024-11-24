package com.tez.app.rest.Controller;

//import com.tez.app.rest.Model.User;
//import com.tez.app.rest.Repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
import com.tez.app.rest.DTO.BusTicketDTO;
import com.tez.app.rest.DTO.PaymentDTO;
import com.tez.app.rest.DTO.SeatDTO;
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

    @GetMapping(value = "/")
    public String getPage(){

        return "Are we ready to TEZ yet?? ";
    }

    @GetMapping(path = "/testmail")
    public void getTestMail() throws Exception {
        mailingService.sendMail("sarcasticreaper21@gmail.com","mr.saboor ahmed");
    }


    //add a bus pass to the user
    @PostMapping(path = "/user/pass/add/{id}/{org}")
    public String assignPass(@PathVariable long id, @PathVariable long org) throws Exception {
        return userService.generatePass(id,org);
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

}
