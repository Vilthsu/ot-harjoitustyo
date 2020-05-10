package projectmanager.services;

import projectmanager.domain.User;

public class SessionService {
    private static User _user;
    
    public static void start(User user) {
        _user = user;
    }
    
    public static void destroy() {
        _user = null;
    }
    
    public static void init() {
        destroy();
    }
    
    public static boolean isLoggedIn() {
        return _user != null;
    }
    
    public static User getLoggedUser() {
        return _user;
    }
}
