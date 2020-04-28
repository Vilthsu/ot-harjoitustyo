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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectmanager.constants.FXMLPath;
import projectmanager.constants.Title;
import projectmanager.domain.User;
import projectmanager.ui.Form;
import projectmanager.ui.IController;
import projectmanager.ui.IStackableUI;
import projectmanager.utils.ControllerUtils;
import projectmanager.utils.StageUtils;

public class LoginUIController implements Initializable, IStackableUI, IController {

    private Stage _stage;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private VBox errorBox;

    private Form _form;

    public LoginUIController() {

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
            Logger.getLogger(LoginUIController.class.getName()).log(Level.SEVERE, null, ex);
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
            openBrowseProjects();
        } catch (IllegalArgumentException ex) {
            errorBox.setOpacity(1);
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
}
