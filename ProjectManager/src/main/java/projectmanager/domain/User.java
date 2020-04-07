package projectmanager.domain;

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

    public void setName(String name) {
        this._name = name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        this._email = email;
    }
    
    
}
