package com.brylix.derp.service;

import com.brylix.derp.dto.UserAuthDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void authenticateValidUser() {
        UserServiceImpl userService = new UserServiceImpl();
        UserAuthDTO user = new UserAuthDTO("admin", "123");

        try {
            boolean isAuth = userService.authenticateUser(user);
            assertEquals(true,isAuth);
        }catch(Exception e){

        }
    }

    @Test
    void authenticateInvalidUser() {
        UserServiceImpl userService = new UserServiceImpl();
        UserAuthDTO user = new UserAuthDTO("admin", "1234");

        try {
            boolean isAuth = userService.authenticateUser(user);
            assertEquals(false,isAuth);
        }catch(Exception e){

        }
    }

    @Test
    void authenticateMissingUser() {
        UserServiceImpl userService = new UserServiceImpl();

        try {
            boolean isAuth = userService.authenticateUser(null);
            assertEquals(false,isAuth);
        }catch(Exception e){
        }
    }
}