package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.model.Crop;
import gr.eagro.agroapp.model.Plant;
import gr.eagro.agroapp.model.Tree;
import gr.eagro.agroapp.utils.ApplicationIndexes;
import gr.eagro.agroapp.utils.ApplicationUtilities;
import gr.eagro.agroapp.utils.ApplicationWindows;
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
    @FXML private ToggleGroup categoryToggle;

    private List<Tree> treeItems;
    private List<Crop> cropItems;

    public FertilizerWindow() {
        super("Λίπασμα", ApplicationWindows.FERTILIZER_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        btnSelectCrop.getStyleClass().remove("radio-button");
        btnSelectTree.getStyleClass().remove("radio-button");

        treeItems = new ArrayList<>();
        cropItems = new ArrayList<>();

        Main.getPlants().forEach(plant -> {
            if(plant instanceof Tree)
                treeItems.add(((Tree) plant));
            else
                cropItems.add(((Crop) plant));
        });

        treeButtonSelected();
    }

    public void openResultWindow() {

        int quantity;

        //Checks the TextField for an Integer
        try {
            quantity = Integer.parseInt(input.getText());

            if(quantity < 0) {
                ApplicationUtilities.createWarning((categoryToggle.getSelectedToggle() == btnSelectTree) ? ApplicationIndexes.WARNING_NEGATIVE_TREE : ApplicationIndexes.WARNING_NEGATIVE_CROP);
                input.clear();
                return;
            }
        }catch(NumberFormatException e) {
            ApplicationUtilities.createWarning((categoryToggle.getSelectedToggle() == btnSelectTree) ? ApplicationIndexes.WARNING_INSERT_TREE : ApplicationIndexes.WARNING_INSERT_CROP);
            return;
        }

        //Checks the list to get the selected Plant
        Plant selectedPlant = plantList.getSelectionModel().getSelectedItem();
        if(selectedPlant == null) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_SELECT_PLANT);
            return;
        }

        if(selectedPlant instanceof Tree) {
            ((Tree) selectedPlant).setQuantity(quantity);
        } else {
            ((Crop) selectedPlant).setQuantity(quantity);
        }

        FertilizerResultWindow controller = ((FertilizerResultWindow) openWindow(ApplicationWindows.FERTILIZER_RESULT_WINDOW));
        controller.getData(selectedPlant);
    }

    public void treeButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(treeItems));
        labelDisplayText.setText("Πόσα δέντρα έχει το χωράφι;");
        labelSelectFromList.setText("Επιλέξτε ένα δέντρο:");
        input.clear();
    }

    public void cropButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(cropItems));
        labelDisplayText.setText("Πόσα στρέμματα είναι το χωράφι;");
        labelSelectFromList.setText("Επιλέξτε μία φύτρα:");
        input.clear();
    }
}
