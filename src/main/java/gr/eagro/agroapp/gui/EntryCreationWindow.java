package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.model.CalendarEntry;
import gr.eagro.agroapp.utils.ApplicationWindows;
import gr.eagro.agroapp.utils.EnumProcedure;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EntryCreationWindow implements Initializable {

    @FXML private DatePicker datePicker;
    @FXML private TextField titleField;
    @FXML private ChoiceBox<EnumProcedure> actionSelector;
    @FXML private Button btnResigerEntry;
    @FXML private CheckBox createFutureRegistries;

    private Stage window;

    private LocalDate displayDate;
    private String displayText;
    private EnumProcedure displayProcedure;

    public EntryCreationWindow(String title, LocalDate date) {
        this.displayDate = date;
        this.displayText = "";
        this.displayProcedure = null;


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ApplicationWindows.ENTRY_CREATION_WINDOW.getLocation()));
            loader.setController(this);
            Parent root = loader.load();

            window = new Stage();

            window.setScene(new Scene(root));
            window.setTitle(title);
            window.initOwner(Main.getWindow());
            window.setResizable(false);
            window.getIcons().setAll(Main.getWindow().getIcons());

            window.show();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EntryCreationWindow(String title, CalendarEntry entry) {
        // TODO: 07-Jun-19 Extract Procedure from displayText
        String displayText = entry.getDisplayText();
        this.displayDate = entry.getDateAsLocalDate();

        String[] splitText = displayText.split(" - ");

        String procedureText = splitText[0].replace("[", "").replace("]", "");
        this.displayProcedure = EnumProcedure.getValueOf(procedureText);
        this.displayText = splitText[1].trim();

        Main.getCalendar().removeEntry(entry);
        openWindow(title);
    }

    public EntryCreationWindow(String title, LocalDate displayDate, EnumProcedure displayProcedure, String displayText) {
        this.displayText = displayText;
        this.displayProcedure = displayProcedure;
        this.displayDate = displayDate;

        openWindow(title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actionSelector.getItems().setAll(EnumProcedure.values());
        if(this.displayProcedure != null)
            actionSelector.getSelectionModel().select(this.displayProcedure);
        else
            actionSelector.getSelectionModel().select(EnumProcedure.PLANTING);

        if(displayDate != null)
            datePicker.setValue(displayDate);
        else
            datePicker.setValue(LocalDate.now());

        titleField.setText(displayText);
    }

    @FXML
    private void updateEntries() {

        LocalDate date = datePicker.getValue();

        String displayText = "";


        if(titleField.getText().equalsIgnoreCase("")) {
            // TODO: 06-Jun-19 Error

            System.out.println("Error");
            return;
        }
        else {
            displayText = "[" + actionSelector.getSelectionModel().getSelectedItem() + "] - " + titleField.getText();
        }

        if(createFutureRegistries.isSelected()) {
            // TODO: 07-Jun-19 Create Entries
            System.out.println("Create the Entries");
        }

        Main.getCalendar().addEntry(new CalendarEntry(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), displayText));
        // TODO: 07-Jun-19 Close Window
        // TODO: 07-Jun-19 Update somehow the list in CalendarWindow
        window.close();
    }

    private void openWindow(String title) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(ApplicationWindows.ENTRY_CREATION_WINDOW.getLocation()));
        Parent root = null;
        try {
            loader.setController(this);
            root = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(root != null) {
            this.window = new Stage();
            this.window.setScene(new Scene(root));
            this.window.sizeToScene();
            this.window.setTitle(title);
            this.window.setAlwaysOnTop(true);
            this.window.setResizable(false);
            this.window.initOwner(Main.getWindow());
            this.window.show();
        }



    }


}
