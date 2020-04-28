package projectmanager.ui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projectmanager.constants.FXMLPath;
import projectmanager.constants.Title;
import projectmanager.domain.User;
import projectmanager.ui.Form;
import projectmanager.ui.IController;
import projectmanager.ui.IStackableUI;
import projectmanager.utils.ControllerUtils;
import projectmanager.utils.StageUtils;

public class ListUIBaseController implements Initializable, IStackableUI, IController {

    protected Stage stage;
    
    public ListUIBaseController() {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @Override
    public void setTitle() {
        if (stage == null) {
            return;
        }
        
        StageUtils.setSceneTitle(stage, "list-screen-title");
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
        setTitle();
    }

    @Override
    public int getHeight() {
        return 570;
    }

    @Override
    public int getWidth() {
        return 720;
    }
}
