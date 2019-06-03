package gr.eagro.agroapp;

import gr.eagro.agroapp.gui.ApplicationWindow;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

            Parent root = FXMLLoader.load(getClass().getResource(ApplicationWindows.MAIN_MENU_WINDOW.getLocation()));
            window.setScene(new Scene(root));
            window.setResizable(false);
            window.sizeToScene();
            window.getIcons().add(new Image(getClass().getResourceAsStream("/images/titleIcon.png")));
            window.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        loadObjects();
//        manualInit();

        navigation = new Stack<>();
    }

    @Override
    public void stop() {
        saveObjects();

        navigation.clear();
        plants.clear();
        calendar.getEntries().clear();
        statistics.getProductionGraphData().clear();
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

    private void manualInit() {
        plants = new ArrayList<>();


        Fertilizer cropCucumberFert = new Fertilizer("cropcucumberfert", 215, 215*0.8);
        Fertilizer cropPumpkinFert = new Fertilizer("croppumpkinfert", 180, 180*0.7);
        Fertilizer cropBeanFert = new Fertilizer("cropbeanfert", 160, 160*1.5);

        Fertilizer treeCherryFert = new Fertilizer("treecherryfert", 40, 40);
        Fertilizer treeLemonFert = new Fertilizer("treelemonfert", 50, 50*1.3);
        Fertilizer treeOrangeFert = new Fertilizer("treeorangefert", 60, 60*0.9);


        Crop cropCucumber = new Crop("cropcucumber", cropCucumberFert, "Αγγουριά", false, 1);
        Crop cropBean = new Crop("cropbean", cropBeanFert, "Φασολιά", false, 1);
        Crop cropPumpkin = new Crop("croppumpkin", cropPumpkinFert, "Κολοκυθιά", false, 1);

        Tree treeCherry = new Tree("treecherry", treeCherryFert, "Κερασιά", false, 1);
        Tree treeLemon = new Tree("treelemon", treeLemonFert, "Λεμονιά", false, 1);
        Tree treeOrange = new Tree("treeorange", treeOrangeFert, "Πορτοκαλιά", false, 1);



        plants.add(cropBean);
        plants.add(cropPumpkin);
        plants.add(cropCucumber);

        plants.add(treeOrange);
        plants.add(treeCherry);
        plants.add(treeLemon);

        calendar = new Calendar();
        statistics = new Statistics();

    }
}
