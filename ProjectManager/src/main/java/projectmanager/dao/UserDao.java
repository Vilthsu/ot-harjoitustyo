package projectmanager.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projectmanager.domain.User;

public class UserDao extends Dao<User, Integer> {
    
    public UserDao(DatabaseConnection connection) {
        this.connection = connection;
    }
    
    @Override
    public boolean create(User user) throws SQLException {
        if (user == null || !user.isValid()) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (name, email, level) VALUES (?, ?, ?)");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setInt(3, user.getLevel());
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public User read(Integer id) throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);
        
        ResultSet result = stmt.executeQuery();
        
        if (!result.next()) {
            return null;
        }
        
        User user = new User(result.getInt("id"), result.getString("name"), result.getString("email"), result.getInt("level"));
        
        closeConnection(conn);
        
        return user;
    }

    @Override
    public boolean update(User user) throws SQLException {
        if (user == null || !user.isValid()) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("UPDATE user SET name = ?, email = ?, level = ? WHERE id = ?");
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
        
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE id = ?");
        stmt.setInt(1, id);
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public List<User> list() throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user");
        
        ResultSet result = stmt.executeQuery();
        
        List<User> list = new ArrayList<>();
        
        while (result.next()) {
            User user = new User(result.getInt("id"), result.getString("name"), result.getString("email"), result.getInt("level"));
            
            list.add(user);
        }
        
        closeConnection(conn);
        
        return list;
    }
}
