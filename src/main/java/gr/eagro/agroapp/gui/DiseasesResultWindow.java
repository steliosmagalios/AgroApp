package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.model.Plant;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DiseasesResultWindow extends ApplicationWindow {

    @FXML private TextArea diseaseInfoArea;
    @FXML private VBox diseasesSelectionBox;
    @FXML private Label infoLabel;
    @FXML private ImageView displayImage;

    private ToggleGroup toggleGroup;

    private Plant selectedPlant;


    public DiseasesResultWindow() {
        super("Αποτελέσματα", ApplicationWindows.DISEASES_RESULT_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        toggleGroup = new ToggleGroup();
    }

    private void initializeComponents() {
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            infoLabel.setText(((RadioButton) newValue).getText());
            diseaseInfoArea.setText(((RadioButton) newValue).getText());

            // TODO: 05-Jun-19 Add Images
            try {
                displayImage.setImage(new Image(getClass().getResourceAsStream("/assets/images/" + selectedPlant.getId() + "/")));
            } catch (NullPointerException e) {
                displayImage.setImage(new Image(getClass().getResourceAsStream("/images/placeholder.png")));
            }
        });

        selectedPlant.getDiseases().forEach(s -> {
            RadioButton button = new RadioButton();
            button.getStyleClass().remove("radio-button");
            button.getStyleClass().addAll("toggle-button", "regularBtn");
            button.setToggleGroup(toggleGroup);
            button.setText(s);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefHeight(45);

            diseasesSelectionBox.getChildren().add(button);
        });
    }

    public void getData(Plant selectedPlant) {
        this.selectedPlant = selectedPlant;
        initializeComponents();
    }
}
