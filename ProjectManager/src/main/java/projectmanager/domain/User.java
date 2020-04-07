package projectmanager.domain;

import projectmanager.utils.StringUtils;

public class User {
    private String _name;
    private String _email;

    public User(String name) {
        this._name = name;
    }

    public User(String name, String email) {
        this._name = name;
        this._email = email;
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
        if (!StringUtils.checkLength(email, 5, 20)) {
            throw new IllegalArgumentException("Sähköpostiosoitteen tulee olla 5-20 merkkiä pitkä");
        }
        
        this._email = email;
    }
    
    
}
