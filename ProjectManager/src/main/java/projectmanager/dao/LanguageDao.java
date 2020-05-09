package projectmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projectmanager.domain.Language;

public class LanguageDao extends Dao<Language, Integer> {
    
    public LanguageDao(DatabaseConnection connection) {
        this.connection = connection;
    }
    
    @Override
    public boolean create(Language language) throws SQLException {
        if (language == null || !language.isValid()) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO language (name) VALUES (?)");
        stmt.setString(1, language.name);
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public Language read(Integer id) throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM language WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);
        
        ResultSet result = stmt.executeQuery();
        
        if (!result.next()) {
            return null;
        }
        
        Language language = new Language(result.getInt("id"), result.getString("name"));
        
        closeConnection(conn);
        
        return language;
    }

    @Override
    public boolean update(Language language) throws SQLException {
        if (language == null || !language.isValid()) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("UPDATE language SET name = ? WHERE id = ?");
        stmt.setString(1, language.name);
        stmt.setInt(2, language.id);
        
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
        
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM language WHERE id = ?");
        stmt.setInt(1, id);
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public List<Language> list() throws SQLException {
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM language");
        
        ResultSet result = stmt.executeQuery();
        
        List<Language> list = new ArrayList<>();
        
        while (result.next()) {
            Language language = new Language(result.getInt("id"), result.getString("name"));
            
            list.add(language);
        }
        
        closeConnection(conn);
        
        return list;
    }
}
