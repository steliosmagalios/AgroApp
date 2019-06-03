package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.CalendarEntry;
import gr.eagro.agroapp.utils.ApplicationIndexes;
import gr.eagro.agroapp.utils.ApplicationUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EntryCreationWindow {

    @FXML private DatePicker dateSelector;
    @FXML private TextField fieldTitle;

    private CalendarEntry oldEntry;
    private LocalDate oldDate;
    private CalendarWindow controller;
    private Stage window;

    public void getData(CalendarEntry entry, boolean disableDatePicker, CalendarWindow controller, Stage window, LocalDate date) {
        this.oldEntry = entry;
        this.controller = controller;
        this.window = window;
        dateSelector.setDisable(disableDatePicker);

        if(entry != null) {
            this.oldDate = entry.getDateAsLocalDate();
            dateSelector.setValue(entry.getDateAsLocalDate());
            fieldTitle.setText(entry.getDisplayText());
        }
        else {
            dateSelector.setValue(date);
        }
    }

    @FXML
    private void updateEntry() {
        String displayText = fieldTitle.getText();
        if(displayText.equalsIgnoreCase("")) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_ENTER_TEXT);
            return;
        }

        LocalDate date = dateSelector.getValue();
        CalendarEntry newEntry = new CalendarEntry(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), displayText);

        controller.updateEnrtries(newEntry, oldDate, oldEntry);
        window.close();
    }
}
