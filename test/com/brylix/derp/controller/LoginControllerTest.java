package com.brylix.derp.controller;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    public void testLoginWithValidCredentials() {
        // Create a LoginController instance
        LoginController loginController = new LoginController();
        // Perform the login
        boolean isAuth = loginController.Login("admin", "123");
        assertEquals(true,isAuth);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        // Create a LoginController instance
        LoginController loginController = new LoginController();
        // Perform the login
        boolean isAuth = loginController.Login("admin", "1234");
        assertEquals(false,isAuth);

    }
}