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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HybridsWindow extends ApplicationWindow {

    @FXML private RadioButton btnSelectCrop;
    @FXML private RadioButton btnSelectTree;
    @FXML private ListView<Plant> plantList;
    @FXML private Label labelSelectFromList;

    private ArrayList<Tree> treeItems;
    private ArrayList<Crop> cropItems;

    public HybridsWindow() {
        super("Υβρίδια", ApplicationWindows.HYBRIDS_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        btnSelectCrop.getStyleClass().remove("radio-button");
        btnSelectTree.getStyleClass().remove("radio-button");

        getData(Main.getPlants());
    }

    public void openResultWindow() {

        Plant selectedPlant = plantList.getSelectionModel().getSelectedItem();

        if(selectedPlant == null) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_SELECT_PLANT);
            return;
        }

        HybridsResultWindow controller = (HybridsResultWindow) openWindow(ApplicationWindows.HYBRIDS_RESULT_WINDOW);
        controller.getData(selectedPlant);
    }

    public void treeButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(treeItems));
        labelSelectFromList.setText("Επιλέξτε ένα δέντρο:");
    }

    public void cropButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(cropItems));
        labelSelectFromList.setText("Επιλέξτε μία φύτρα:");
    }

    public void getData(ArrayList<Plant> plantList) {
        treeItems = new ArrayList<>();
        cropItems = new ArrayList<>();
        plantList.forEach(plant -> {
            if(plant instanceof Tree)
                treeItems.add(((Tree) plant));
            else
                cropItems.add(((Crop) plant));
        });
        treeButtonSelected();
    }
}
