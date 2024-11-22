package com.tez.app.rest.service;


import com.tez.app.rest.DTO.UserDTO;
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
}
