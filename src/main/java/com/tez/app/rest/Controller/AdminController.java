package com.tez.app.rest.Controller;

import com.tez.app.rest.Model.User;
import com.tez.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(path = "/admin/users")
    public List<User> getUsers(){

        return userRepo.findAll();
    }
    @GetMapping(path = "/sample")
    public String getSample(){
        return "sample.";
    }
}
