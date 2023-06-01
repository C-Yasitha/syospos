package com.brylix.derp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DatabaseQueryExecutor {
    private Connection connection;

    public DatabaseQueryExecutor() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        setStatementParameters(statement, params);
        return statement.executeQuery();
    }

    public int executeUpdate(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        setStatementParameters(statement, params);
        return statement.executeUpdate();
    }

    private void setStatementParameters(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }
}
