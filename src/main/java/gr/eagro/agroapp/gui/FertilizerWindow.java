package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Crop;
import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.Plant;
import gr.eagro.agroapp.Tree;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FertilizerWindow extends ApplicationWindow {

    @FXML private RadioButton btnSelectTree;
    @FXML private RadioButton btnSelectCrop;
    @FXML private ListView<Plant> plantList;
    @FXML private TextField input;
    @FXML private Label labelDisplayText;
    @FXML private Label labelSelectFromList;

    private List<Tree> treeItems;
    private List<Crop> cropItems;

    public FertilizerWindow() {
        super("Λίπασμα", EnumWindowLocation.FERTILIZER_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        btnSelectCrop.getStyleClass().remove("radio-button");
        btnSelectTree.getStyleClass().remove("radio-button");

        treeItems = new ArrayList<>();
        cropItems = new ArrayList<>();

        getData(Main.getPlants());
    }

    public void openResultWindow() {

        int quantity;

        //Input validation
        try {
            quantity = Integer.parseInt(input.getText());
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Παρακαλώ εισάγετε τα κατάλληλα στοιχεία.");
            alert.show();
            return;
        }
        Plant selectedPlant = plantList.getSelectionModel().getSelectedItem();

        FertilizerResultWindow controller = ((FertilizerResultWindow) openWindow(EnumWindowLocation.FERTILIZER_RESULT_WINDOW));
        controller.getData(selectedPlant, quantity);
    }

    public void treeButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(treeItems));
        labelDisplayText.setText("Πόσα δέντρα έχει το χωράφι;");
        labelSelectFromList.setText("Επιλέξτε ένα δέντρο:");
        input.setText("");
    }

    public void cropButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(cropItems));
        labelDisplayText.setText("Πόσα στρέμματα είναι το χωράφι;");
        labelSelectFromList.setText("Επιλέξτε μία φύτρα:");
        input.setText("");
    }

    public void getData(ArrayList<Plant> plantList) {
        plantList.forEach(plant -> {
            if(plant instanceof Tree)
                treeItems.add(((Tree) plant));
            else
                cropItems.add(((Crop) plant));
        });

        treeButtonSelected();
    }
}
