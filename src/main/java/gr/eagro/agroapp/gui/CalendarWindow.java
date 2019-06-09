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
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CalendarWindow extends ApplicationWindow {

    @FXML private ListView<CalendarEntry> dayEntries;
    @FXML private BorderPane calendarPane;
    @FXML private Label labelActionNumber;

    private Calendar calendar;
    private DatePicker datePicker;
    private ObservableList<CalendarEntry> observableEntries;

    private EntryCreationWindow entryWindow;

    public CalendarWindow() {
        super("Ημερολόγιο", ApplicationWindows.CALENDAR_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.calendar = Main.getCalendar();
        this.datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        this.observableEntries = FXCollections.observableArrayList(calendar.getEntries());

        FilteredList<CalendarEntry> filteredList = new FilteredList<>(observableEntries, entry -> entry.getDateAsLocalDate().equals(datePicker.getValue()));
        dayEntries.setItems(filteredList);
        labelActionNumber.setText((filteredList.size() > 0) ? "Καταχωρήσεις για σήμερα: " + filteredList.size() : "Δεν υπάρχουν καταχωρήσεις.");

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

                        if(item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            getStyleClass().add("disabled-date-cell");
                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(entry -> entry.getDateAsLocalDate().equals(newValue));
            labelActionNumber.setText((filteredList.size() > 0) ? "Καταχωρήσεις για σήμερα: " + filteredList.size() : "Δεν υπάρχουν καταχωρήσεις.");
        });


        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node datePickerDisplayNode = datePickerSkin.getPopupContent();
        datePickerDisplayNode.getStyleClass().add("calendar-picker");
        calendarPane.setCenter(datePickerDisplayNode);
    }

    @FXML
    private void createNewEntry() {
        new EntryCreationWindow("Προσθήκη Καταχώρησης", datePicker.getValue(), observableEntries);
    }

    @FXML
    private void deleteSelectedItem() {
        CalendarEntry entryToRemove = dayEntries.getSelectionModel().getSelectedItem();

        if(entryToRemove == null) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_SELECT_ENTRY);
            return;
        }
        if(!ApplicationUtilities.createConfirmation(ApplicationIndexes.CONFIRM_DELETE_ENTRY)) return;

        calendar.removeEntry(entryToRemove);
        observableEntries.setAll(calendar.getEntries());
        dayEntries.getSelectionModel().select(null);
    }

    @FXML
    private void editSelectedEntry() {
        CalendarEntry entryToEdit = dayEntries.getSelectionModel().getSelectedItem();
        if(entryToEdit == null) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_SELECT_ENTRY);
            return;
        }
        new EntryCreationWindow("Επεξεργασία καταχώρησης", entryToEdit, observableEntries);
    }
}
