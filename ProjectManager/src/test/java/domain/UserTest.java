package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectmanager.domain.User;

public class UserTest {
    
    private User user;
    
    @Before
    public void setUp() {
        user = new User();
    }
    
    @Test
    public void userIsNotNull() {
        assertTrue(user != null);
    }
    
    @Test
    public void newUserInstanceIsValid() {
        String name = "User name";
        
        User u = new User(name);
        
        assertTrue(u.toString().equals(name + " [käyttäjä]"));
    }
    
    @Test
    public void newUserInstanceIsValid2() {
        String name = "User name";
        String email = "username@emailprovider.com";
        
        User u = new User(name, email);
        
        assertTrue(u.toString().equals(name + " [käyttäjä]"));
        assertTrue(u.getEmail().equals(email));
    }
    
    @Test
    public void newUserInstanceIsValid3() {
        String name = "User name";
        String email = "username@emailprovider.com";
        int level = 2;
        
        User u = new User(name, email, level);
        
        assertTrue(u.toString().equals(name + " [pääkäyttäjä]"));
        assertTrue(u.getEmail().equals(email));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void userNameMustBeAtLeastFiveCharactersLong() {
        user.setName("User");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void userNameMustBeMaxFiveteenCharactersLong() {
        user.setName("This is an example user");
    }
    
    @Test
    public void userNameMustBeValidWhenLenghtIsNineCharacters() {
        String name = "User name";
        user.setName(name);
        assertTrue(user.getName().equals(name));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emailMustBeAtLeastFiveCharactersLong() {
        user.setEmail("a@b");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emailMustBeMaxThirtyFiveCharactersLong() {
        user.setEmail("someonescoolusername@exampledomain.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emailMustBeValid() {
        user.setEmail("someone(at)example.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emailMustContainValidDomain() {
        user.setEmail("someone@example");
    }
    
    @Test
    public void emailMustBeValidWhenLenghtIsNineteenCharactersAndItHasAllRequiredCharactersAndDomain() {
        String email = "someone@example.com";
        user.setEmail(email);
        assertTrue(user.getEmail().equals(email));
    }
    
    @Test
    public void userLevelMustBeOne() {
        assertTrue(user.getLevel() == 1);
    }
    
    @Test
    public void toStringMustBeValid() {
        String name = "User name";
        user.setName(name);
        
        assertTrue(user.toString().equals("User name [käyttäjä]"));
    }
}
