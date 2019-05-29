package gr.eagro.agroapp;

import gr.eagro.agroapp.gui.ApplicationWindow;
import gr.eagro.agroapp.gui.EnumWindowLocation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Main extends Application {

    private static final String SAVE_FILE_LOCATION = "";
    private static final String SAVE_FILE_NAME = "agroapp.dat";

    private static Stage window;
    private static Stack<ApplicationWindow> navigation;

    private static ArrayList<Plant> plants;
    private static Calendar calendar;
    private static Statistics statistics;

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
        loadObjects();
//        manualInit();

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

    private void manualInit() {
        plants = new ArrayList<>();
        plants.add(new Tree("treecherry", new Fertilizer("treecherryfert", 12f, 10.45f), "Cherry", 50));
        plants.add(new Crop("cropcuccumber", new Fertilizer("cropcuccumberfert", 12f, 10.45f), "Cuccumber", 100));

        plants.forEach(plant -> {
            for(int i=0;i<10;i++)
                plant.addDisease(Integer.toString(i));
        });

        calendar = new Calendar(new ArrayList<>());
        calendar.addEntry(new CalendarEntry(25, 5, 2019, "Test1"));
        calendar.addEntry(new CalendarEntry(25, 5, 2019, "Test2"));
        calendar.addEntry(new CalendarEntry(26, 5, 2019, "Test3"));
        calendar.addEntry(new CalendarEntry(1, 6, 2019, "Test4"));

        statistics = new Statistics();


        Main.getStatistics().getIncomeGraphData().put(2018+1,(200+23.0));
        Main.getStatistics().getIncomeGraphData().put(2018+2,(200+14.0));
        Main.getStatistics().getIncomeGraphData().put(2018+8,(200+45.0));
        Main.getStatistics().getIncomeGraphData().put(2018+4,(200+24.0));
        Main.getStatistics().getIncomeGraphData().put(2018+5,(200+34.0));
        Main.getStatistics().getIncomeGraphData().put(2018+6,(200+36.0));

        Main.getStatistics().getIncomeGraphData().put(2018+3,(200+15.0));
        Main.getStatistics().getIncomeGraphData().put(2018+7,(200+22.0));

        Main.getStatistics().getIncomeGraphData().put(2018+9,(200+43.0));
        Main.getStatistics().getIncomeGraphData().put(2018+10,(200+ 17.0));
        Main.getStatistics().getIncomeGraphData().put(2018+11,(200+ 29.0));
        Main.getStatistics().getIncomeGraphData().put(2018+12,(200+ 25.0));


        Main.getStatistics().getQuantityGraphData().put(2018+1,(200+23.0));
        Main.getStatistics().getQuantityGraphData().put(2018+2,(200+14.0));
        Main.getStatistics().getQuantityGraphData().put(2018+3,(200+15.0));
        Main.getStatistics().getQuantityGraphData().put(2018+4,(200+24.0));
        Main.getStatistics().getQuantityGraphData().put(2018+5,(200+34.0));
        Main.getStatistics().getQuantityGraphData().put(2018+6,(200+36.0));
        Main.getStatistics().getQuantityGraphData().put(2018+7,(200+22.0));
        Main.getStatistics().getQuantityGraphData().put(2018+8,(200+45.0));
        Main.getStatistics().getQuantityGraphData().put(2018+9,(200+43.0));
        Main.getStatistics().getQuantityGraphData().put(2018+10,(200+ 17.0));
        Main.getStatistics().getQuantityGraphData().put(2018+11,(200+ 29.0));
        Main.getStatistics().getQuantityGraphData().put(2018+12,(200+ 25.0));

    }

    @Override
    public void stop() {
        saveObjects();

        navigation.clear();
        plants.clear();
        calendar.getEntries().clear();
        statistics.getQuantityGraphData().clear();
        statistics.getIncomeGraphData().clear();
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

    public static Statistics getStatistics() {
        return statistics;
    }

    @SuppressWarnings("unchecked")
    private void loadObjects() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(SAVE_FILE_LOCATION + SAVE_FILE_NAME)));
            plants = ((ArrayList<Plant>) objectInputStream.readObject());
            calendar = ((Calendar) objectInputStream.readObject());
            statistics = ((Statistics) objectInputStream.readObject());
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    private void saveObjects() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(SAVE_FILE_LOCATION + SAVE_FILE_NAME)));
            objectOutputStream.writeObject(plants);
            objectOutputStream.writeObject(calendar);
            objectOutputStream.writeObject(statistics);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
