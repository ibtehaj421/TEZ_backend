package com.tez.app.rest.Controller;


import com.tez.app.rest.DTO.RouteDTO;
import com.tez.app.rest.Model.Route;
import com.tez.app.rest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteController {

    @Autowired
    RouteService routeService;

    @PostMapping(path = "/admin/route/add")
    public String addRoute(@RequestBody RouteDTO route) {
        return routeService.addRoute(route);
    }

    //route editing options.


    //route get options.
    @GetMapping(path = "/public/route/get")
    public List<RouteDTO> getRoutes() {
        return routeService.getRoutes();
    }

}
