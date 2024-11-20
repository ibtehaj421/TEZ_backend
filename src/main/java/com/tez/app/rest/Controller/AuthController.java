package com.tez.app.rest.Controller;
import com.tez.app.rest.Model.Admin;
import com.tez.app.rest.Model.Driver;
import com.tez.app.rest.Model.User;
//import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Repo.AdminRepo;
import com.tez.app.rest.Repo.DriverRepo;
import com.tez.app.rest.Repo.UserRepo;
import com.tez.app.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private DriverRepo driverRepo;

    @PostMapping(path = "/auth/register/user")
    public String saveUser(@RequestBody User user){
        System.out.println("this "+user);
        String ret = userService.registerUser(user);
        return ret;
    }

    @PostMapping(path = "/auth/register/admin")
    public String saveAdmin(@RequestBody Admin admin){
        adminRepo.save(admin);
        return "Admin Saved.";
    }

    @PostMapping(path = "/auth/register/driver")
    public String saveDriver(@RequestBody Driver driver){
        driverRepo.save(driver);
        return "Driver Saved.";
    }

    @PostMapping(path = "/login/user")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

    @PostMapping(path = "/login/admin")
    public String loginAdmin(@RequestBody Admin admin){
        //adminRepo.save(admin);
        return "Admin Saved.";
    }

    @PostMapping(path = "/login/driver")
    public String loginDriver(@RequestBody Driver driver){
        return "Driver Saved.";
    }

}
