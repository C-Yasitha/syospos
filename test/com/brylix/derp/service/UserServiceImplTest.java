package com.brylix.derp.service;

import com.brylix.derp.dto.UserAuthDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void authenticateValidUser() {
        UserServiceImpl userService = new UserServiceImpl();
        UserAuthDTO user = new UserAuthDTO("admin", "123");

        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        // starting 10 asynchronous calls
        for (int i = 0; i < 1000; i++) {
            futures.add(CompletableFuture.supplyAsync(() -> {
                try {
                    return userService.authenticateUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }));
        }

        // collecting the results of the asynchronous calls
        for (CompletableFuture<Boolean> future : futures) {
            try {
                boolean isAuth = future.get(); // this will block until the result is available
                assertTrue(isAuth); // assert that the result is true
            }catch(Exception e){

            }
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