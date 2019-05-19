package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Plant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DiseasesResultWindow extends ApplicationWindow {

    @FXML private TextArea diseaseInfoArea;
    @FXML private VBox diseasesSelectionBox;
    @FXML private Label infoLabel;

    private ToggleGroup toggleGroup;

    private Plant selectedPlant;
    private List<String> diseases;


    public DiseasesResultWindow() {
        super("Αποτελέσματα", EnumWindowLocation.DISEASES_RESULT_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        toggleGroup = new ToggleGroup();

        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<100;i++)
            temp.add(Integer.toString(i));

        requestData(null, temp);
    }

    public void requestData(Plant selectedPlant, ArrayList<String> diseases) {
        this.selectedPlant = selectedPlant;
        this.diseases = diseases;

        initializeComponents();
    }

    private void initializeComponents() {
//        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
//            // TODO: 19-May-19 ADD INFORMATION TO LABEL ABOVE THE TEXT AREA AND SET THE TEXT ON THE TEXT AREA
//            infoLabel.setText(((RadioButton) newValue).getText());
//            diseaseInfoArea.setText(((RadioButton) newValue).getText());
//        });
//
//        diseases.forEach(s -> {
//            RadioButton button = new RadioButton();
//            button.getStyleClass().remove("radio-button");
//            button.getStyleClass().addAll("toggle-button", "regularBtn");
//            button.setToggleGroup(toggleGroup);
//            button.setText(s);
//            button.setMaxWidth(Double.MAX_VALUE);
//            button.setPrefHeight(45);
//
//            diseasesSelectionBox.getChildren().add(button);
//        });
    }
}
