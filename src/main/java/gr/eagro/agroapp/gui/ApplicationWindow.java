package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.utils.ApplicationIndexes;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ApplicationWindow implements Initializable {

    @FXML protected Pane backgroundPane;
    @FXML protected HBox navigationHBox;

    private String title;
    private ApplicationWindows location;

    public ApplicationWindow(String title, ApplicationWindows location) {
        this.title = title;
        this.location = location;
        Main.getNavigation().add(this);
        Main.getWindow().setTitle(ApplicationIndexes.WINDOW_TITLE + " - " + title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.getNavigation().forEach(applicationWindow -> {
            Button btn = new Button(applicationWindow.title);
            btn.setOnAction(event -> {
                if(Main.getNavigation().peek().equals(applicationWindow)) return;

                ApplicationWindow window;
                do {
                    window = Main.getNavigation().pop();
                }while(window != applicationWindow);
                openWindow(applicationWindow.location);
            });
            btn.getStyleClass().add("navigation-btn");
            btn.setCursor(Cursor.HAND);

            Label spacer = new Label(">");
            spacer.getStyleClass().add("navigation-spacer");

            navigationHBox.getChildren().addAll(btn, spacer);
        });
    }

    public void returnToMainMenu() {
        Main.getNavigation().clear();
        openWindow(ApplicationWindows.MAIN_MENU_WINDOW);
    }

    public ApplicationWindow openWindow(ApplicationWindows window) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(window.getLocation()));
            Parent root = loader.load();
            Main.getWindow().setScene(new Scene(root, 1280, 720));
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ApplicationWindow openPreviousWindow() {
        Main.getNavigation().pop();
        return openWindow(Main.getNavigation().pop().location);
    }
}
