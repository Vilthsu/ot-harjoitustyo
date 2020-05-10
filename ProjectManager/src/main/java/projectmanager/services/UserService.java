package projectmanager.services;

import java.sql.SQLException;
import projectmanager.dao.UserDao;
import projectmanager.domain.User;
import projectmanager.utils.AlertUtils;
import projectmanager.utils.UserUtils;


public class UserService {
    protected UserDao userDao;
    
    public UserService() {
        try {
            userDao = new UserDao();
        } catch (ClassNotFoundException ex) {
            AlertUtils.showErrorAlert("An error occurred while establishing a database connection.");
        }
    }
    
    public void login(String username, String password) throws IllegalArgumentException {
        if (userDao == null) {
            AlertUtils.showErrorAlert("An error occurred while logging in.");
            return;
        }
        
        try {
            User user = checkUsername(username);
            
            if (user == null) {
                throw new IllegalArgumentException("An error occurred while logging in.");
            }
            
            boolean loggedIn = checkPassword(user, password);
            
            if (loggedIn) {
                SessionService.start(user);
            } else {
                throw new IllegalArgumentException("An error occurred while logging in.");
            }
        } catch (SQLException ex) {
            AlertUtils.showErrorAlert("An error occurred while logging in.");
        }
    }
    
    private User checkUsername(String username) throws SQLException {
        if (userDao == null) {
            return null;
        }
        
        return userDao.findUserByName(username);
    }
    
    private boolean checkPassword(User user, String password) throws SQLException {
        if (userDao == null) {
            return false;
        }
        
        String passHash = userDao.readPasswordHash(user.getId());
        
        return UserUtils.verifyPasswordHash(password, passHash);
    }
    
    public void create(String username, String email, String password) throws SQLException, IllegalArgumentException {
        int total = userDao.usersTotal();
        int level = 1;
        
        if (total == 0) {
            level = 2;
        }
        
        User anotherUser = checkUsername(username);
        
        if (anotherUser != null) {
            throw new IllegalArgumentException("Käyttäjänimi on jo käytössä");
        }
        
        User user = new User(username, email, level);
        
        userDao.createWithPassword(user, UserUtils.createPasswordHash(password));
    }
}
