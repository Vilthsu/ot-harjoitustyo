package projectmanager.utils;

public class StringUtils {
    public static boolean checkMinLength(String str, int minLength) {
        str = trimString(str);
        return str.length() >= minLength;
    }
    
    public static boolean checkMaxLength(String str, int maxLength) {
        str = trimString(str);
        return str.length() <= maxLength;
    }
    
    public static boolean checkLength(String str, int minLength, int maxLength) {
        return StringUtils.checkMinLength(str, minLength) && StringUtils.checkMaxLength(str, maxLength);
    }
    
    public static String trimString(String str) {
        return str.trim();
    }
}
