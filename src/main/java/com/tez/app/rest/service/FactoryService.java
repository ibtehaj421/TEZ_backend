package com.tez.app.rest.service;


import com.tez.app.rest.DTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

    public static UserDTO createUserDTO() {
        return new UserDTO();
    }

}
