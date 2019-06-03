package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Crop;
import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.Plant;
import gr.eagro.agroapp.Tree;
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

public class DiseasesWindow extends ApplicationWindow {

    @FXML private RadioButton btnSelectTree;
    @FXML private RadioButton btnSelectCrop;
    @FXML private ListView<Plant> plantList;
    @FXML private Label labelSelectFromList;

    private ArrayList<Tree> treeItems;
    private ArrayList<Crop> cropItems;

    public DiseasesWindow() {
        super("Ασθένειες", ApplicationWindows.DISEASES_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        btnSelectCrop.getStyleClass().remove("radio-button");
        btnSelectTree.getStyleClass().remove("radio-button");

        getData(Main.getPlants());
    }

    public void treeButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(treeItems));
        labelSelectFromList.setText("Επιλέξτε ένα δέντρο:");
    }

    public void cropButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(cropItems));
        labelSelectFromList.setText("Επιλέξτε μία φύτρα:");
    }

    public void openResultWindow() {
        Plant selectedPlant = plantList.getSelectionModel().getSelectedItem();
        if(selectedPlant == null) {
            ApplicationUtilities.createWarning(ApplicationIndexes.WARNING_SELECT_PLANT);
            return;
        }

        DiseasesResultWindow controller = (DiseasesResultWindow) openWindow(ApplicationWindows.DISEASES_RESULT_WINDOW);
        controller.getData(selectedPlant);
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
