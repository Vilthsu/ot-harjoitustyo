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

public class RegistrationUIController implements Initializable, IStackableUI, IController {

    private Stage _stage;
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField password2Field;
    @FXML
    private VBox errorBox;
    @FXML
    private Text errorText;
    
    private Form _form;
    
    public RegistrationUIController() {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _form = new Form(this.usernameField, this.emailField, this.passwordField, this.password2Field);
    }
    
    @Override
    public void setTitle() {
        if (_stage == null) {
            return;
        }
        
        StageUtils.setSceneTitle(_stage, Title.REGISTRATION);
    }

    @Override
    public void setStage(Stage stage) {
        _stage = stage;
        setTitle();
    }

    @Override
    public int getHeight() {
        return 370;
    }

    @Override
    public int getWidth() {
        return 420;
    }
    
    
    public void openLogin() {
        if (_stage == null) {
            return;
        }
        
        try {
            ControllerUtils.loadController(_stage, getClass(), FXMLPath.LOGIN);
        } catch (IOException ex) {
            Logger.getLogger(LoginUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleLoginLinkClick(ActionEvent event) {
        openLogin();
    }
    
    @FXML
    private void handleRegistrationButtonClick(ActionEvent event) {
        try {
            validate();
            errorBox.setOpacity(0);
        } catch (IllegalArgumentException ex) {
            errorText.setText(ex.getMessage());
            errorBox.setOpacity(1);
        }
    }
    
    private void validate() throws IllegalArgumentException {
        Map<String, String> values = _form.getValues();
        
        String username = values.get("username");
        String email = values.get("email");
        String password = values.get("password");
        String password2 = values.get("password2");
        
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        
        if (password == null || password2 == null || !password.trim().equals(password2.trim())) {
            throw new IllegalArgumentException("Salasanat eivät täsmää");
        }
        
        if (password.trim().length() < 8 || password2.trim().length() < 8) {
            throw new IllegalArgumentException("Salasanan tulee olla vähintään kahdeksan (8) merkkiä pitkä");
        }
    }
}
