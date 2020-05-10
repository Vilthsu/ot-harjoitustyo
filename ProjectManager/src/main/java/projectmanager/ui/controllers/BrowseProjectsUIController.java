package projectmanager.ui.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import projectmanager.constants.*;
import projectmanager.domain.*;
import projectmanager.services.ProjectService;
import projectmanager.services.SessionService;
import projectmanager.ui.components.ProjectListItemComponent;
import projectmanager.utils.*;

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
    @FXML
    protected Tab browseAllProjectsTab;
    @FXML
    protected Tab myProjectsTab;
    @FXML
    protected Button deleteProjectBtn;
    @FXML
    protected Button deleteMyProjectBtn;
    @FXML
    protected Button leaveMyProjectBtn;
    @FXML
    protected Button joinProjectBtn;
    @FXML
    protected Button leaveProjectBtn;

    protected ProjectService projectService;

    protected Project selectedProject;

    public BrowseProjectsUIController() {
        super();

        projectService = new ProjectService();
        try {
            initUserProjects();
        } catch (SQLException ex) {
            AlertUtils.showErrorAlert("An error occurred while fetching your projects.");
        }
    }

    private void initUserProjects() throws SQLException {
        User user = SessionService.getLoggedUser();
        user.projects = projectService.listAll(user);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toggleAdminControls();
        initializeLists();
        listenTabChangeEvent();
    }

    private void toggleAdminControls() {
        User user = SessionService.getLoggedUser();

        deleteProjectBtn.setVisible(UserUtils.isAdmin(user));
    }

    private void initializeLists() {
        allProjectsList.setCellFactory(listView -> {
            return new ProjectListItemComponent();
        });
        myProjectsList.setCellFactory(listView -> {
            return new ProjectListItemComponent();
        });

        try {
            populateListsWithData();
        } catch (SQLException ex) {
            AlertUtils.showErrorAlert("An error occurred while performing a database operation.");
        }

        disableRowButtons();
        listenSelectionEvent();
    }

    private void listenSelectionEvent() {
        ListView[] listViews = new ListView[]{allProjectsList, myProjectsList};

        for (ListView listView : listViews) {
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Project>() {
                @Override
                public void changed(ObservableValue<? extends Project> value, Project prev, Project project) {
                    selectedProject = project;

                    if (!deleteMyProjectBtn.isVisible() || !leaveMyProjectBtn.isVisible()) {
                        disableEnableRowEvents(true);
                    }

                    boolean enableLeaving = isProjectInMyProjects(project);
                    joinProjectBtn.setVisible(!enableLeaving);
                    leaveProjectBtn.setVisible(enableLeaving);
                    leaveMyProjectBtn.setVisible(enableLeaving);

                    deleteProjectBtn.setVisible(UserUtils.isAdmin(SessionService.getLoggedUser()));
                }
            });
        }
    }

    private boolean isProjectInMyProjects(Project project) {
        User user = SessionService.getLoggedUser();
        return user.projects.contains(project);
    }

    private void listenTabChangeEvent() {
        Tab[] tabs = new Tab[]{browseAllProjectsTab, myProjectsTab};

        for (Tab tab : tabs) {
            tab.setOnSelectionChanged(new EventHandler() {
                @Override
                public void handle(Event t) {
                    initializeLists();
                }
            });
        }
    }

    private void disableRowButtons() {
        selectedProject = null;
        disableEnableRowEvents(false);

        deleteProjectBtn.setVisible(false);
        joinProjectBtn.setVisible(false);
        leaveProjectBtn.setVisible(false);
        leaveMyProjectBtn.setVisible(false);
    }

    private void disableEnableRowEvents(boolean state) {
        Button[] buttons = new Button[]{deleteMyProjectBtn};

        for (Button button : buttons) {
            button.setVisible(state);
        }
    }

    /**
     * Development purposes only! Populates project lists with automatically
     * created example data.
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

    /**
     * Fetches data to the lists.
     */
    private void populateListsWithData() throws SQLException {
        // TODO: Use ListViewUtils.
        fetchListData(allProjectsList, projectService.listAll());
        fetchListData(myProjectsList, projectService.listAll(SessionService.getLoggedUser()));
    }

    private IExampleData projectInstanceCallback(int i) {
        Project project = new Project();
        project.createExampleInstance(i + 1);
        return project;
    }

    @FXML
    protected void handleJoinProjectAction(ActionEvent event) {
        try {
            projectService.joinProject(selectedProject);
        } catch (SQLException ex) {
            AlertUtils.showErrorAlert("Tapahtui virhe liityttäessä projektiin.");
        } finally {
            quickRefreshUserProjects();
        }
    }

    @FXML
    protected void handleLeaveProjectAction(ActionEvent event) {
        try {
            projectService.leaveProject(selectedProject);
        } catch (SQLException ex) {
            AlertUtils.showErrorAlert("Tapahtui virhe poistuttaessa projektista.");
        } finally {
            quickRefreshUserProjects();
        }
    }

    @FXML
    protected void handleDeleteProjectAction(ActionEvent event) {
        Alert alert = AlertUtils.showConfirmationAlert("Haluatko varmasti poistaa valitun projektin?");

        if (!alert.getResult().equals(ButtonType.OK)) {
            return;
        }

        try {
            projectService.delete(selectedProject);
        } catch (SQLException ex) {
            AlertUtils.showErrorAlert("Tapahtui virhe poistettaessa projektista.");
        } finally {
            quickRefreshUserProjects();
        }
    }
    
    private void quickRefreshUserProjects() {
        try {
            initUserProjects();
        } catch (SQLException ex) {
            AlertUtils.showErrorAlert("Tapahtui virhe virkistettäessä omia projekteja.");
        }
    }

    @FXML
    protected void handleLogoutRequest(ActionEvent event) {
        SessionService.destroy();
        backToLogin();
    }

    @FXML
    protected void handleTerminateAppRequest(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void handleGuideRequest(ActionEvent event) {
        if (!Desktop.isDesktopSupported()) {
            return;
        }

        Desktop desktop = Desktop.getDesktop();

        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI("https://github.com/Vilthsu/ot-harjoitustyo/blob/master/dokumentaatio/kaytto-ohje.md"));
            } catch (IOException | URISyntaxException ex) {
                AlertUtils.showErrorAlert("Tapahtui virhe avattaessa selainta.");
            }
        }
    }

    @Override
    public void setTitle() {
        if (stage == null) {
            return;
        }

        StageUtils.setSceneTitle(stage, Title.BROWSE_PROJECTS);
    }

    public void backToLogin() {
        try {
            ControllerUtils.loadController(stage, getClass(), FXMLPath.LOGIN);
            stage.show();
        } catch (IOException ex) {
            AlertUtils.showErrorAlert("Tapahtui virhe palattaessa kirjautumisikkunaan.");
        }
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
            AlertUtils.showErrorAlert("Tapahtui virhe avattaessa projektin lisäysikkunaa.");
        }
    }

    @FXML
    private void handleNewButtonClick(ActionEvent event) {
        openProjectForm();
    }

    /**
     * Fetches data into given ListView
     *
     * @param element A ListView element
     * @param data Data as List object
     */
    private void fetchListData(ListView element, List data) {
        fetchListData(element, FXCollections.observableList(data));
    }

    /**
     * Fetches data into given ListView
     *
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
