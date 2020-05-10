package projectmanager.ui;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import projectmanager.constants.DatabaseProps;
import projectmanager.constants.FXMLPath;
import projectmanager.utils.AlertUtils;
import projectmanager.utils.ControllerUtils;
import projectmanager.utils.DatabaseUtils;

public class ProjectManagerUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
        checkDatabase();
        initDatabase();
    }
    
    private void checkDatabase() {
        boolean databaseExists = DatabaseUtils.isDatabaseCreated();
        
        if (!databaseExists) {
            boolean created = DatabaseUtils.createNewSQLiteDatabase();
            
            if (!created) {
                boolean fileCreated = DatabaseUtils.createNewSQLiteDatabaseFile();
                
                if (!fileCreated) {
                    AlertUtils.showErrorAlert("An error occurred while creating a database.");
                }
            }
        }
    }
    
    private void initDatabase() {
        try {
            boolean initialized = DatabaseUtils.initDatabaseFromResources(DatabaseProps.templatePath);

            if (!initialized) {
                AlertUtils.showErrorAlert("An error occurred while initializing the database.");
            }
        } catch (ClassNotFoundException | SQLException | URISyntaxException | IOException ex) {
            AlertUtils.showErrorAlert("An error occurred while initializing the database.");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Project manager");
        try {
            openLogin(primaryStage);
        } catch (IOException ex) {
            AlertUtils.showErrorAlert("An error occurred while trying to open the login window.");
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
