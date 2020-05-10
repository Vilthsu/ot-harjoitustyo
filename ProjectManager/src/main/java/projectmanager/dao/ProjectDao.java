package projectmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projectmanager.constants.DatabaseProps;
import projectmanager.domain.Language;
import projectmanager.domain.Project;
import projectmanager.domain.User;
import projectmanager.services.SessionService;

public class ProjectDao extends Dao<Project, Integer> {
    
    private final Dao<User, Integer> _userDao;
    private final Dao<Language, Integer> _languageDao;
    
    public ProjectDao(DatabaseConnection connection, Dao<User, Integer> userDao, Dao<Language, Integer> languageDao) {
        this.connection = connection;
        _userDao = userDao;
        _languageDao = languageDao;
        this.table = "project";
    }
    
    public ProjectDao(String connection, Dao<User, Integer> userDao, Dao<Language, Integer> languageDao) throws ClassNotFoundException {
        this(new DatabaseConnection(connection), userDao, languageDao);
    }
    
    public ProjectDao(Dao<User, Integer> userDao, Dao<Language, Integer> languageDao) throws ClassNotFoundException {
        this(DatabaseProps.defaultConnectionString, userDao, languageDao);
    }

    @Override
    public boolean create(Project project) throws SQLException {
        User user = SessionService.getLoggedUser();
        
        if (project == null || !project.isValid() || user == null) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + this.table + " (name, description, author_id) VALUES (?, ?, ?)");
        stmt.setString(1, project.getName());
        stmt.setString(2, project.getDescription());
        stmt.setInt(3, user.getId());
        
        boolean status = stmt.executeUpdate() == 1;
        
        int id = getLastInsertRowId(conn);
        
        closeConnection(conn);
        
        if (id > 0) {
            associateLoggedUserToProject(project, id);
        }
        
        return status;
    }
    
    private int getLastInsertRowId(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM " + this.table + " ORDER BY id DESC LIMIT 1");
        ResultSet result = stmt.executeQuery();
        
        if (result.next()) {
            return result.getInt("id");
        }
        
        return -1;
    }
    
    public boolean associateLoggedUserToProject(Project project) throws SQLException {
        return associateLoggedUserToProject(project, project.getID());
    }
    
    public boolean associateLoggedUserToProject(Project project, int projectId) throws SQLException {
        if (project == null) {
            return false;
        }
        
        User user = SessionService.getLoggedUser();
        
        user.projects.add(project);
        
        if (projectId > 0) {
            return associateLoggedUserToProject(projectId);
        }
        
        return true;
    }
    
    public boolean associateLoggedUserToProject(int id) throws SQLException {
        User user = SessionService.getLoggedUser();
        return associateUserToProject(id, user);
    }
    
    public boolean associateUserToProject(int id, User user) throws SQLException {
        if (id < 1 || user == null) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_" + this.table + " (user_id, project_id) VALUES (?, ?)");
        stmt.setInt(1, user.getId());
        stmt.setInt(2, id);
        
        boolean status = stmt.executeUpdate() == 1;
        
        closeConnection(conn);
        
        return status;
    }
    
    public boolean loggedUserLeavesProject(Project project) throws SQLException {
        return loggedUserLeavesProject(project, project.getID());
    }
    
    public boolean loggedUserLeavesProject(Project project, int projectId) throws SQLException {
        if (project == null) {
            return false;
        }
        
        User user = SessionService.getLoggedUser();
        
        user.projects.remove(project);
        
        if (projectId > 0) {
            return loggedUserLeavesProject(projectId);
        }
        
        return true;
    }
    
    public boolean loggedUserLeavesProject(int id) throws SQLException {
        User user = SessionService.getLoggedUser();
        return userLeavesProject(id, user);
    }
    
    public boolean userLeavesProject(int id, User user) throws SQLException {
        if (id < 1 || user == null) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_" + this.table + " WHERE user_id = ? AND project_id = ?");
        stmt.setInt(1, user.getId());
        stmt.setInt(2, id);
        
        boolean status = stmt.executeUpdate() > 0;
        
        closeConnection(conn);
        
        return status;
    }

    @Override
    public Project read(Integer id) throws SQLException {
        if (_userDao == null) {
            return null;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + this.table + " WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);
        
        ResultSet result = stmt.executeQuery();
        
        if (!result.next()) {
            return null;
        }
        
        User author = _userDao.read(result.getInt("author_id"));
        
        if (author == null) {
            return null;
        }
        
        Project project = new Project(result.getInt("id"), result.getString("name"), result.getString("description"), author, result.getLong("created"), new ArrayList<>());
        
        closeConnection(conn);
        
        return project;
    }

    @Override
    public boolean update(Project project) throws SQLException {
        if (project == null || !project.isValid()) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("UPDATE " + this.table + " SET name = ?, description = ?, author_id = ? WHERE id = ?");
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
        if (id == null || id < 0) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + this.table + " WHERE id = ?");
        stmt.setInt(1, id);
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        deleteAllAssociates(id);
        
        return status;
    }

    @Override
    public List<Project> list() throws SQLException {
        if (_userDao == null) {
            return null;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + this.table + " ORDER BY created DESC");
        
        List<Project> list = resultsToList(stmt.executeQuery());
        
        closeConnection(conn);
        
        return list;
    }
    
    public boolean deleteAllAssociates(int projectId) throws SQLException {
        if (projectId < 0) {
            return false;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_" + this.table + " WHERE project_id = ?");
        stmt.setInt(1, projectId);
        
        boolean status = stmt.execute();
        
        closeConnection(conn);
        
        return status;
    }

    public List<Project> list(User author) throws SQLException {
        if (_userDao == null || author == null) {
            return null;
        }
        
        Connection conn = openConnection();
        
        PreparedStatement stmt = conn.prepareStatement("SELECT " + this.table + ".* FROM user_project LEFT JOIN " + this.table + " ON user_project." + this.table + "_id = " + this.table + ".id  WHERE user_id = ?");
        stmt.setInt(1, author.getId());
        
        List<Project> list = resultsToList(stmt.executeQuery());
        
        closeConnection(conn);
        
        return list;
    }
    
    private List<Project> resultsToList(ResultSet result) throws SQLException {
        List<Project> list = new ArrayList<>();
        
        while (result.next()) {
            User author = _userDao.read(result.getInt("author_id"));
        
            if (author == null) {
                return null;
            }

            Project project = new Project(result.getInt("id"), result.getString("name"), result.getString("description"), author, result.getLong("created"), new ArrayList<>());
            
            list.add(project);
        }
        
        return list;
    }
}
