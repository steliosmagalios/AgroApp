package gr.eagro.agroapp.gui;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import gr.eagro.agroapp.Calendar;
import gr.eagro.agroapp.CalendarEntry;
import gr.eagro.agroapp.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CalendarWindow extends ApplicationWindow {

    @FXML private BorderPane calendarPane;
    @FXML private ListView<CalendarEntry> dayEntries;

    private static Set<LocalDate> dates;
    private static Calendar calendar;

    private DatePicker datePicker;

    public CalendarWindow() {
        super("Ημερολόγιο", EnumWindowLocation.CALENDAR_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        calendar = Main.getCalendar();
        dates = new HashSet<>();

        for(CalendarEntry entry: calendar.getEntries()) {
            dates.add(LocalDate.of(entry.getYear(), entry.getMonth(), entry.getDay()));
        }

        datePicker = new DatePicker(LocalDate.now());
        datePicker.setShowWeekNumbers(false);

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if(dates.contains(item))
                            getStyleClass().add("selected-date-cell");
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);

        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node node = datePickerSkin.getPopupContent();
        node.getStyleClass().add("calendar-picker");

        calendarPane.setCenter(node);
        datePicker.valueProperty().addListener((observable, oldValue, newValue) ->
                dayEntries.setItems(FXCollections.observableArrayList(getEntriesFromDate(newValue))));

        dayEntries.setItems(FXCollections.observableArrayList(getEntriesFromDate(LocalDate.now())));
    }

    private List<CalendarEntry> getEntriesFromDate(LocalDate date) {
        List<CalendarEntry> entries = new ArrayList<>();

        for(CalendarEntry entry: calendar.getEntries()) {
            LocalDate entryDate = LocalDate.of(entry.getYear(), entry.getMonth(), entry.getDay());

            if(entryDate.equals(date))
                entries.add(entry);
        }

        return entries;
    }

    public void deleteSelectedItem() {
        CalendarEntry entryToRemove = dayEntries.getSelectionModel().getSelectedItem();

        if(entryToRemove == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(WarningIndex.WARNING_SELECT_ENTRY);
            alert.show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setContentText("Θέλετε σίγουρα να διαγράψετε αυτή την καταχώρηση;");
        confirm.setTitle("Διαγραφή Καταχώρησης");
        Optional<ButtonType> result = confirm.showAndWait();
        if(result.isPresent() && !result.get().equals(ButtonType.OK)) return;

        dates.remove(LocalDate.of(entryToRemove.getYear(), entryToRemove.getMonth(), entryToRemove.getDay()));
        dayEntries.getItems().remove(entryToRemove);
        dayEntries.getSelectionModel().select(null);
        calendar.getEntries().remove(entryToRemove);
    }

    public void createNewEntry() {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(EnumWindowLocation.ENTRY_CREATION_WINDOW.getLocation()));
            Parent root = loader.load();

            EntryCreationWindow controller = loader.getController();
            controller.getData(stage, datePicker.getValue());

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void fetchEntry(CalendarEntry entry) {
        calendar.getEntries().add(entry);
        dates.add(LocalDate.of(entry.getYear(), entry.getMonth(), entry.getDay()));
    }
}
