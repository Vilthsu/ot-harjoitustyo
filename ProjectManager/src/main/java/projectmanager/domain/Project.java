package projectmanager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import projectmanager.utils.StringUtils;

public class Project {
    public final long created;
    public final User author;
    
    private String _name;
    private String _description;
    private final List<Language> _languages;

    public Project() {
        this.created = System.currentTimeMillis();
        this.author = null;
        
        _languages = new ArrayList<>();
    }
    
    public Project(User author) {
        this.created = System.currentTimeMillis();
        this.author = author;
        
        _languages = new ArrayList<>();
    }
    
    public Project(String name, String description) {
        this._name = name;
        this._description = description;
        this.created = System.currentTimeMillis();
        this.author = null;
        
        _languages = new ArrayList<>();
    }
    
    public Project(String name, String description, User author) {
        this._name = name;
        this._description = description;
        this.created = System.currentTimeMillis();
        this.author = author;
        
        _languages = new ArrayList<>();
    }
    
    public Project(String name, String description, long created, User author) {
        this._name = name;
        this._description = description;
        this.created = created;
        this.author = author;
        
        _languages = new ArrayList<>();
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (!StringUtils.checkLength(name, 5, 50)) {
            throw new IllegalArgumentException("Projektin nimen tulee olla 5-50 merkkiä pitkä");
        }
        
        this._name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (!StringUtils.checkMaxLength(description, 255)) {
            throw new IllegalArgumentException("Projektin kuvauksen tulee olla korkeintaan 255 merkkiä pitkä");
        }
        
        this._description = description;
    }
    
    public boolean hasLanguage(Language language) {
        return _languages.contains(language);
    }
    
    public void addLanguage(Language language) {
        if (this.hasLanguage(language)) {
            return;
        }
        
        _languages.add(language);
    }
    
    public void removeLanguage(Language language) {
        if (!this.hasLanguage(language)) {
            return;
        }
        
        _languages.remove(language);
    }

    public List<Language> getLanguages() {
        return _languages;
    }

    public void clearLanguages() {
        _languages.clear();
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
        
        return name + ", " + desc + ", tekijä: " + this.author.getName() + ", luotu: "
                + new Date(created).toString() + ", kielet: " + languageStr;
    }
}
