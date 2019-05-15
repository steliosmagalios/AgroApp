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
        navbar.setText(Main.getNavigationText());
        Main.getWindow().setFocused(false);
    }

    @Override
    public String toString() {
        return title;
    }

    public void returnToMainMenu() throws Exception{
        Main.getNavigation().clear();
        Parent root = FXMLLoader.load(getClass().getResource(EnumWindowLocations.MAIN_MENU_WINDOW.getLocation()));
        Main.getWindow().setScene(new Scene(root));
    }

    public void openWindow(EnumWindowLocations window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(window.getLocation()));
        Main.getWindow().setScene(new Scene(root));
    }

    public void openPreviousWindow() throws Exception {
        Main.getNavigation().pop();
        openWindow(Main.getNavigation().pop().location);
    }
}
