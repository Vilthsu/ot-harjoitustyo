package domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import projectmanager.domain.Project;
import projectmanager.domain.User;
import projectmanager.utils.UserUtils;

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
    public void friendlyUserLevelMustValid() {
        String fromMap = UserUtils.userFriendlyLevels().get(user.getLevel());
        String fromMethod = UserUtils.userFriendlyLevel(user);
        
        assertTrue(fromMap.equals(fromMethod));
    }
    
    @Test
    public void toStringMustBeValid() {
        String name = "User name";
        user.setName(name);
        
        assertTrue(user.toString().equals(name + " [käyttäjä]"));
    }
    
    @Test
    public void usersAreEqual() {
        String name = "User name";
        
        User user1 = new User(1, name, null, 1);
        User user2 = new User(1, name, null, 1);
        
        assertTrue(user1.equals(user2));
    }
    
    @Test
    public void usersAreNotEqual() {
        String name = "User name";
        String name2 = "Käyttäjä";
        
        User user1 = new User(1, name, null, 1);
        User user2 = new User(2, name2, null, 1);
        
        assertFalse(user2.equals(user1));
    }
    
    @Test
    public void usersAreNotEqualWhenAnotherUserIsNull() {
        String name = "User name";
        
        User user1 = new User(name);
        User user2 = null;
        
        assertNotEquals(user1, user2);
    }
    
    @Test
    public void usersAreNotEqualWhenAnotherUserIsNotUser() {
        String name = "User name";
        
        User user1 = new User(name);
        Object user2 = new Project();
        
        assertNotEquals(user1, user2);
    }
    
    @Test
    public void userHashCodeIsInteger() {
        assertEquals(((Integer)user.hashCode()).getClass(), Integer.class);
    }
}
