package com.tez.app.rest.DTO;

import java.util.List;

public class RouteDTO {
    public long id;
    public String origin;
    public String destination;
    public String description;
    public List<BusDTO> buses;
    public String url;
}
// 1 route
// is an entity that is not owned by any organization
// we have multiple schedules
//these are owned by organizations to tell them that we use these.
// 1 route can have many schedules that it uses.
// each bus is assigned a route (admin can change this at any given time and add a new route.)
// user tries to view seats -> then the user selects a route