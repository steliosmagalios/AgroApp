package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Window implements Initializable {

    @FXML
    protected Label navbar;

    private String title;

    public Window(String title) {
        this.title = title;
        Main.getNavigation().add(title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navbar.setText(Main.getNavigationText());
    }

    @Override
    public String toString() {
        return title;
    }

    public void returnToMainMenu() throws Exception{
        Main.getNavigation().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/MainMenuWindowView.fxml"));
        Main.getWindow().setScene(new Scene(root));
    }
}
