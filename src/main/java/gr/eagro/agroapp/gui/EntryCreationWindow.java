package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.model.CalendarEntry;
import gr.eagro.agroapp.utils.ApplicationIndexes;
import gr.eagro.agroapp.utils.ApplicationUtilities;
import gr.eagro.agroapp.utils.ApplicationWindows;
import gr.eagro.agroapp.utils.EnumProcedure;
import javafx.collections.ObservableList;
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

    private CalendarEntry inputEntry = null;
    private ObservableList<CalendarEntry> observableCalendar;

    public EntryCreationWindow(String title, LocalDate date, ObservableList<CalendarEntry> observableCalendar) {
        this(date, null, "");
        this.observableCalendar = observableCalendar;
        openWindow(title);
    }

    public EntryCreationWindow(String title, CalendarEntry entry, ObservableList<CalendarEntry> observableCalendar) {
        this(entry.getDateAsLocalDate(), null, entry.getDisplayText());
        this.observableCalendar = observableCalendar;
        this.inputEntry = entry;
        openWindow(title);
    }

    public EntryCreationWindow(String title, LocalDate displayDate, EnumProcedure displayProcedure, String displayText) {
        this(displayDate, displayProcedure, displayText);
        this.observableCalendar = null;
        openWindow(title);
    }

    private EntryCreationWindow(LocalDate displayDate, EnumProcedure displayProcedure, String displayText) {
        if(displayDate != null)
            this.displayDate = displayDate;
        else
            this.displayDate = LocalDate.now();

        if(displayProcedure == null && displayText.equals("")) {
            this.displayProcedure = EnumProcedure.PLANTING;
            this.displayText = "";
        }
        else {
            if(displayProcedure != null) {
                this.displayProcedure = displayProcedure;
                this.displayText = displayText;
            } else {
                String[] splitText = displayText.split("-");

                this.displayProcedure = EnumProcedure.getValueOf(splitText[0].replace("[", "").replace("]", "").trim());
                this.displayText = splitText[1].trim();
            }
        }
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

        datePicker.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if(item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    getStyleClass().add("disabled-date-cell");
                }
            }
        });

        createFutureRegistries.setTooltip(new Tooltip("Όταν επιλεγεί, δημιουργεί καταχωρήσεις σε μελλοντικό χρόνο για επόμενες ενέργειες."));
    }

    @FXML
    private void updateEntries() {

        LocalDate date = datePicker.getValue();
        this.displayProcedure = actionSelector.getSelectionModel().getSelectedItem();

        String displayText;


        if(titleField.getText().equalsIgnoreCase("")) {
            // TODO: 06-Jun-19 Error
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_ENTER_TEXT);
            System.out.println("Error");
            return;
        }
        else {
            displayText = "[" + this.displayProcedure  + "] - " + titleField.getText();
        }

        Main.getCalendar().addEntry(new CalendarEntry(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), displayText));
        if(createFutureRegistries.isSelected()) {
            LocalDate loopDate = datePicker.getValue();


            boolean createNew = false;
            for (EnumProcedure procedure : EnumProcedure.values()) {
                if(procedure.equals(displayProcedure)) {
                    createNew = true;
                    continue;
                }

                if(createNew) {
                    switch(procedure) {
                        case FERTILIZING:
                            loopDate = loopDate.plusWeeks(3);
                            Main.getCalendar().addEntry(new CalendarEntry(loopDate.getDayOfMonth(), loopDate.getMonthValue(), loopDate.getYear(), "[" + procedure + "] - " + titleField.getText()));
                            break;
                        case CUTTING:
                            loopDate = loopDate.plusWeeks(1);
                            Main.getCalendar().addEntry(new CalendarEntry(loopDate.getDayOfMonth(), loopDate.getMonthValue(), loopDate.getYear(), "[" + procedure + "] - " + titleField.getText()));
                            break;
                        case COLLECTION:
                            loopDate = loopDate.plusWeeks(2);
                            Main.getCalendar().addEntry(new CalendarEntry(loopDate.getDayOfMonth(), loopDate.getMonthValue(), loopDate.getYear(), "[" + procedure + "] - " + titleField.getText()));
                            break;
                        case UPROOTING:
                            loopDate = loopDate.plusWeeks(4);
                            Main.getCalendar().addEntry(new CalendarEntry(loopDate.getDayOfMonth(), loopDate.getMonthValue(), loopDate.getYear(), "[" + procedure + "] - " + titleField.getText()));
                            break;
                    }
                }
            }
        }

        Main.getCalendar().removeEntry(inputEntry);
        if (observableCalendar != null)
            observableCalendar.setAll(Main.getCalendar().getEntries());
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
            this.window.setResizable(false);
            this.window.initOwner(Main.getWindow());
            this.window.getIcons().setAll(Main.getWindow().getIcons());
            this.window.showAndWait();
        }
    }

}
