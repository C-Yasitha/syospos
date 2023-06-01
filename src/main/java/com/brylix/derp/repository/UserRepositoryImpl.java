package com.brylix.derp.repository;

import com.brylix.derp.dao.UserRepository;
import com.brylix.derp.database.DatabaseQueryExecutor;
import com.brylix.derp.dto.UserAuthDTO;
import com.brylix.derp.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    private DatabaseQueryExecutor queryExecutor;
    public UserRepositoryImpl() {
        queryExecutor = new DatabaseQueryExecutor();
    }

    public boolean authenticateUser(User user) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            ResultSet resultSet = queryExecutor.executeQuery(query, user.getUserName(), user.getPassword());

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's requirements
        }
        return false;
    }
}
