package com.tez.app.rest.service;


import com.tez.app.rest.DTO.RouteDTO;
import com.tez.app.rest.Model.Route;
import com.tez.app.rest.Repo.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    RouteRepo repo;

    public String addRoute(RouteDTO req){
           if(req == null){
               return "Invalid Request";
           }
           Route route = FactoryService.createRoute();
           route.setDetails(req.name,req.origin,req.url,req.destination,req.description);
           repo.save(route);
            return "Success";
    }

    public RouteDTO getRoute(Long id){
        Route val = repo.findByID(id);
        RouteDTO dto = FactoryService.createRouteDTO();
        dto.id = val.getId();
        dto.name = val.getName();
        dto.origin = val.getOrigin();
        dto.destination = val.getDestination();
        dto.description = val.getDiscription();
        dto.url = val.getPasses();
        return dto;
    }


    public List<RouteDTO> getRoutes() {
        List<Route> routes = repo.findAll();
        List<RouteDTO> dto = new ArrayList<RouteDTO>();
        for(int i=0;i<routes.size();i++){
            dto.add(FactoryService.createRouteDTO());
            dto.get(i).setDetails(routes.get(i));
            //the buses will be null for now.
        }
        return dto;
    }

    public List<RouteDTO> getOrgRoute(String org) {
        List<Route> routes = repo.findByOrg(org);
        List<RouteDTO> dto = new ArrayList<>();
        for(int i=0;i<routes.size();i++){
            dto.add(FactoryService.createRouteDTO());
            dto.get(i).setDetails(routes.get(i));
            //the buses will be null for now.
        }
        return dto;
    }
}
