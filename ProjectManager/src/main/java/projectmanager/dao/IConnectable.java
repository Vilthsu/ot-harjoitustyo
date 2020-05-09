package projectmanager.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectable {
    Connection openConnection() throws SQLException;
    void closeConnection(Connection connection) throws SQLException;
}
