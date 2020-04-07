package projectmanager.domain;

public class Language {
    public final String name;
    
    public Language(String name) {
        this.name = name;
    }

    public boolean equals(Language lang) {
        return lang.name.equals(this.name);
    }
}
