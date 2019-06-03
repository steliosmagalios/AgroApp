package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Calendar;
import gr.eagro.agroapp.CalendarEntry;
import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.utils.ApplicationIndexes;
import gr.eagro.agroapp.utils.ApplicationUtilities;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainMenuWindow extends ApplicationWindow {

    @FXML private ImageView bellImg;
    @FXML private Button notificationBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        List<CalendarEntry> entriesToNotify = findEntries();
        if(entriesToNotify != null) {
            bellImg.setImage(new Image(getClass().getResourceAsStream("/images/alarmOn.png")));

            notificationBtn.setTooltip((entriesToNotify.size() > 1) ? new Tooltip("Έχετε " + entriesToNotify.size() + " ενέργειες για σήμερα.") : new Tooltip("Έχετε " + entriesToNotify.size() + " ενέργεια για σήμερα."));
            notificationBtn.setOnAction(event -> {
                BorderPane pane = new BorderPane();
                ListView<CalendarEntry> stack = new ListView<>();
                stack.setStyle("-fx-font-size: 18px;");
                stack.setItems(FXCollections.observableArrayList(entriesToNotify));
                pane.setCenter(stack);

                Scene scene = new Scene(pane, 400, 520);

                Stage window = new Stage();
                window.setResizable(false);
                window.setScene(scene);
                window.getIcons().add(new Image(getClass().getResourceAsStream("/images/titleIcon.png")));
                window.setTitle("Σημερινές Ενέργειες [" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+ "]");
                window.setMinWidth(250);
                window.setMinHeight(400);
                window.initOwner(Main.getWindow());
                window.show();
            });
        }
        else {
            notificationBtn.setOnAction(event -> ApplicationUtilities.createInformation(ApplicationIndexes.WARNING_NO_ENTRIES));
            notificationBtn.setTooltip(new Tooltip("Δεν έχετε ενέργειες για σήμερα."));
        }
    }

    public MainMenuWindow() {
        super("Αρχική", ApplicationWindows.MAIN_MENU_WINDOW);
    }

    public void openFertilizerWindow() {
        openWindow(ApplicationWindows.FERTILIZER_WINDOW);
    }

    public void openDiseasesWindow() {
        openWindow(ApplicationWindows.DISEASES_WINDOW);
    }

    public void openHybridsWindow() {
        openWindow(ApplicationWindows.HYBRIDS_WINDOW);
    }

    public void openCalendarWindow() {
        openWindow(ApplicationWindows.CALENDAR_WINDOW);
    }

    public void openStatisticsWindow() {
        openWindow(ApplicationWindows.STATISTICS_ADD_WINDOW);
    }

    private List<CalendarEntry> findEntries() {
        Calendar calendar = Main.getCalendar();
        LocalDate today = LocalDate.now();

        List<CalendarEntry> entries = new ArrayList<>();
        calendar.getEntries().forEach(entry -> {
            if (entry.getDateAsLocalDate().equals(today))
                entries.add(entry);
        });
        if (!entries.isEmpty()) return entries;
        return null;
    }
}
