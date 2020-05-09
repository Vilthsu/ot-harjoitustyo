package projectmanager.ui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import projectmanager.constants.FXMLPath;
import projectmanager.constants.Title;
import projectmanager.domain.IExampleData;
import projectmanager.domain.Project;
import projectmanager.ui.components.ProjectListItemComponent;
import projectmanager.utils.ControllerUtils;
import projectmanager.utils.IPopulateListCallback;
import projectmanager.utils.StageUtils;

public class BrowseProjectsUIController extends ListUIBaseController {

    public IPopulateListCallback exampleProjectCallback = new IPopulateListCallback() {
            @Override
            public IExampleData generateItem(int i) {
                return projectInstanceCallback(i);
            }
        };
    
    @FXML
    protected ListView allProjectsList;
    @FXML
    protected ListView myProjectsList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allProjectsList.setCellFactory(listView -> {
            return new ProjectListItemComponent();
        });
        myProjectsList.setCellFactory(listView -> {
            return new ProjectListItemComponent();
        });
        
        populateListsWithExampleData();
    }
    
    /**
     * Development purposes only!
     * Populates project lists with automatically created example data.
     */
    private void populateListsWithExampleData() {
        // TODO: Use ListViewUtils.
        List<Project> projects = new ArrayList();
        
        for (int i = 1; i <= 10; i++) {
            Project p = new Project();
            p.createExampleInstance();
            projects.add(p);
        }
        
        fetchListData(allProjectsList, projects);
        fetchListData(myProjectsList, projects);
    }
    
    private IExampleData projectInstanceCallback(int i) {
        Project project = new Project();
        project.createExampleInstance(i + 1);
        return project;
    }
    
    @FXML
    protected void handleTerminateAppRequest(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    protected void handleGuideRequest(ActionEvent event) {
        // TODO: Open GitHub
    }
    
    @Override
    public void setTitle() {
        if (stage == null) {
            return;
        }
        
        StageUtils.setSceneTitle(stage, Title.BROWSE_PROJECTS);
    }
    
    public void openProjectForm() {
        Stage formStage = new Stage();
        formStage.setAlwaysOnTop(true);
        formStage.setHeight(320);
        formStage.setWidth(420);
        
        try {
            ControllerUtils.loadController(formStage, getClass(), FXMLPath.ADD_PROJECT);
            formStage.show();
        } catch (IOException ex) {
            Logger.getLogger(BrowseProjectsUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleNewButtonClick(ActionEvent event) {
        openProjectForm();
    }
    
    /**
     * Fetches data into given ListView
     * @param element A ListView element
     * @param data Data as List object
     */
    private void fetchListData(ListView element, List data) {
        fetchListData(element, FXCollections.observableList(data));
    }
    
    /**
     * Fetches data into given ListView
     * @param element A ListView element
     * @param data Data as ObservableList object
     */
    private void fetchListData(ListView element, ObservableList data) {
        if (element == null || data == null) {
            return;
        }
        
        element.setItems(data);
    }
}
