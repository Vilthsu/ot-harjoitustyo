package projectmanager.services;

import java.sql.SQLException;
import java.util.List;
import projectmanager.dao.LanguageDao;
import projectmanager.dao.ProjectDao;
import projectmanager.dao.UserDao;
import projectmanager.domain.Project;
import projectmanager.domain.User;
import projectmanager.utils.AlertUtils;
import projectmanager.utils.UserUtils;


public class ProjectService {
    protected ProjectDao projectDao;
    protected UserDao userDao;
    protected LanguageDao languageDao;
    
    public ProjectService() {
        try {
            userDao = new UserDao();
            languageDao = new LanguageDao();
            projectDao = new ProjectDao(userDao, languageDao);
        } catch (ClassNotFoundException ex) {
            AlertUtils.showErrorAlert("An error occurred while establishing a database connection.");
        }
    }
    
    public List<Project> listAll() throws SQLException {
        if (projectDao == null) {
            return null;
        }
        
        return projectDao.list();
    }
    
    public List<Project> listAll(User user) throws SQLException {
        if (projectDao == null) {
            return null;
        }
        
        return projectDao.list(user);
    }
    
    public boolean create(Project project) throws SQLException {
        if (project == null || !project.isValid()) {
            return false;
        }
        
        return projectDao.create(project);
    }
    
    public boolean joinProject(Project project) throws SQLException {
        if (project == null || !project.isValid()) {
            return false;
        }
        
        return projectDao.associateLoggedUserToProject(project);
    }
    
    public boolean leaveProject(Project project) throws SQLException {
        if (project == null || !project.isValid()) {
            return false;
        }
        
        return projectDao.loggedUserLeavesProject(project);
    }
    
    public boolean delete(Project project) throws SQLException {
        if (project == null || !project.isValid()) {
            return false;
        }
        
        User user = SessionService.getLoggedUser();
        
        // Admins have permission to delete any project.
        // Regular users can delete their own projects only.
        if (!project.getAuthor().equals(user) && !UserUtils.isAdmin(user)) {
            return false;
        }
        
        return projectDao.delete(project.getID());
    }
}
