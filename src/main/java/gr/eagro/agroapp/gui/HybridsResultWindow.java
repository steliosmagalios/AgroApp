package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.model.Plant;
import gr.eagro.agroapp.utils.ApplicationUtilities;
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

public class HybridsResultWindow extends ApplicationWindow {

    @FXML private TextArea hybridInfoArea;
    @FXML private VBox hybridsSelectionBox;
    @FXML private Label infoLabel;
    @FXML private ImageView displayImage;

    private ToggleGroup toggleGroup;

    public HybridsResultWindow() {
        super("Αποτελέσματα", ApplicationWindows.HYBRIDS_RESULT_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        toggleGroup = new ToggleGroup();
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            infoLabel.setText(((RadioButton) newValue).getText());
        });

    }

    public void getData(Plant plant) {
        initializeComponents(plant);
    }

    private void initializeComponents(Plant plant) {
        plant.getHybrids().forEach(hybrid -> {
            RadioButton button = new RadioButton();
            button.setText(hybrid.getName());
            button.getStyleClass().remove("radio-button");
            button.getStyleClass().addAll("toggle-button", "regularBtn");
            button.setToggleGroup(toggleGroup);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefHeight(45);

            button.setOnAction(event -> {
               /* try {
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
*/

               hybridInfoArea.setText(ApplicationUtilities.readFile(plant.getInfo("hybrids", hybrid.getId())));

                //loading the image
                try {
                    displayImage.setImage(new Image(getClass().getResourceAsStream("/assets/images/hybrids/" + plant.getId() + "/" + hybrid.getId() + ".png")));
                } catch (NullPointerException e) {
                    displayImage.setImage(new Image(getClass().getResourceAsStream("/images/placeholder.png")));
                }

            });

            hybridsSelectionBox.getChildren().add(button);
        });
    }
}
