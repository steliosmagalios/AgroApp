package gr.eagro.agroapp.gui;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.model.Calendar;
import gr.eagro.agroapp.model.CalendarEntry;
import gr.eagro.agroapp.utils.ApplicationIndexes;
import gr.eagro.agroapp.utils.ApplicationUtilities;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CalendarWindow extends ApplicationWindow {

    @FXML private ListView<CalendarEntry> dayEntries;
    @FXML private BorderPane calendarPane;

    private Calendar calendar;
    private DatePicker datePicker;

    private Map<LocalDate, ObservableList<CalendarEntry>> calendarModel;

    public CalendarWindow() {
        super("Ημερολόγιο", ApplicationWindows.CALENDAR_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.calendar = Main.getCalendar();
        this.datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        initializeCalendarModel();

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if(calendarModel.containsKey(item)) {
                            if(!calendarModel.get(item).isEmpty())
                                getStyleClass().add("selected-date-cell");
                        }
                    }
                };

            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
                dayEntries.setItems(FXCollections.observableArrayList(getEntriesFromDate(newValue)));
        });

        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node datePickerDisplayNode = datePickerSkin.getPopupContent();
        datePickerDisplayNode.getStyleClass().add("calendar-picker");
        calendarPane.setCenter(datePickerDisplayNode);

        dayEntries.setItems(FXCollections.observableArrayList(getEntriesFromDate(LocalDate.now())));
    }

    @FXML
    private void createNewEntry() {
        openEntryWindow("Προσθήκη καταχώρησης.", datePicker.getValue(), null, true);
    }

    @FXML
    private void deleteSelectedItem() {
        CalendarEntry entryToRemove = dayEntries.getSelectionModel().getSelectedItem();

        if(entryToRemove == null) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_SELECT_ENTRY);
            return;
        }
        if(!ApplicationUtilities.createConfirmation(ApplicationIndexes.CONFIRM_DELETE_ENTRY)) return;

        dayEntries.getItems().remove(entryToRemove);
        calendar.removeEntry(entryToRemove);
        calendarModel.get(entryToRemove.getDateAsLocalDate()).remove(entryToRemove);
        dayEntries.getSelectionModel().select(null);
    }

    @FXML
    private void editSelectedEntry() {
        CalendarEntry entryToEdit = dayEntries.getSelectionModel().getSelectedItem();
        if(entryToEdit == null) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_SELECT_ENTRY);
            return;
        }
        openEntryWindow("Επεξεργασία καταχώρησης.", entryToEdit.getDateAsLocalDate(), entryToEdit, false);
    }

    public void updateEnrtries(CalendarEntry newEntry, LocalDate oldDate, CalendarEntry oldEntry) {

        if(oldEntry != null && oldDate != null) {
            calendar.removeEntry(oldEntry);
            calendarModel.get(oldDate).remove(oldEntry);
        }

        calendar.addEntry(newEntry);
        if(calendarModel.containsKey(newEntry.getDateAsLocalDate()))
            calendarModel.get(newEntry.getDateAsLocalDate()).add(newEntry);
        else
            calendarModel.put(newEntry.getDateAsLocalDate(), FXCollections.observableArrayList(newEntry));
        dayEntries.setItems(calendarModel.get(newEntry.getDateAsLocalDate()));
        datePicker.setValue(newEntry.getDateAsLocalDate());
    }

    private void initializeCalendarModel() {
        calendarModel = new HashMap<>();

        for (CalendarEntry entry : calendar.getEntries())
            if (calendarModel.containsKey(entry.getDateAsLocalDate())) calendarModel.get(entry.getDateAsLocalDate()).add(entry);
            else calendarModel.put(entry.getDateAsLocalDate(), FXCollections.observableArrayList(entry));
    }

    private List<CalendarEntry> getEntriesFromDate(LocalDate date) {
        List<CalendarEntry> entries = new ArrayList<>();
        for (CalendarEntry entry : calendar.getEntries()) {
            if(entry.getDateAsLocalDate().equals(date))
                entries.add(entry);
        }
        return entries;
    }

    private void openEntryWindow(String title, LocalDate date, CalendarEntry entry, boolean disableDatePicker) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ApplicationWindows.ENTRY_CREATION_WINDOW.getLocation()));
            Parent root = loader.load();

            Stage window = new Stage();
            window.setTitle(title);
            window.setScene(new Scene(root));
            window.setResizable(false);
            EntryCreationWindow controller = loader.getController();
            controller.getData(entry, disableDatePicker, this, window, date);
            window.initOwner(Main.getWindow());
            window.getIcons().setAll(Main.getWindow().getIcons());
            window.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
