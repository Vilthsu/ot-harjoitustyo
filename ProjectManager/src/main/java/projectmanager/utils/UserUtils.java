package projectmanager.utils;

import java.util.HashMap;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;
import projectmanager.domain.User;

public class UserUtils {
    private static final int _adminLevel = 2;
    private static final int _hashingRoundsDefault = 15;
    
    public static Map<Integer, String> userFriendlyLevels() {
        Map<Integer, String> levels = new HashMap<>();
        
        levels.put(1, "käyttäjä");
        levels.put(2, "pääkäyttäjä");
        
        return levels;
    }
    
    public static String userFriendlyLevel(User user) {
        int level = user.getLevel();
        return UserUtils.userFriendlyLevels().get(level);
    }
    
    public static boolean isAdmin(User user) {
        return user.getLevel() == _adminLevel;
    }
    
    public static String createPasswordHash(String password) {
        return createPasswordHash(password, _hashingRoundsDefault);
    }
    
    public static String createPasswordHash(String password, int rounds) {
        return BCrypt.hashpw(password, BCrypt.gensalt(rounds));
    }
    
    public static boolean verifyPasswordHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
