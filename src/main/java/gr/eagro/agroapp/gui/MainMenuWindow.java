package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenuWindow extends Window {

    public MainMenuWindow() {
        super("Αρχική");
    }

    public void openFertilizerWindow() {
        try {
            Parent root = getWindow(EnumWindowLocations.FERTILIZER_WINDOW.getLocation());
            Main.getWindow().setScene(new Scene(root));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openDiseasesWindow() {
        try {
            Parent root = getWindow(EnumWindowLocations.DISEASES_WINDOW.getLocation());
            Main.getWindow().setScene(new Scene(root));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openHybridsWindow() {
        try {
            Parent root = getWindow(EnumWindowLocations.HYBRIDS_WINDOW.getLocation());
            Main.getWindow().setScene(new Scene(root));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openCalendarWindow() {
        try {
            Parent root = getWindow(EnumWindowLocations.CALENDAR_WINDOW.getLocation());
            Main.getWindow().setScene(new Scene(root));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openStatisticsWindow() {
        try {
            Parent root = getWindow(EnumWindowLocations.STATISTICS_ADD_WINDOW.getLocation());
            Main.getWindow().setScene(new Scene(root));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Parent getWindow(String location) throws IOException {
        return FXMLLoader.load(getClass().getResource(location));
    }
}
