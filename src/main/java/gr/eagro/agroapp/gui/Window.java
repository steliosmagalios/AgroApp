package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Window implements Initializable {

    @FXML
    protected Label navbar;
    @FXML
    protected Pane backgroundPane;

    private String title;
    private EnumWindowLocations location;

    public Window(String title, EnumWindowLocations location) {
        this.title = title;
        this.location = location;
        Main.getNavigation().add(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navbar.setText(Main.getNavigation().toString());
        Main.getWindow().setFocused(false);
    }

    @Override
    public String toString() {
        return title;
    }

    public void returnToMainMenu() {
        Main.getNavigation().clear();
        openWindow(EnumWindowLocations.MAIN_MENU_WINDOW);
    }

    public Window openWindow(EnumWindowLocations window) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(window.getLocation()));
            Parent root = loader.load();
            Main.getWindow().setScene(new Scene(root));
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void openPreviousWindow() {
        Main.getNavigation().pop();
        openWindow(Main.getNavigation().pop().location);
    }
}
