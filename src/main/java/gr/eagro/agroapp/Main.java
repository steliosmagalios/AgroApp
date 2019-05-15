package gr.eagro.agroapp;

import gr.eagro.agroapp.gui.EnumWindowLocations;
import gr.eagro.agroapp.gui.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {

    private static Stage window;
    private static Stack<Window> navigation = new Stack<>();

    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource(EnumWindowLocations.MAIN_MENU_WINDOW.getLocation()));
        window.setScene(new Scene(root));
        window.setTitle("AgroApp");
        window.show();
    }

    public static void main(String[] args) {
        navigation = new Stack<>();
        launch(args);
    }

    public static Stage getWindow() {
        return window;
    }

    public static Stack<Window> getNavigation() {
        return navigation;
    }

    public static String getNavigationText() {
        StringBuilder location = new StringBuilder();

        for(Window item : navigation)
            location.append(item).append(" > ");

        return location.toString();
    }
}
