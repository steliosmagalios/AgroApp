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

public abstract class ApplicationWindow implements Initializable {

    @FXML
    protected Label navbar;
    @FXML
    protected Pane backgroundPane;

    private String title;
    private EnumWindowLocation location;

    public ApplicationWindow(String title, EnumWindowLocation location) {
        this.title = title;
        this.location = location;
        Main.getNavigation().add(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navbar.setText(Main.getNavigation().toString());
        navbar.requestFocus();
    }

    @Override
    public String toString() {
        return title;
    }

    public void returnToMainMenu() {
        Main.getNavigation().clear();
        openWindow(EnumWindowLocation.MAIN_MENU_WINDOW);
    }

    public ApplicationWindow openWindow(EnumWindowLocation window) {
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
