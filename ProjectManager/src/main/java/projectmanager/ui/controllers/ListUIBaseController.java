package projectmanager.ui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import projectmanager.ui.IController;
import projectmanager.ui.IStackableUI;
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
