package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Plant;
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

    private int kgToDisplay;
    private double priceToDisplay;
    private Plant plant;

    public FertilizerResultWindow() {
        super("Αποτελέσματα", EnumWindowLocation.FERTILIZER_RESULT_WINDOW);
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
        labelPrice.setText(df.format(plant.getFertilizer().calculateCost(plant.getFertilizer().calculateQuantity(plant))) + "€");

        StringBuilder builder = new StringBuilder();
        try {
//            File f = plant.getFertilizer().getInfo();
            InputStream f = plant.getFertilizer().getInfo();

            BufferedReader reader = new BufferedReader(new InputStreamReader(plant.getFertilizer().getInfo(), StandardCharsets.UTF_8));
//            BufferedReader reader = new BufferedReader(new FileReader(f));
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
