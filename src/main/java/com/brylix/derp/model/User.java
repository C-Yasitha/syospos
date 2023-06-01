package com.brylix.derp.model;

import com.brylix.derp.database.DatabaseQueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
public class User {
    private DatabaseQueryExecutor queryExecutor;

    public User() {
        queryExecutor = new DatabaseQueryExecutor();
    }

    public boolean authenticateUser(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            ResultSet resultSet = queryExecutor.executeQuery(query, username, password);

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's requirements
        }
        return false;
    }
}
