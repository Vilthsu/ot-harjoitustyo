package projectmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projectmanager.domain.Language;
import projectmanager.domain.Project;
import projectmanager.domain.User;

public class ProjectDao extends Dao<Project, Integer> {
    
    private final Dao<User, Integer> _userDao;
    private final Dao<Language, Integer> _languageDao;
    
    public ProjectDao(DatabaseConnection connection, Dao<User, Integer> userDao, Dao<Language, Integer> languageDao) {
        this.connection = connection;
        _userDao = userDao;
        _languageDao = languageDao;
    }

    @Override
    public boolean create(Project project) throws SQLException {
        if (project == null || !project.isValid() || !(project.getAuthor() != null && project.getAuthor().isValid())) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO project (name, desc, author_id) VALUES (?, ?, ?)");
        stmt.setString(1, project.getName());
        stmt.setString(2, project.getDescription());
        stmt.setInt(3, project.getAuthor().getId());
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public Project read(Integer id) throws SQLException {
        if (_userDao == null) {
            return null;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM project WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);
        
        ResultSet result = stmt.executeQuery();
        
        if (!result.next()) {
            return null;
        }
        
        User author = _userDao.read(result.getInt("author_id"));
        
        if (author == null) {
            return null;
        }
        
        Project project = new Project(result.getInt("id"), result.getString("name"), result.getString("desc"), author, result.getLong("created"), new ArrayList<>());
        
        closeConnection(conn);
        
        return project;
    }

    @Override
    public boolean update(Project project) throws SQLException {
        if (project == null || !project.isValid()) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("UPDATE project SET name = ?, desc = ?, author_id = ? WHERE id = ?");
        stmt.setString(1, project.getName());
        stmt.setString(2, project.getDescription());
        stmt.setInt(3, project.getAuthor().getId());
        stmt.setInt(4, project.getID());
        
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
        
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM project WHERE id = ?");
        stmt.setInt(1, id);
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public List<Project> list() throws SQLException {
        if (_userDao == null) {
            return null;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM project");
        
        ResultSet result = stmt.executeQuery();
        
        List<Project> list = new ArrayList<>();
        
        while (result.next()) {
            User author = _userDao.read(result.getInt("author_id"));
        
            if (author == null) {
                return null;
            }

            Project project = new Project(result.getInt("id"), result.getString("name"), result.getString("desc"), author, result.getLong("created"), new ArrayList<>());
            
            list.add(project);
        }
        
        closeConnection(conn);
        
        return list;
    }
}
