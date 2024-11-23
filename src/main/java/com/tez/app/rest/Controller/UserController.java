package com.tez.app.rest.Controller;

//import com.tez.app.rest.Model.User;
//import com.tez.app.rest.Repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
import com.tez.app.rest.service.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@RestController
public class UserController {

//    @Autowired
//    private UserRepo userRepo;

    @Autowired
    MailingService mailingService;
    @GetMapping(value = "/")
    public String getPage(){

        return "Are we ready to TEZ yet?? ";
    }

    @GetMapping(path = "/testmail")
    public void getTestMail() throws Exception {
        mailingService.sendMail("sarcasticreaper21@gmail.com","mr.saboor ahmed");
    }

//    @PostMapping(path = "/save")
//    public String saveUser(@RequestBody User user){
//       userRepo.save(user);
//       return  "User Saved.";
//    }
}
