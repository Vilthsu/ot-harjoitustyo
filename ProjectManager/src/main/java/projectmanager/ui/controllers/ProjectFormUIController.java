package projectmanager.ui.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projectmanager.constants.Title;
import projectmanager.domain.Language;
import projectmanager.domain.Project;
import projectmanager.services.ProjectService;
import projectmanager.ui.Form;
import projectmanager.ui.IController;
import projectmanager.ui.IStackableUI;
import projectmanager.utils.StageUtils;

public class ProjectFormUIController implements Initializable, IStackableUI, IController {

    private Stage _stage;

    @FXML
    protected TextField projectnameField;
    @FXML
    protected TextField projectdescField;
    @FXML
    protected TextArea projectlangField;
    @FXML
    protected VBox errorBox;
    @FXML
    protected Text errorText;

    private Form _form;

    protected ProjectService projectService;

    private Project _tempProject;

    public ProjectFormUIController() {
        projectService = new ProjectService();
        _tempProject = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _form = new Form(this.projectnameField, this.projectdescField, this.projectlangField);
    }

    @Override
    public void setTitle() {
        if (_stage == null) {
            return;
        }

        StageUtils.setSceneTitle(_stage, Title.ADD_PROJECT);
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

    public void closeWindow() {
        if (_stage == null) {
            return;
        }

        _stage.close();
    }

    @FXML
    private void handleCancelButtonClick(ActionEvent event) {
        closeWindow();
    }

    @FXML
    private void handleCreationButtonClick(ActionEvent event) {
        try {
            validate();
            errorBox.setOpacity(0);
            creationOperation();
        } catch (IllegalArgumentException ex) {
            errorText.setText(ex.getMessage());
            errorBox.setOpacity(1);
        }
    }

    private void validate() throws IllegalArgumentException {
        Map<String, String> values = _form.getValues();

        String projectname = values.get("projectname");
        String projectdesc = values.get("projectdesc");
        String projectlang = values.get("projectlang");

        _tempProject = new Project(projectname, projectdesc);

        String[] langs = projectlang.split("\n");

        for (String lang : langs) {
            _tempProject.addLanguage(new Language(lang));
        }
    }

    private void creationOperation() {
        if (_tempProject == null) {
            return;
        }

        try {
            boolean status = projectService.create(_tempProject);

            if (status) {
                onCreationSuccess();
            }
        } catch (IllegalArgumentException ex) {
            errorText.setText(ex.getMessage());
            errorBox.setOpacity(1);
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Tuntematon virhe!");
        } finally {
        }
    }

    private void onCreationSuccess() {
        closeWindow();
    }
}
