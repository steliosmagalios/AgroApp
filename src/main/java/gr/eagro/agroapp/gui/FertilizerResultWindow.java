package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.model.Crop;
import gr.eagro.agroapp.model.Plant;
import gr.eagro.agroapp.model.Tree;
import gr.eagro.agroapp.utils.ApplicationWindows;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class FertilizerResultWindow extends ApplicationWindow {

    @FXML private TextArea fertilizerInfoArea;
    @FXML private Label labelKg;
    @FXML private Label labelPrice;

    private Plant plant;

    public FertilizerResultWindow() {
        super("Αποτελέσματα", ApplicationWindows.FERTILIZER_RESULT_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        fertilizerInfoArea.setWrapText(true);
        fertilizerInfoArea.setEditable(false);
    }

    public void getData(Plant plant) {
        this.plant = plant;
        initializeImportedComponents();
    }

    private void initializeImportedComponents() {
        DecimalFormat df = new DecimalFormat("#.##");
        labelKg.setText(plant.getFertilizer().calculateQuantity(plant) + " Kg");

        if(plant instanceof Tree)
            labelPrice.setText(df.format(plant.getFertilizer().calculateCost(((Tree) plant).getQuantity())) + "€");
        else
            labelPrice.setText(df.format(plant.getFertilizer().calculateCost(((Crop) plant).getQuantity())) + "€");

        StringBuilder builder = new StringBuilder();
        try {
            InputStream f = plant.getFertilizer().getInfo();
            BufferedReader reader = new BufferedReader(new InputStreamReader(f, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            f.close();

            fertilizerInfoArea.setText(builder.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Null");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
