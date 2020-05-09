package projectmanager.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.validator.EmailValidator;
import projectmanager.utils.StringUtils;
import projectmanager.utils.UserUtils;

public class User implements IModel, IExampleData {
    private int _id;
    private String _name;
    private String _email;
    private int _level;
    
    /**
     * List of user's project
     */
    public List<Project> projects;

    /**
     * Creates a new User instance
     */
    public User() {
        this._level = 1;
        
        projects = new ArrayList<>();
    }

    /**
     * Creates a new User instance
     * @param name User's username
     */
    public User(String name) {
        this();
        this.setName(name);
    }

    /**
     * Creates a new User instance
     * @param name User's username
     * @param email User's email address
     */
    public User(String name, String email) {
        this(name);
        this.setEmail(email);
    }

    /**
     * Creates a new User instance
     * @param name User's username
     * @param email User's email address
     * @param level User's level as integer
     */
    public User(String name, String email, int level) {
        this(name, email);
        this._level = level;
        
        projects = new ArrayList<>();
    }

    /**
     * Creates a new User instance
     * @param id User ID
     * @param name User's username
     * @param email User's email address
     * @param level User's level as integer
     */
    public User(int id, String name, String email, int level) {
        this(name, email, level);
        this._id = id;
        
        projects = new ArrayList<>();
    }

    /**
     * Gets user's id
     * @return User's id
     */
    public int getId() {
        return _id;
    }

    /**
     * Gets user's username
     * @return User's username
     */
    public String getName() {
        return _name;
    }
    /**
     * Sets and validates user's username
     * @param name An username
     * @throws IllegalArgumentException if username is invalid
     */
    public final void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Käyttäjänimi ei voi olla tyhjä");
        }
        
        if (!StringUtils.checkLength(name, 5, 15)) {
            throw new IllegalArgumentException("Käyttäjänimen tulee olla 5-15 merkkiä pitkä");
        }
        
        this._name = name;
    }

    /**
     * Gets user's email address
     * @return User's email address
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Sets and validates user's email address
     * @param email An email address
     * @throws IllegalArgumentException if email address is invalid
     */
    public final void setEmail(String email) throws IllegalArgumentException {
        boolean noEmail = email == null || email.trim().length() == 0;
        
        if (!noEmail && !StringUtils.checkLength(email, 5, 35)) {
            throw new IllegalArgumentException("Sähköpostiosoitteen tulee olla 5-35 merkkiä pitkä");
        }
        
        if (!noEmail) {
            email = StringUtils.trimString(email);
            EmailValidator validator = EmailValidator.getInstance();

            if (!validator.isValid(email)) {
                throw new IllegalArgumentException("Sähköpostiosoite on virheellinen");
            }
        }
        
        this._email = !noEmail ? email : "";
    }

    /**
     * Gets user's level as integer
     * @return User's level as integer
     */
    public int getLevel() {
        return _level;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        
        return Objects.equals(this._name, other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this._name);
        return hash;
    }
    
    @Override
    public String toString() {
        String name = this.getName();
        String level = UserUtils.userFriendlyLevel(this);
        
        return name + " [" + level + "]";
    }

    @Override
    public void createExampleInstance() {
        setName("Example user");
        setEmail("example.user@provider.com");
    }

    @Override
    public void createExampleInstance(int i) {
        _id = i;
        setName("User #" + i);
        setEmail("example.user" + i + "@provider.com");
    }

    @Override
    public boolean isValid() {
        return getId() > 0
                && getName() != null && StringUtils.checkLength(getName(), 5, 15)
                && getEmail() == null || (getEmail() != null && StringUtils.checkLength(getEmail(), 5, 35))
                && getLevel() >= 0;
    }
}
