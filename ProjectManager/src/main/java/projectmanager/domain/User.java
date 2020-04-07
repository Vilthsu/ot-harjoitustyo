package projectmanager.domain;

import org.apache.commons.validator.EmailValidator;
import projectmanager.utils.StringUtils;
import projectmanager.utils.UserUtils;

public class User {
    private String _name;
    private String _email;
    private final int _level;

    public User() {
        this._level = 1;
    }

    public User(String name) {
        this._name = name;
        this._level = 1;
    }

    public User(String name, String email) {
        this._name = name;
        this._email = email;
        this._level = 1;
    }

    public User(String name, String email, int level) {
        this._name = name;
        this._email = email;
        this._level = level;
    }
    
    public String getName() {
        return _name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (!StringUtils.checkLength(name, 5, 15)) {
            throw new IllegalArgumentException("Käyttäjänimen tulee olla 5-15 merkkiä pitkä");
        }
        
        this._name = name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if (!StringUtils.checkLength(email, 5, 35)) {
            throw new IllegalArgumentException("Sähköpostiosoitteen tulee olla 5-35 merkkiä pitkä");
        }
        
        email = StringUtils.trimString(email);
        EmailValidator validator = EmailValidator.getInstance();
        
        if (!validator.isValid(email)) {
            throw new IllegalArgumentException("Sähköpostiosoite on virheellinen");
        }
        
        this._email = email;
    }

    public int getLevel() {
        return _level;
    }
    
    @Override
    public String toString() {
        String name = this.getName();
        String level = UserUtils.userFriendlyLevel(this);
        
        return name + " [" + level + "]";
    }
}
