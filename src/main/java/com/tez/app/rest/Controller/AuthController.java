package com.tez.app.rest.Controller;
import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.Model.Admin;
import com.tez.app.rest.Model.Driver;
import com.tez.app.rest.Model.User;
//import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.service.AdminService;
import com.tez.app.rest.service.DriverService;
import com.tez.app.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;
    @Autowired
    private DriverService driverService;

    @PostMapping(path = "/auth/register/user")
    public String saveUser(@RequestBody User user){
        System.out.println("this "+user);
        String ret = userService.registerUser(user);
        return ret;
    }

    @PostMapping(path = "/auth/register/admin")
    public String saveAdmin(@RequestBody Admin admin){
        String ret = adminService.registerUser(admin);
        return ret;
    }

    @PostMapping(path = "/auth/register/driver")
    public String saveDriver(@RequestBody Driver driver){
        driverService.registerUser(driver);
        return "Driver Saved.";
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }


    @GetMapping(path = "/default")
    public String defaultUser(){
        return "success";
    }
}
