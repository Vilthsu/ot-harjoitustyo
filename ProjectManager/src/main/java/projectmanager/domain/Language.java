package projectmanager.domain;

import java.util.Objects;

public class Language implements IModel {
    /**
     * ID of language
     */
    public final int id;
    /**
     * Name of language
     */
    public final String name;
    
    /**
     * Creates a new Language instance
     * @param name A name of language
     */
    public Language(String name) {
        this(0, name);
    }
    
    /**
     * Creates a new Language instance
     * @param id An ID of language
     * @param name A name of language
     */
    public Language(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        return hash;
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
        final Language other = (Language) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public boolean isValid() {
        return name != null && name.trim().length() > 0;
    }
}
