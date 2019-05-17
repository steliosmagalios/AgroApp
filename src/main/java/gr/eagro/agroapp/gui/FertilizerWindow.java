package gr.eagro.agroapp.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FertilizerWindow extends ApplicationWindow {

    @FXML private ListView<Object> plantList;
    @FXML private Button btnSelectTree;
    @FXML private Button btnSelectCrop;
    @FXML private TextField input;

    @FXML private Label labelDisplayText;
    @FXML private Label labelSelectFromList;

    private ArrayList<Object> treeItems;
    private ArrayList<Object> cropItems;

    public FertilizerWindow() {
        super("Λίπασμα", EnumWindowLocation.FERTILIZER_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        //todo Enter correct data to ArrayLists
        //For now the list initialize here with no items
        treeItems = new ArrayList<>();

        for(int i=0; i<10; i++) {
            treeItems.add(Integer.toString(i));
        }

        cropItems = new ArrayList<>(treeItems);
        cropItems.add(Integer.toString(10));

        treeButtonSelected();
    }

    public void openResultWindow() {
        //Input validation

        //todo complete and re-add validation
//        String inputText = input.getText();
//
//        int amount;
//        try {
//            amount = Integer.parseInt(inputText);
//        } catch (NumberFormatException e) {
//            //Open error window and return
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("Παρακαλώ εισάγετε έναν αριθμό");
//            alert.show();
//            return;
//        }
//
//        String selectedPlant = ((String) plantList.getSelectionModel().getSelectedItem());
//        if(selectedPlant == null) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("Παρακαλώ επιλέξτε ένα φυτό από την λίστα.");
//            alert.show();
//            return;
//        }
//
//
//        FertilizerResultWindow controller = ((FertilizerResultWindow) openWindow(EnumWindowLocation.FERTILIZER_RESULT_WINDOW));
//        //todo call the correct ones
//        controller.getItems(amount, 12.45f, null);

        openWindow(EnumWindowLocation.FERTILIZER_RESULT_WINDOW);
    }

    public void treeButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(treeItems));
        labelDisplayText.setText("Πόσα δέντρα έχει το χωράφι;");
        labelSelectFromList.setText("Επιλέξτε ένα δέντρο:");
        input.setText("");

        if(!btnSelectTree.getStyleClass().contains("menuBtnSelected"))
            btnSelectTree.getStyleClass().add("menuBtnSelected");
        btnSelectCrop.getStyleClass().remove("menuBtnSelected");
    }

    public void cropButtonSelected() {
        plantList.setItems(FXCollections.observableArrayList(cropItems));
        labelDisplayText.setText("Πόσα στρέμματα είναι το χωράφι;");
        labelSelectFromList.setText("Επιλέξτε μία φύτρα:");
        input.setText("");

        if(!btnSelectCrop.getStyleClass().contains("menuBtnSelected"))
            btnSelectCrop.getStyleClass().add("menuBtnSelected");
        btnSelectTree.getStyleClass().remove("menuBtnSelected");
    }
}
