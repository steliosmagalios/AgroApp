package gr.eagro.agroapp.gui;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import gr.eagro.agroapp.CalendarEntry;
import gr.eagro.agroapp.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CalendarWindow extends ApplicationWindow {

    @FXML private BorderPane calendarPane;
    @FXML private ListView<CalendarEntry> dayEntries;

    private DatePickerSkin datePickerSkin;
    private DatePicker datePicker;

    public CalendarWindow() {
        super("Ημερολόγιο", EnumWindowLocation.CALENDAR_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        datePicker = new DatePicker(LocalDate.now());
        datePicker.setShowWeekNumbers(false);

        datePickerSkin = new DatePickerSkin(datePicker);
        Node node = datePickerSkin.getPopupContent();
        node.getStyleClass().add("calendar-picker");

        calendarPane.setCenter(node);
        datePicker.valueProperty().addListener((observable, oldValue, newValue) ->
                dayEntries.setItems(FXCollections.observableArrayList(getEntriesFromDate(newValue))));

        dayEntries.setItems(FXCollections.observableArrayList(getEntriesFromDate(LocalDate.now())));
    }

    private List<CalendarEntry> getEntriesFromDate(LocalDate date) {
        List<CalendarEntry> entries = new ArrayList<>();

        for(CalendarEntry entry: Main.getCalendar().getEntries()) {
            LocalDate entryDate = LocalDate.of(entry.getYear(), entry.getMonth(), entry.getDay());

            if(entryDate.equals(date))
                entries.add(entry);
        }

        return entries;
    }
}
