package org.app.donationstream;

import javafx.scene.control.Alert;

public class Alerts {
    public static void createAndShowWarning(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String alertWarning = RunApplication.resourceBundle.getString("alertWarning");
        alert.setTitle(alertWarning);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    public static void createAndShowError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String alertError = RunApplication.resourceBundle.getString("alertError");
        alert.setTitle(alertError);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
