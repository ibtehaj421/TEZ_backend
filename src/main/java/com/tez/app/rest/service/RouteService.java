package com.tez.app.rest.service;


import com.tez.app.rest.DTO.RouteDTO;
import com.tez.app.rest.Repo.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    RouteRepo repo;

    public String addRoute(RouteDTO req){
            return null;
    }


}
