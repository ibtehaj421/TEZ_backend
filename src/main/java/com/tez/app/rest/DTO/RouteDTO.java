package com.tez.app.rest.DTO;

import com.tez.app.rest.Model.Route;

import java.util.List;

public class RouteDTO {
    public long id;
    public String name;
    public String origin;
    public String destination;
    public String description;
    public List<BusDTO> buses; //will be null when not required.
    public String url;

    public void setDetails(Route input){
        id = input.getId();
        name = input.getName();
        origin = input.getOrigin();
        destination = input.getDestination();
        description = input.getDiscription();
        url = input.getPasses();
    }
}
// 1 route
// is an entity that is not owned by any organization
// we have multiple schedules
//these are owned by organizations to tell them that we use these.
// 1 route can have many schedules that it uses.
// each bus is assigned a route (admin can change this at any given time and add a new route.)
// user tries to view seats -> then the user selects a route