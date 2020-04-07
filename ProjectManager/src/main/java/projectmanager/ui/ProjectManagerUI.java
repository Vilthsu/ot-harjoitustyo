package projectmanager.ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.stage.Stage;

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
        
        primaryStage.show();
    }
}
