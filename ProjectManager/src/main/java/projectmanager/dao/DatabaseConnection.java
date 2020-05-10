package projectmanager.dao;

import java.sql.*;

public class DatabaseConnection {
    private final String _connStr;

    public DatabaseConnection(String connStr) throws ClassNotFoundException {
        this._connStr = connStr;
    }

    public Connection getConnectionObject() throws SQLException {
        return DriverManager.getConnection(_connStr);
    }
}
