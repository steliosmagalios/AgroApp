package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Plant;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HybridsResultWindow extends ApplicationWindow {

    @FXML private TextArea hybridInfoArea;
    @FXML private VBox hybridsSelectionBox;

    private ToggleGroup toggleGroup;

    public HybridsResultWindow() {
        super("Αποτελέσματα", EnumWindowLocation.HYBRIDS_RESULT_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        toggleGroup = new ToggleGroup();

    }

    public void getData(Plant plant) {
        initializeComponents(plant);
    }

    private void initializeComponents(Plant plant) {
        plant.getHybrids().forEach(p -> {
            RadioButton button = new RadioButton();
            button.setText(p.getName());
            button.getStyleClass().remove("radio-button");
            button.getStyleClass().add("toggle-button");
            button.setToggleGroup(toggleGroup);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefHeight(45);

            hybridsSelectionBox.getChildren().add(button);
        });
    }
}
