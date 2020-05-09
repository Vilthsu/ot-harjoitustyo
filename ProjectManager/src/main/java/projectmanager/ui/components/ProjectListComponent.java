package projectmanager.ui.components;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import projectmanager.domain.Project;

public class ProjectListComponent {
    
    @FXML
    protected ListView projectList;

    public ProjectListComponent() {
        super();
    }
    
    /**
     * Set data to the list
     * @param projects List of projects as ObservableList&lt;Project&gt;
     */
    public void setProjects(ObservableList<Project> projects) {
        if (projectList == null) {
            return;
        }
        
        projectList.setItems(projects);
        projectList.setCellFactory(listView -> {
            return new ProjectListItemComponent();
        });
    }
}