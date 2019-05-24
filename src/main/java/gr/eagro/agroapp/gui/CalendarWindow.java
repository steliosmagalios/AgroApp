package gr.eagro.agroapp.gui;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CalendarWindow extends ApplicationWindow {

    @FXML private Pane calendarPane;

    private DatePickerSkin datePickerSkin;
    private DatePicker datePicker;

    public CalendarWindow() {
        super("Ημερολόγιο", EnumWindowLocation.CALENDAR_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        datePicker = new DatePicker(LocalDate.now());

        datePickerSkin = new DatePickerSkin(datePicker);
        Node node = datePickerSkin.getPopupContent();

//        datePicker.setPrefHeight(300);
//        datePicker.setPrefWidth(300);

        calendarPane.getChildren().add(node);


        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {

        });


    }
}
