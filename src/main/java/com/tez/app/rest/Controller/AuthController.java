package com.tez.app.rest.Controller;
import com.tez.app.rest.Model.Admin;
import com.tez.app.rest.Model.Driver;
import com.tez.app.rest.Model.User;
//import com.tez.app.rest.Model.UserBase;
import com.tez.app.rest.Repo.AdminRepo;
import com.tez.app.rest.Repo.DriverRepo;
import com.tez.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    private DriverRepo driverRepo;

    @PostMapping(path = "/saveuser")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return  "User Saved.";
    }

    @PostMapping(path = "/saveadmin")
    public String saveAdmin(@RequestBody Admin admin){
        adminRepo.save(admin);
        return "Admin Saved.";
    }

    @PostMapping(path = "/admin/driver")
    public String saveDriver(@RequestBody Driver driver){
        driverRepo.save(driver);
        return "Driver Saved.";
    }
}
