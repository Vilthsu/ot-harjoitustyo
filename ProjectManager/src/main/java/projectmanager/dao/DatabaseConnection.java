package projectmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String _connStr;

    public DatabaseConnection(String connStr) throws ClassNotFoundException {
        this._connStr = connStr;
    }

    public Connection getConnectionObject() throws SQLException {
        return DriverManager.getConnection(_connStr);
    }
}
