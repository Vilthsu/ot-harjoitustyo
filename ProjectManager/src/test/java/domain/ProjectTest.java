package domain;

import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import projectmanager.domain.Language;
import projectmanager.domain.Project;
import projectmanager.domain.User;

public class ProjectTest {
    
    private Project project;
    private User user;
    private final long created = System.currentTimeMillis();
    
    @Before
    public void setUp() {
        user = new User("User name");
        project = new Project("Test project", "A cool description for this project", created, user);
    }
    
    @Test
    public void projectIsNotNull() {
        assertTrue(project != null);
    }
    
    @Test
    public void userIsNotNull() {
        assertTrue(user != null);
    }
    
    @Test
    public void newProjectInstanceIsValid() {
        String name = "Test project",
               desc = "";
        
        Project p = new Project(name, desc);
        
        assertTrue(p.getName().equals(name));
        assertTrue(p.getDescription().equals(desc));
    }
    
    @Test
    public void newProjectInstanceIsValid2() {
        User u = new User("User name");
        
        Project p = new Project(u);
        
        assertTrue(p.getName() == null);
        assertTrue(p.getDescription() == null);
        assertTrue(p.author.equals(u));
    }
    
    @Test
    public void newProjectInstanceIsValid3() {
        String name = "Test project",
               desc = "";
        User u = new User("User name");
        
        Project p = new Project(name, desc, u);
        
        assertTrue(p.getName().equals(name));
        assertTrue(p.getDescription().equals(desc));
        assertTrue(p.author.equals(u));
    }
    
    @Test
    public void newProjectInstanceIsValid4() {
        String name = "Test project",
               desc = "";
        User u = new User("User name");
        long createdAt = System.currentTimeMillis();
        
        Project p = new Project(name, desc, createdAt, u);
        
        assertTrue(p.getName().equals(name));
        assertTrue(p.getDescription().equals(desc));
        assertTrue(p.author.equals(u));
        assertTrue(p.created == createdAt);
    }
    
    @Test
    public void newProjectInstanceIsValid5() {
        long createdAt = System.currentTimeMillis();
        
        Project p = new Project();
        
        assertTrue(p.getName() == null);
        assertTrue(p.getDescription() == null);
        assertTrue(p.author == null);
        assertTrue(p.created == createdAt);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void projectNameMustBeAtLeastFiveCharactersLong() {
        project.setName("P");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void projectNameMustBeMaxFiftyCharactersLong() {
        project.setName("This is a very long name for an example project which has more than 50 characters");
    }
    
    @Test
    public void projectNameMustBeValidWhenLenghtIsTwelveCharacters() {
        String name = "Project name";
        project.setName(name);
        assertTrue(project.getName().equals(name));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void projectDescriptionMustBeMax255CharactersLong() {
        project.setDescription("This is a very long description for an example project which has more than 255 characters because we have set that limit in our database and longer values are not allowed. That is why we have to validate length of inputs before inserting them into our database.");
    }
    
    @Test
    public void projectDescriptionMustBeValidWhenLenghtIsNineteenCharacters() {
        String desc = "Project description";
        
        project.setDescription(desc);
        
        assertTrue(project.getDescription().equals(desc));
    }
    
    @Test
    public void projectDescriptionMustBeValidWhenLenghtIsZeroCharacters() {
        String desc = "";
        
        project.setDescription(desc);
        
        assertTrue(project.getDescription().equals(desc));
    }
    
    @Test
    public void canAddLanguagesToProject() {
        Language lang = new Language("C#");
        
        project.addLanguage(lang);
        
        assertTrue(project.hasLanguage(lang));
    }
    
    @Test
    public void canClearProjectLanguages() {
        Language lang = new Language("C#");
        
        project.addLanguage(lang);
        project.clearLanguages();
        
        assertTrue(project.getLanguages().isEmpty());
    }
    
    @Test
    public void canAddUniqueLanguagesToProject() {
        Language lang = new Language("C#");
        
        project.addLanguage(lang);
        project.addLanguage(lang);
        
        assertTrue(project.hasLanguage(lang));
        assertEquals(project.getLanguages().size(), 1);
    }
    
    @Test
    public void canRemoveLanguagesFromProject() {
        Language lang = new Language("C#");
        
        project.addLanguage(lang);
        
        assertTrue(project.hasLanguage(lang));
        assertEquals(project.getLanguages().size(), 1);
        
        project.removeLanguage(lang);
        
        assertTrue(project.getLanguages().isEmpty());
    }
    
    @Test
    public void cannotRemoveLanguagesFromProject() {
        project.clearLanguages();
        
        assertTrue(project.getLanguages().isEmpty());
        
        Language lang = new Language("C#");
        project.removeLanguage(lang);
        
        assertTrue(project.getLanguages().isEmpty());
    }
    
    @Test
    public void toStringOutputIsCorrect() {
        Language lang = new Language("C#");
        project.addLanguage(lang);
        
        String result = project.getName() + ", " + project.getDescription()+ ", tekijä: "
                + project.author.getName() + ", luotu: " + new Date(created).toString()
                + ", kielet: C#";
        
        assertEquals(project.toString(), result);
    }
}
