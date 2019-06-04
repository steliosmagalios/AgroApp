package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.model.Plant;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class HybridsResultWindow extends ApplicationWindow {

    @FXML private TextArea hybridInfoArea;
    @FXML private VBox hybridsSelectionBox;

    private ToggleGroup toggleGroup;

    public HybridsResultWindow() {
        super("Αποτελέσματα", ApplicationWindows.HYBRIDS_RESULT_WINDOW);
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
            button.getStyleClass().addAll("toggle-button", "regularBtn");
            button.setToggleGroup(toggleGroup);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefHeight(45);

            button.onActionProperty().set(event -> {
                try {
                    InputStream stream = plant.getInfo("hybrids", p.getId());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    hybridInfoArea.setText(builder.toString());
                }catch (IOException e) {
                    e.printStackTrace();
                }
            });

            hybridsSelectionBox.getChildren().add(button);
        });
    }
}
