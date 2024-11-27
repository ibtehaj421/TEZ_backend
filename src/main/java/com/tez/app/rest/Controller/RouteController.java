package com.tez.app.rest.Controller;


import com.tez.app.rest.DTO.BusDTO;
import com.tez.app.rest.DTO.RouteDTO;
import com.tez.app.rest.Model.Route;
import com.tez.app.rest.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //get routes used by an organization
    @GetMapping(path = "/public/route/get/{org}")
    public List<RouteDTO> getRoutesByOrg(@PathVariable String org) {
        return routeService.getOrgRoute(org);
    }

//    @GetMapping(path = "public/route/bus/{route}")
//    public List<BusDTO> getBusByRoute(@PathVariable long route) {
//        return
//    }

    @GetMapping(path = "/driver/route/{driver}")
    public RouteDTO getRoutesByDriver(@PathVariable long driver) {
        return routeService.getRoteByDriver(driver);
    }

}
