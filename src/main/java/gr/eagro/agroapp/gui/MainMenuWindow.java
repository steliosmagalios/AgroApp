package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenuWindow extends Window {

    public MainMenuWindow() {
        super("Αρχική", EnumWindowLocations.MAIN_MENU_WINDOW);
    }

    public void openFertilizerWindow() {
        openWindow(EnumWindowLocations.FERTILIZER_WINDOW);
    }

    public void openDiseasesWindow() {
        openWindow(EnumWindowLocations.DISEASES_WINDOW);
    }

    public void openHybridsWindow() {
        openWindow(EnumWindowLocations.HYBRIDS_WINDOW);
    }

    public void openCalendarWindow() {
        openWindow(EnumWindowLocations.CALENDAR_WINDOW);
    }

    public void openStatisticsWindow() {
        openWindow(EnumWindowLocations.STATISTICS_ADD_WINDOW);
    }
}
