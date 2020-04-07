package projectmanager.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {
    public final long created;
    public final User author;
    
    private String _name;
    private String _description;
    private final List<Language> _languages;
    
    public Project(String name, String description) {
        this._name = name;
        this._description = description;
        this.created = System.currentTimeMillis();
        this.author = null;
        
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

    public void setName(String name) {
        this._name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        this._description = description;
    }
    
    public void addLanguage(Language language) {
        if (this.hasLanguage(language)) {
            return;
        }
        
        _languages.add(language);
    }
    
    public boolean hasLanguage(Language language) {
        return _languages.contains(language);
    }
    
    public Language getLanguage(String name) {
        int index = _languages.indexOf(new Language(name));
        
        return this.getLanguage(index);
    }
    
    public Language getLanguage(int index) {
        return _languages.get(index);
    }
    
    public void removeLanguage(Language language) {
        if (!this.hasLanguage(language)) {
            return;
        }
        
        _languages.remove(language);
    }
}
