package projectmanager.utils;

import javafx.stage.Stage;
import projectmanager.constants.Title;

public class StageUtils {
    public static void setSceneTitle(Stage stage, String title) {
        stage.setTitle(Title.BASE + " - " + title);
    }
}
