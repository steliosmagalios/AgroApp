package gr.eagro.agroapp.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DiseasesWindow extends ApplicationWindow {

    //TODO: change type of ListView to <Plant>
    @FXML private ListView<Object> plantList;
    @FXML private RadioButton btnSelectTree;
    @FXML private RadioButton btnSelectCrop;
    @FXML private Label labelSelectFromList;

    //TODO: change type of ArrayList to <Tree>
    private ArrayList<Object> treeItems;
    //TODO: change type of ArrayList to <Crop>
    private ArrayList<Object> cropItems;

    public DiseasesWindow() {
        super("Ασθένειες", EnumWindowLocation.DISEASES_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        btnSelectCrop.getStyleClass().remove("radio-button");
        btnSelectTree.getStyleClass().remove("radio-button");

        //todo remove
        treeItems = new ArrayList<>();
        cropItems = new ArrayList<>();

        treeButtonSelected();
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
        openWindow(EnumWindowLocation.DISEASES_RESULT_WINDOW);
    }
}
