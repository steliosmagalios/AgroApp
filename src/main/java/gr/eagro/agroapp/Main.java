package gr.eagro.agroapp;

import gr.eagro.agroapp.gui.ApplicationWindow;
import gr.eagro.agroapp.model.*;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main extends Application {

    private static final String SAVE_FILE_LOCATION = "";
    private static final String SAVE_FILE_NAME = "AgroApp.dat";

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
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        loadObjects();
//        manualInit();

        navigation = new Stack<>();

        //remove entries from older days
        List<CalendarEntry> oldEntries = new ArrayList<>();
        for(CalendarEntry entry : calendar.getEntries()) {
            if (entry.getDateAsLocalDate().isBefore(LocalDate.now()))
                oldEntries.add(entry);
        }
        oldEntries.forEach(calendar::removeEntry);

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
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(new File(SAVE_FILE_LOCATION + SAVE_FILE_NAME)));
            plants = ((ArrayList<Plant>) reader.readObject());
            calendar = ((Calendar) reader.readObject());
            statistics = ((Statistics) reader.readObject());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    private void saveObjects() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(new File(SAVE_FILE_LOCATION + SAVE_FILE_NAME)));
            writer.writeObject(plants);
            writer.writeObject(calendar);
            writer.writeObject(statistics);
            writer.close();
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

        Crop hybBereket = new Crop("bereket", cropCucumberFert, "Bereket", true, 1);
        Crop hybMakedon = new Crop("makedon", cropCucumberFert, "Μακεδών", true, 1);
        Crop hybTrimax = new Crop("trimax", cropCucumberFert, "Trimax", true, 1);

        cropCucumber.addHyrbid(hybBereket);
        cropCucumber.addHyrbid(hybMakedon);
        cropCucumber.addHyrbid(hybTrimax);



        Crop cropBean = new Crop("cropbean", cropBeanFert, "Φασολιά", false, 1);

        Crop hybPirgetos = new Crop("pirgetos", cropBeanFert, "Πυργετός", true, 1);
        Crop hybRapsani = new Crop("rapsani", cropBeanFert, "Ραψάνη", true,1);
        Crop hybSemeli = new Crop("semeli", cropBeanFert, "Σέμελη", true, 1);

        cropBean.addHyrbid(hybPirgetos);
        cropBean.addHyrbid(hybRapsani);
        cropBean.addHyrbid(hybSemeli);



        Crop cropPumpkin = new Crop("croppumpkin", cropPumpkinFert, "Κολοκυθιά", false, 1);

        Crop hybAngelina = new Crop("angelina", cropPumpkinFert, "Angelina", true, 1);
        Crop hybAstro = new Crop("astro", cropPumpkinFert, "Άστρο", true, 1);
        Crop hybClarion = new Crop("clarion", cropPumpkinFert, "Clarion", true, 1);

        cropPumpkin.addHyrbid(hybAngelina);
        cropPumpkin.addHyrbid(hybAstro);
        cropPumpkin.addHyrbid(hybClarion);



        Tree treeCherry = new Tree("treecherry", treeCherryFert, "Κερασιά", false, 1);

        Tree hybGisela5 = new Tree("gisela5", treeCherryFert, "Gisela 5", true, 1);
        Tree hybGisela6 = new Tree("gisela6", treeCherryFert, "Gisela 6", true, 1);
        Tree hybPrunus = new Tree("prunusmahaleb", treeCherryFert, "Prunus Mahaleb", true, 1);

        treeCherry.addHyrbid(hybGisela5);
        treeCherry.addHyrbid(hybGisela6);
        treeCherry.addHyrbid(hybPrunus);



        Tree treeLemon = new Tree("treelemon", treeLemonFert, "Λεμονιά", false, 1);

        Tree hybEureka = new Tree("eureka", treeLemonFert, "Eureka", true, 1);
        Tree hybLapith = new Tree("lapithiotiki", treeLemonFert, "Λαπιθιώτικη", true, 1);
        Tree hybPolofori = new Tree("polifori", treeLemonFert, "Πολύφορη", true, 1);

        treeLemon.addHyrbid(hybEureka);
        treeLemon.addHyrbid(hybLapith);
        treeLemon.addHyrbid(hybPolofori);



        Tree treeOrange = new Tree("treeorange", treeOrangeFert, "Πορτοκαλιά", false, 1);

        Tree hybJaffa = new Tree("jaffa", treeOrangeFert, "Jaffa", true, 1);
        Tree hybMoro = new Tree("moro", treeOrangeFert, "Moro", true, 1);
        Tree hybSanguinelli = new Tree("sanguinelli", treeCherryFert, "Sanguinelli", true, 1);

        treeOrange.addHyrbid(hybJaffa);
        treeOrange.addHyrbid(hybMoro);
        treeOrange.addHyrbid(hybSanguinelli);

        Disease askochitosi = new Disease("Ασκοχίτωση", "askochitosi");
        Disease sklirotinia = new Disease("Σκληρωτίνια", "sklirotinia");
        Disease votritis = new Disease("Βοτρύτης", "votritis");

        Disease oidio = new Disease("Ωίδιο", "oidio");
        Disease peronosporos = new Disease("Περονόσπορος", "peronosporos");
        Disease pythiumspp = new Disease("Pythium SPP", "pythiumspp");

        Disease afides = new Disease("Αφίδες", "afides");
        Disease anthrakosi = new Disease("Ανθράκωση", "anthrakosi");
        Disease monilialaxa = new Disease("Monilia Laxa", "monilialaxa");

        Disease fitofthora = new Disease("Φυτοθρώρα", "fitofthora");
        Disease korifoksira = new Disease("Κωρυφοξήρα", "korifoksira");
        Disease kommiosi = new Disease("Κομμίωση", "kommiosi");

        Disease tristetsa = new Disease("Τριστέτσα", "tristetsa");


        cropBean.addDisease(askochitosi);
        cropBean.addDisease(sklirotinia);
        cropBean.addDisease(votritis);

        cropCucumber.addDisease(oidio);
        cropCucumber.addDisease(peronosporos);
        cropCucumber.addDisease(pythiumspp);

        cropPumpkin.addDisease(peronosporos);
        cropPumpkin.addDisease(sklirotinia);
        cropPumpkin.addDisease(votritis);

        treeCherry.addDisease(afides);
        treeCherry.addDisease(anthrakosi);
        treeCherry.addDisease(monilialaxa);

        treeLemon.addDisease(anthrakosi);
        treeLemon.addDisease(fitofthora);
        treeLemon.addDisease(korifoksira);

        treeOrange.addDisease(anthrakosi);
        treeOrange.addDisease(kommiosi);
        treeOrange.addDisease(tristetsa);

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
