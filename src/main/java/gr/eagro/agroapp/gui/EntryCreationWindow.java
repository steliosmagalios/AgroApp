package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.CalendarEntry;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EntryCreationWindow {

    @FXML private DatePicker dateSelector;
    @FXML private TextField fieldTitle;

    private Stage window;
    private CalendarEntry entry;

    public void createNewEntry() {
        LocalDate date = dateSelector.getValue();

        if(entry == null) {
            CalendarWindow.fetchEntry(new CalendarEntry(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), fieldTitle.getText()), true);
        }
        else {
            entry.setDay(date.getDayOfMonth());
            entry.setMonth(date.getMonthValue());
            entry.setYear(date.getYear());
            entry.setDisplayText(fieldTitle.getText());
            CalendarWindow.fetchEntry(entry, false);
        }

        window.close();
    }

    public void getData(Stage window, LocalDate date, CalendarEntry entry) {
        this.window = window;
        this.entry = entry;
        dateSelector.setValue(date);

        if(entry != null)
            fieldTitle.setText(entry.getDisplayText());
    }
}
