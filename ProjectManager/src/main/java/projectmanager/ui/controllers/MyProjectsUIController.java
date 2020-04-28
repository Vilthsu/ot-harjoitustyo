package projectmanager.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import projectmanager.constants.Title;
import projectmanager.utils.StageUtils;

public class MyProjectsUIController extends ListUIBaseController {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @Override
    public void setTitle() {
        if (stage == null) {
            return;
        }
        
        StageUtils.setSceneTitle(stage, Title.MY_PROJECTS);
    }
}
