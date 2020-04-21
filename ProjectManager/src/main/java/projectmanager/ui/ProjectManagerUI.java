package projectmanager.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import projectmanager.constants.FXMLPath;
import projectmanager.utils.ControllerUtils;

public class ProjectManagerUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
      // TODO
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Project manager");
        try {
            openLogin(primaryStage);
        } catch (IOException ex) {
            Logger.getLogger(ProjectManagerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.show();
    }
    
    public void openLogin(Stage stage) throws IOException {
        openController(stage, FXMLPath.LOGIN);
    }
    
    public void openController(Stage stage, String path) throws IOException {
        ControllerUtils.loadController(stage, getClass(), path);
    }
}
