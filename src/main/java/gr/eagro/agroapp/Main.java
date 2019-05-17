package gr.eagro.agroapp;

import gr.eagro.agroapp.gui.ApplicationWindow;
import gr.eagro.agroapp.gui.EnumWindowLocation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {

    private static Stage window;
    private static Stack<ApplicationWindow> navigation;

    public void start(Stage primaryStage) {
        try {
            window = primaryStage;

            Parent root = FXMLLoader.load(getClass().getResource(EnumWindowLocation.MAIN_MENU_WINDOW.getLocation()));
            window.setScene(new Scene(root));
            window.setTitle("AgroApp");
            window.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        navigation = new Stack<ApplicationWindow>() {
            @Override
            public synchronized String toString() {
                StringBuilder location = new StringBuilder();

                for(ApplicationWindow item : navigation)
                    location.append(item).append(" > ");

                return location.toString();
            }
        };
    }

    @Override
    public void stop() {
        navigation.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getWindow() {
        return window;
    }

    public static Stack<ApplicationWindow> getNavigation() {
        return navigation;
    }

}
