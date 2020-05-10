package projectmanager.ui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectmanager.constants.FXMLPath;
import projectmanager.constants.Title;
import projectmanager.domain.User;
import projectmanager.services.SessionService;
import projectmanager.services.UserService;
import projectmanager.ui.Form;
import projectmanager.ui.IController;
import projectmanager.ui.IStackableUI;
import projectmanager.utils.AlertUtils;
import projectmanager.utils.ControllerUtils;
import projectmanager.utils.StageUtils;

public class LoginUIController implements Initializable, IStackableUI, IController {

    private Stage _stage;

    @FXML
    protected TextField usernameField;
    @FXML
    protected PasswordField passwordField;
    @FXML
    protected VBox errorBox;

    private Form _form;
    
    protected UserService userService;

    public LoginUIController() {
        userService = new UserService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _form = new Form(this.usernameField, this.passwordField);
    }

    @Override
    public void setTitle() {
        if (_stage == null) {
            return;
        }

        StageUtils.setSceneTitle(_stage, Title.LOGIN);
    }

    @Override
    public void setStage(Stage stage) {
        _stage = stage;
        setTitle();
    }

    @Override
    public int getHeight() {
        return 275;
    }

    @Override
    public int getWidth() {
        return 420;
    }

    public void openRegistration() {
        openWindow(FXMLPath.REGISTRATION);
    }

    public void openBrowseProjects() {
        openWindow(FXMLPath.BROWSE_PROJECTS);
    }

    public void openWindow(String path) {
        if (_stage == null) {
            return;
        }

        try {
            ControllerUtils.loadController(_stage, getClass(), path);
        } catch (IOException ex) {
            AlertUtils.showErrorAlert("Tapahtui virhe avatessa uutta näkymää.");
        }
    }

    @FXML
    private void handleRegistrationLinkClick(ActionEvent event) {
        openRegistration();
    }

    @FXML
    private void handleLoginButtonClick(ActionEvent event) {
        runValidation();
    }

    private void runValidation() {
        try {
            validate();
            errorBox.setOpacity(0);
            loginOperation();
        } catch (IllegalArgumentException ex) {
            errorBox.setOpacity(1);
        }
    }
    
    private void loginOperation() {
        Map<String, String> values = _form.getValues();

        String username = values.get("username");
        String password = values.get("password");
        
        try {
            userService.login(username, password);
        } catch (IllegalArgumentException ex) {
            errorBox.setOpacity(1);
        } finally {
            if (SessionService.isLoggedIn()) {
                onLoginSuccess();
            }
        }
    }

    @FXML
    private void handleFieldKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            runValidation();
        }
    }

    private void validate() throws IllegalArgumentException {
        Map<String, String> values = _form.getValues();

        String username = values.get("username");
        String password = values.get("password");

        User user = new User();
        user.setName(username);

        if (password == null || password.trim().length() < 8) {
            throw new IllegalArgumentException();
        }
    }
    
    private void onLoginSuccess() {
        openBrowseProjects();
    }
}
