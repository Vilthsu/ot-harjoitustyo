package projectmanager.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class AlertUtils {
    public static Alert showAlert(AlertType type, String content) {
        Alert alert = new Alert(type);
        alert.setContentText(content);
        alert.show();
        
        return alert;
    }
    
    public static void showErrorAlert(String content) {
        showAlert(AlertType.ERROR, content);
    }
    
    public static Alert showConfirmationAlert(String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(content);
        alert.showAndWait();
        
        return alert;
    }
    
    public static void showInformationAlert(String content) {
        showAlert(AlertType.INFORMATION, content);
    }
    
    public static void showPlainAlert(String content) {
        showAlert(AlertType.NONE, content);
    }
    
    public static void showWarningAlert(String content) {
        showAlert(AlertType.WARNING, content);
    }
}
