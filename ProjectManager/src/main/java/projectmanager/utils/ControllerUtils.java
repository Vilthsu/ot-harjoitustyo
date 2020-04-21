package projectmanager.utils;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projectmanager.ui.IStackableUI;

public class ControllerUtils {
    /**
     * Loads controller from specified path
     * @param <T> Type of controller
     * @param stage Primary stage
     * @param path Path of FXML file
     * @throws java.io.IOException
     */
    public static <T extends IStackableUI> void loadController(Stage stage, URL path) throws IOException {
        FXMLLoader loader = new FXMLLoader(path);
        
        Parent root = loader.load();
        
        T controller = loader.getController();
        controller.setStage(stage);
        
        stage.setScene(new Scene(root, controller.getWidth(), controller.getHeight()));
    }
    
    /**
     * Loads controller from specified path
     * @param <T> Type of controller
     * @param stage Primary stage
     * @param obj Class for resource loader. Usually same class where this method is called.
     * @param path Path of FXML file
     * @throws java.io.IOException
     */
    public static <T extends IStackableUI> void loadController(Stage stage, Class<?> obj, String path) throws IOException {
        ControllerUtils.loadController(stage, obj.getResource(path));
    }
}
