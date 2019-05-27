package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.Statistics;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsGraphWindow extends ApplicationWindow {

    @FXML private LineChart<Number, Number> productionChart;
    @FXML private LineChart<Number, Number> incomeChart;
    @FXML private Label labelAverageQuantity;
    @FXML private Label labelAverageIncome;
    @FXML private Label labelEstimatedGrowth;

    public StatisticsGraphWindow() {
        super("Αποτελέσματα", EnumWindowLocation.STATISTICS_GRAPH_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        Statistics statistics = Main.getStatistics();

        XYChart.Series<Number, Number> productionSeries = new XYChart.Series<>();
        statistics.getQuantitygraphData().forEach((year, quantity) ->
                productionSeries.getData().add(new XYChart.Data<>(year, quantity))
        );
        productionChart.getData().add(productionSeries);

        XYChart.Series<Number, Number> incomeSeries = new XYChart.Series<>();
        statistics.getIncomeGraphData().forEach((year, income) ->
                incomeSeries.getData().add(new XYChart.Data<>(year, income))
        );
        incomeChart.getData().add(incomeSeries);

//        labelAverageIncome.setText(statistics.calculateAverageIncome() + " €");
//        labelAverageQuantity.setText(statistics.calculateAverageQuantity() + " Kg");
//        labelEstimatedGrowth.setText(statistics.calculateEstimatedGrowth() + " Kg");

    }
}
