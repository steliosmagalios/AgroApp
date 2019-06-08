package gr.eagro.agroapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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

    public static boolean createConfirmation(String displayText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(displayText);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent() && result.get().equals(ButtonType.OK));
    }

    public static String readFile(InputStream stream) {

        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            // TODO: 07-Jun-19 change
            System.out.println("Info not found.");
        } catch (NullPointerException e) {
            builder.append("Δεν βρέθηκαν πληροφορίες.");
        }
        return builder.toString();
    }



}
