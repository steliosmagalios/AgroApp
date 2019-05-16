package gr.eagro.agroapp.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class FertilizerResultWindow extends Window{

    @FXML
    private TextArea fertilizerInfoArea;

    public FertilizerResultWindow() {
        super("Αποτελέσματα", EnumWindowLocations.FERTILIZER_RESULT_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        fertilizerInfoArea.setWrapText(true);
        fertilizerInfoArea.setEditable(false);

    }
}
