package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.CalendarEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EntryCreationWindow implements Initializable {

    @FXML private DatePicker dateSelector;
    @FXML private TextField fieldTitle;

    private Stage window;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createNewEntry() {
        LocalDate date = dateSelector.getValue();
        CalendarWindow.fetchEntry(new CalendarEntry(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), fieldTitle.getText()));
        window.close();
    }

    public void getData(Stage window, LocalDate date) {
        this.window = window;
        dateSelector.setValue(date);
    }
}
