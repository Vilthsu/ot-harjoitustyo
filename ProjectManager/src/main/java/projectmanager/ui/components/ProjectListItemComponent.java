package projectmanager.ui.components;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import static projectmanager.constants.FXMLPath.PROJECT_LIST_ITEM;
import projectmanager.domain.Project;
import projectmanager.ui.controllers.ProjectListItemController;
import projectmanager.utils.ControllerUtils;


public class ProjectListItemComponent extends ListCell<Project> {
    
    private ProjectListItemController _controller = null;
    private Parent _root = null;
    
    public ProjectListItemComponent() {
        super();
        
        try {
            FXMLLoader loader = new FXMLLoader(ProjectListComponent.class.getResource(PROJECT_LIST_ITEM));
            _root = loader.load();
            _controller = (ProjectListItemController)loader.getController();
        } catch (IOException ex) {
        }
    }
    
    @Override
    protected void updateItem(Project project, boolean empty) {
        super.updateItem(project, empty);

        if(empty || project == null || !project.isRenderable() || _controller == null || _root == null) {
            setText(null);
            setGraphic(null);
            
            return;
        }

        _controller.setData(project);
        
        setText(null);
        setGraphic(_controller.getView());
    }
}
