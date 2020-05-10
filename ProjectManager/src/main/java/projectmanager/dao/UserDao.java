package projectmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import projectmanager.constants.DatabaseProps;
import projectmanager.domain.User;

public class UserDao extends Dao<User, Integer> {
    
    public UserDao(DatabaseConnection connection) {
        this.connection = connection;
        this.table = "users";
    }
    public UserDao(String connection) throws ClassNotFoundException {
        this(new DatabaseConnection(connection));
    }
    public UserDao() throws ClassNotFoundException {
        this(DatabaseProps.defaultConnectionString);
    }
    
    @Override
    public boolean create(User user) throws SQLException {
        if (user == null || !user.isValid()) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + this.table + " (name, email, level) VALUES (?, ?, ?)");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setInt(3, user.getLevel());
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }
    
    public boolean createWithPassword(User user, String passwordHash) throws SQLException {
        if (user == null) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + this.table + " (name, email, level, password_hash) VALUES (?, ?, ?, ?)");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setInt(3, user.getLevel());
        stmt.setString(4, passwordHash);
        
        boolean status = stmt.executeUpdate() == 1;
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public User read(Integer id) throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + this.table + " WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);
        
        ResultSet result = stmt.executeQuery();
        
        if (!result.next()) {
            return null;
        }
        
        User user = new User(result.getInt("id"), result.getString("name"), result.getString("email"), result.getInt("level"));
        
        closeConnection(conn);
        
        return user;
    }

    public User findUserByName(String name) throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + this.table + " WHERE name = ? LIMIT 1");
        stmt.setString(1, name);
        
        ResultSet result = stmt.executeQuery();
        
        if (!result.next()) {
            return null;
        }
        
        User user = new User(result.getInt("id"), result.getString("name"), result.getString("email"), result.getInt("level"));
        
        closeConnection(conn);
        
        return user;
    }

    public String readPasswordHash(Integer id) throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT password_hash FROM " + this.table + " WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);
        
        ResultSet result = stmt.executeQuery();
        
        if (!result.next()) {
            return null;
        }
        
        String hash = result.getString("password_hash");
        
        closeConnection(conn);
        
        return hash;
    }

    @Override
    public boolean update(User user) throws SQLException {
        if (user == null || !user.isValid()) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("UPDATE " + this.table + " SET name = ?, email = ?, level = ? WHERE id = ?");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setInt(3, user.getLevel());
        stmt.setInt(4, user.getId());
        
        int updatedRows = stmt.executeUpdate();
        
        closeConnection(conn);
        
        return updatedRows == 1;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        if (id < 0) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + this.table + " WHERE id = ?");
        stmt.setInt(1, id);
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public List<User> list() throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + this.table);
        
        ResultSet result = stmt.executeQuery();
        
        List<User> list = new ArrayList<>();
        
        while (result.next()) {
            User user = new User(result.getInt("id"), result.getString("name"), result.getString("email"), result.getInt("level"));
            
            list.add(user);
        }
        
        closeConnection(conn);
        
        return list;
    }

    public int usersTotal() throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) as amount FROM " + this.table);
        
        ResultSet result = stmt.executeQuery();
        
        int total = result.getInt("amount");
        
        closeConnection(conn);
        
        return total;
    }
}
