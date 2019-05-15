package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Crop;
import gr.eagro.agroapp.Tree;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FertilizerWindow extends Window {

    private ArrayList<Tree> treeList;
    private ArrayList<Crop> cropList;

    public FertilizerWindow() {
        super("Λίπασμα", EnumWindowLocations.FERTILIZER_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        //todo make lists accept the right items
    }

    public void openResultWindow() throws Exception{
        openWindow(EnumWindowLocations.FERTILIZER_RESULT_WINDOW);
    }
}
