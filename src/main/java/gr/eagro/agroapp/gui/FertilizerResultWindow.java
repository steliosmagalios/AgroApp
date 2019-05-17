package gr.eagro.agroapp.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class FertilizerResultWindow extends ApplicationWindow {

    @FXML private TextArea fertilizerInfoArea;
    @FXML private Label labelKg;
    @FXML private Label labelPrice;

    private int kgToDisplay;
    private double priceToDisplay;
    private File fileToDisplay;


    public FertilizerResultWindow() {
        super("Αποτελέσματα", EnumWindowLocation.FERTILIZER_RESULT_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        fertilizerInfoArea.setWrapText(true);
        fertilizerInfoArea.setEditable(false);

    }

    public void getItems(int kgToDisplay, double priceToDisplay, File fileToDisplay) {
        this.kgToDisplay = kgToDisplay;
        this.priceToDisplay = priceToDisplay;
        this.fileToDisplay = fileToDisplay;
        initializeImportedComponents();
    }

    private void initializeImportedComponents() {
        DecimalFormat df = new DecimalFormat("#.##");
        labelKg.setText(kgToDisplay + " Kg");
        labelPrice.setText(df.format(priceToDisplay) + "€");

    }
}
