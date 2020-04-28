package projectmanager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import projectmanager.utils.StringUtils;

public class Project {
    
    private long _created;
    private User _author = null;
    private String _name;
    private String _description;
    private final List<Language> _languages;

    /**
     * Creates a new Project instance
     */
    public Project() {
        this._created = System.currentTimeMillis();
        _languages = new ArrayList<>();
    }
    
    /**
     * Creates a new Project instance
     * @param author Author user
     */
    public Project(User author) {
        this();
        this._author = author;
    }
    
    /**
     * Creates a new Project instance
     * @param name Project name
     * @param description Project description
     */
    public Project(String name, String description) {
        this(null);
        this.setName(name);
        this.setDescription(description);
    }
    
    /**
     * Creates a new Project instance
     * @param name Project name
     * @param description Project description
     * @param author Author user
     */
    public Project(String name, String description, User author) {
        this(name, description);
        this._author = author;
    }
    
    /**
     * Creates a new Project instance
     * @param name Project name
     * @param description Project description
     * @param created Creation timestamp
     * @param author Author user
     */
    public Project(String name, String description, long created, User author) {
        this(name, description, author);    
        this._created = created;
    }

    /**
     * Gets name of project
     * @return Name as string
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets name of project
     * @param description A name string
     * @throws IllegalArgumentException if name is invalid
     */
    public final void setName(String name) throws IllegalArgumentException {
        if (!StringUtils.checkLength(name, 5, 50)) {
            throw new IllegalArgumentException("Projektin nimen tulee olla 5-50 merkkiä pitkä");
        }
        
        this._name = name;
    }

    /**
     * Returns description of project
     * @return Description as string
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets description of project
     * @param description A description string
     * @throws IllegalArgumentException if description is invalid
     */
    public final void setDescription(String description) throws IllegalArgumentException {
        if (!StringUtils.checkMaxLength(description, 255)) {
            throw new IllegalArgumentException("Projektin kuvauksen tulee olla korkeintaan 255 merkkiä pitkä");
        }
        
        this._description = description;
    }
    
    /**
     * Checks if language exists in list of languages
     * @param language Unique language instance to check
     */
    public boolean hasLanguage(Language language) {
        return _languages.contains(language);
    }
    
    /**
     * Adds language to list of languages
     * @param language Unique language instance
     */
    public void addLanguage(Language language) {
        if (this.hasLanguage(language)) {
            return;
        }
        
        _languages.add(language);
    }
    
    /**
     * Removes language from list of languages
     * @param language Existing language instance
     */
    public void removeLanguage(Language language) {
        if (!this.hasLanguage(language)) {
            return;
        }
        
        _languages.remove(language);
    }

    /**
     * Returns list of languages
     * @return List of languages as List<{@link Language}>
     */
    public List<Language> getLanguages() {
        return _languages;
    }

    /**
     * Clears project languages
     */
    public void clearLanguages() {
        _languages.clear();
    }

    /**
     * Returns creation timestamp of project
     * @return Creation timestamp as long
     */
    public long getCreated() {
        return _created;
    }

    /**
     * Returns author of project
     * @return Author as User object
     */
    public User getAuthor() {
        return _author;
    }
    
    @Override
    public String toString() {
        String name = this.getName();
        String desc = this.getDescription();
        List<String> languages = new ArrayList<>();
        
        this.getLanguages().forEach((lang) -> {
            languages.add(lang.name);
        });
        
        String languageStr = String.join(", ", languages);
        
        return name + ", " + desc + ", tekijä: " + this._author.getName() + ", luotu: "
                + new Date(_created).toString() + ", kielet: " + languageStr;
    }
}
