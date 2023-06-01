package com.brylix.derp.factory;

import com.brylix.derp.dao.UserDAO;
import com.brylix.derp.dao.UserDAOImpl;
import com.brylix.derp.decorator.UserDecorator;

// creating instances of the userDAO without access
public class UserDAOFactory {
    public static UserDAO getUserDAO() {
        UserDAO userDAO = new UserDAOImpl(); // Create an instance of UserDAOImpl

        // Decorate the UserDAO with additional functionality
        userDAO = new UserDecorator(userDAO); // Decorate the UserDAO with UserDecorator

        return userDAO; // Return the decorated UserDAO instance
    }
}
