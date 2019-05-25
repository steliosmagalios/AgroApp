package gr.eagro.agroapp;

import gr.eagro.agroapp.gui.ApplicationWindow;
import gr.eagro.agroapp.gui.EnumWindowLocation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Stack;

public class Main extends Application {

    private static Stage window;
    private static Stack<ApplicationWindow> navigation;

    private static ArrayList<Plant> plants;
    private static Calendar calendar;

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

        plants = new ArrayList<>();
        plants.add(new Tree("01", new Fertilizer("00", 12f, 10.45f), "Cherry", 50));
        plants.add(new Crop("02", new Fertilizer("00", 12f, 10.45f), "Strawberry", 100));

        calendar = new Calendar(new ArrayList<>());
        calendar.addEntry(new CalendarEntry(25, 5, 2019, "Test1"));
        calendar.addEntry(new CalendarEntry(25, 5, 2019, "Test2"));
        calendar.addEntry(new CalendarEntry(26, 5, 2019, "Test3"));
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

    public static ArrayList<Plant> getPlants() {
        return plants;
    }

    public static Calendar getCalendar() {
        return calendar;
    }
}
