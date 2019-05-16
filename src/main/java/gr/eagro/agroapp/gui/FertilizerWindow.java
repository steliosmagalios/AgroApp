package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Crop;
import gr.eagro.agroapp.Tree;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FertilizerWindow extends Window {

    @FXML private ListView<Integer> list;
    @FXML private RadioButton rbtnTree;
    @FXML private RadioButton rbtnCrop;

    private Integer[] ones = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    private Integer[] zeroes = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};

    private ToggleGroup toggleGroup;

    private ArrayList<Tree> treeList;
    private ArrayList<Crop> cropList;

    public FertilizerWindow() {
        super("Λίπασμα", EnumWindowLocations.FERTILIZER_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        toggleGroup = new ToggleGroup();
        rbtnCrop.setToggleGroup(toggleGroup);
        rbtnTree.setToggleGroup(toggleGroup);


        //todo make lists accept the right items

    }

    public void openResultWindow() {
        openWindow(EnumWindowLocations.FERTILIZER_RESULT_WINDOW);
    }

    public void rbtnTreeClick() {
        list.setItems(FXCollections.observableArrayList(ones));
    }

    public void rbtnCropClick() {
        list.setItems(FXCollections.observableArrayList(zeroes));
    }
}
