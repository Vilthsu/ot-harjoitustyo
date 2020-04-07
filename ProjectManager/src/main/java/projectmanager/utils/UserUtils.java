package projectmanager.utils;

import java.util.HashMap;
import java.util.Map;
import projectmanager.domain.User;

public class UserUtils {
    public static Map<Integer, String> userFriendlyLevels() {
        Map<Integer, String> levels = new HashMap<Integer, String>();
        
        levels.put(1, "käyttäjä");
        levels.put(2, "pääkäyttäjä");
        
        return levels;
    }
    
    public static String userFriendlyLevel(User user) {
        int level = user.getLevel();
        return UserUtils.userFriendlyLevels().get(level);
    }
}
