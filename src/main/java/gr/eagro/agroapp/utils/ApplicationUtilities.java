package gr.eagro.agroapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ApplicationUtilities {

    public static void createWarning(String displayText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(displayText);
        alert.showAndWait();
    }

    public static void createInformation(String displayText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(displayText);
        alert.showAndWait();
    }

//    public static void createError(String displayText) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setContentText(displayText);
//        alert.showAndWait();
//    }

    public static boolean createConfirmation(String displayText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(displayText);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent() && result.get().equals(ButtonType.OK));
    }



}
