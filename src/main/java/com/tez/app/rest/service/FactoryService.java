package com.tez.app.rest.service;


import com.tez.app.rest.DTO.RouteDTO;
import com.tez.app.rest.DTO.ScheduleDTO;
import com.tez.app.rest.DTO.UserDTO;
import com.tez.app.rest.DTO.getLoginDTO;
import com.tez.app.rest.Model.*;
import com.tez.app.rest.Role;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

    public static UserDTO createUserDTO() {
        return new UserDTO();
    }

    public static Organization createOrganization(String type) {
        if(type.equals("CORP_ADMIN")){
            return new Corp_Admin();
        }
            return new Edu_Admin();
    }

    public static User createStudent() {
        return new User();
    }
    public static Admin createAdmin() {
        return new Admin();
    }

    public static Seat createSeat() {
        return new Seat();
    }
    public static Bus createBus() {
        return new Bus();
    }
    public static Schedule createSchedule() { return new Schedule(); }

    public static ScheduleDTO createScheduleDTO() {
        return new ScheduleDTO();
    }

    public static Route createRoute() {
        return new Route();
    }

    public static RouteDTO createRouteDTO() {
        return new RouteDTO();
    }
    public static BusPass createBusPass() { return new BusPass(); }

    public static Payment createPayment() {
        return new Payment();
    }

    public static BusTicket createTicket() {
        return new BusTicket();
    }

    public static getLoginDTO getLoginDTO() {
        return new getLoginDTO();
    }
}
