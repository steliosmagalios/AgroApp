package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.Statistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsAddWindow extends ApplicationWindow {

    @FXML private TextField fieldYear;
    @FXML private TextField fieldQuantity;
    @FXML private TextField fieldIncome;
    @FXML private TableView<TableEntry> dataTable;

    private ObservableList<TableEntry> data;
    private Statistics statistics;

    public StatisticsAddWindow() {
        super("Εισαγωγή Στατιστικών", EnumWindowLocation.STATISTICS_ADD_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.statistics = Main.getStatistics();

        TableColumn<TableEntry, String> col1 = new TableColumn<>("Έτος");
        TableColumn<TableEntry, String> col2 = new TableColumn<>("Έσοδα");
        TableColumn<TableEntry, String> col3 = new TableColumn<>("Ποσότητα");

        col1.setCellValueFactory(new PropertyValueFactory<>("year"));
        col2.setCellValueFactory(new PropertyValueFactory<>("income"));
        col3.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        dataTable.getColumns().addAll(col1, col2, col3);
        data = FXCollections.observableArrayList();

        dataTable.setItems(data);
    }

    public void addDataToTable() {
        int year;
        double income, quantity;
        try{

            year = Integer.parseInt(fieldYear.getText());
            income = Double.parseDouble(fieldIncome.getText());
            quantity = Double.parseDouble(fieldQuantity.getText());

        }catch(NumberFormatException e) {
            fieldYear.clear();
            fieldIncome.clear();
            fieldQuantity.clear();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Δεδομένα!");
            alert.show();
            return;
        }

        data.add(new TableEntry(year, income, quantity));
        statistics.getIncomeGraphData().put(year, income);
        statistics.getQuantitygraphData().put(year, quantity);
    }

    public void openResultWindow() {
//        if(data.size() < 2) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("Όχι αρκετά δεδομένα!");
//            alert.show();
//            return;
//        }
        openWindow(EnumWindowLocation.STATISTICS_GRAPH_WINDOW);
    }

}
