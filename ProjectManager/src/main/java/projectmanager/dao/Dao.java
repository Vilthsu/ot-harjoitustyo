package projectmanager.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao<T, K> implements IDao<T, K>, IConnectable {

    protected DatabaseConnection connection;
    protected String table;

    /**
     * Opens a new connection to the database
     * @return Connection object or null if DatabaseConnection is null.
     * @throws SQLException If open operation fails
     */
    @Override
    public Connection openConnection() throws SQLException {
        if (connection == null) {
            return null;
        }
        
        return connection.getConnectionObject();
    }

    /**
     * Closes given connection
     * @param conn Connection to close
     * @throws SQLException If open operation fails
     */
    @Override
    public void closeConnection(Connection conn) throws SQLException {
        if (conn == null) {
            return;
        }
        
        conn.close();
    }
    
}
