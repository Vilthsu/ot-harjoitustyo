package projectmanager.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import projectmanager.domain.Project;

public class ProjectListItemController implements Initializable {
    @FXML
    protected Text heading;
    @FXML
    protected Text author;
    @FXML
    protected Text desc;
    @FXML
    protected BorderPane row;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setData(Project project) {
        if (heading == null || author == null || desc == null) {
            return;
        }
        
        heading.setText(project.getName());
        desc.setText(project.getDescription());

        if (project.getAuthor() != null && project.getAuthor().getName() != null) {
            author.setText(project.getAuthor().getName());
        }
    }
        
    public Node getView() {
        return row;
    }
}
