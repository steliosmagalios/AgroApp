package gr.eagro.agroapp.gui;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsGraphWindow extends ApplicationWindow {

    @FXML private LineChart<Number, Number> productionChart;
    @FXML private LineChart<Integer, Double> incomeChart;

    public StatisticsGraphWindow() {
        super("Αποτελέσματα", EnumWindowLocation.STATISTICS_GRAPH_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        
    }

}
