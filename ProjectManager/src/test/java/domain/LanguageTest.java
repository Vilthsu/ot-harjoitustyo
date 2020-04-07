package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectmanager.domain.Language;
import projectmanager.domain.User;

public class LanguageTest {
    
    private Language language;
    
    @Before
    public void setUp() {
        language = new Language("Java");
    }
    
    @Test
    public void instanceIsNotNull() {
        assertTrue(language != null);
    }
    
    @Test
    public void instanceHasName() {
        assertTrue(language.name.equals("Java"));
    }
    
    @Test
    public void instancesAreEqual() {
        String name = "Java";
        
        Language lang1 = new Language(name);
        Language lang2 = new Language(name);
        
        assertTrue(lang1.equals(lang2));
    }
    
    @Test
    public void instancesAreNotEqual() {
        String name = "Java";
        String name2 = "C#";
        
        Language instance1 = new Language(name);
        Language instance2 = new Language(name2);
        
        assertFalse(instance2.equals(instance1));
    }
    
    @Test
    public void instancesAreNotEqualWhenAnotherLanguageIsNull() {
        String name = "Java";
        
        Language instance1 = new Language(name);
        Language instance2 = null;
        
        assertNotEquals(instance1, instance2);
    }
    
    @Test
    public void instancesAreNotEqualWhenAnotherLanguageIsNotLanguage() {
        String name = "Java";
        
        Language instance1 = new Language(name);
        Object instance2 = new User();
        
        assertNotEquals(instance1, instance2);
    }
    
    @Test
    public void instanceHashCodeIsInteger() {
        assertEquals(((Integer)language.hashCode()).getClass(), Integer.class);
    }
}
