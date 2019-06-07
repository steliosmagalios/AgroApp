package gr.eagro.agroapp.gui;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.model.Calendar;
import gr.eagro.agroapp.model.CalendarEntry;
import gr.eagro.agroapp.utils.ApplicationIndexes;
import gr.eagro.agroapp.utils.ApplicationUtilities;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CalendarWindow extends ApplicationWindow {

    @FXML private ListView<CalendarEntry> dayEntries;
    @FXML private BorderPane calendarPane;

    private ListProperty<CalendarEntry> listProperty = new SimpleListProperty<>();

    private Calendar calendar;
    private DatePicker datePicker;

    public CalendarWindow() {
        super("Ημερολόγιο", ApplicationWindows.CALENDAR_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.calendar = Main.getCalendar();
        this.datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        for(CalendarEntry entry: calendar.getEntries())
                            if(entry.getDateAsLocalDate().equals(item))
                                getStyleClass().add("selected-date-cell");
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.valueProperty().addListener((observable, oldValue, newValue) ->
                listProperty.set(FXCollections.observableArrayList(getEntriesFromDate(newValue))));

//        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
//            if(newValue != null)
//                dayEntries.getItems().setAll(getEntriesFromDate(newValue));
//        });

        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node datePickerDisplayNode = datePickerSkin.getPopupContent();
        datePickerDisplayNode.getStyleClass().add("calendar-picker");
        calendarPane.setCenter(datePickerDisplayNode);

//        dayEntries.getItems().setAll(getEntriesFromDate(LocalDate.now()));

        listProperty.set(FXCollections.observableArrayList(getEntriesFromDate(datePicker.getValue())));
        dayEntries.itemsProperty().bind(listProperty);

    }

    @FXML
    private void createNewEntry() {
        openEntryWindow("Προσθήκη καταχώρησης.", datePicker.getValue(), null, true);

//        EntryCreationWindow.openWindow(null, datePicker.getValue());
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
        dayEntries.getSelectionModel().select(null);
    }

    @FXML
    private void editSelectedEntry() {
        CalendarEntry entryToEdit = dayEntries.getSelectionModel().getSelectedItem();
        if(entryToEdit == null) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_SELECT_ENTRY);
            return;
        }
//        openEntryWindow("Επεξεργασία καταχώρησης.", entryToEdit.getDateAsLocalDate(), entryToEdit, false);
        new EntryCreationWindow("Επεξεργασία καταχώρησης", entryToEdit);
    }

    public void updateEnrtries(CalendarEntry newEntry, LocalDate oldDate, CalendarEntry oldEntry) {



//        if(oldEntry != null && oldDate != null) {
//            calendar.removeEntry(oldEntry);
//        }
//
//        calendar.addEntry(newEntry);
//        dayEntries.getItems().setAll(calendar.getEntries());
//        datePicker.setValue(newEntry.getDateAsLocalDate());
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

        new EntryCreationWindow("Test", LocalDate.now());

    }

}
