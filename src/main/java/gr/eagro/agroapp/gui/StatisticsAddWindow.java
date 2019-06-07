package gr.eagro.agroapp.gui;

import gr.eagro.agroapp.Main;
import gr.eagro.agroapp.model.Statistics;
import gr.eagro.agroapp.utils.ApplicationUtilities;
import gr.eagro.agroapp.utils.ApplicationWindows;
import gr.eagro.agroapp.utils.TableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsAddWindow extends ApplicationWindow {

    @FXML private TextField fieldYear;
    @FXML private TextField fieldQuantity;
    @FXML private TextField fieldIncome;
    @FXML private TableView<TableEntry> dataTable;

    private ObservableList<TableEntry> data;
    private Statistics statistics;

    public StatisticsAddWindow() {
        super("Εισαγωγή Στατιστικών", ApplicationWindows.STATISTICS_ADD_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.statistics = Main.getStatistics();

        TableColumn<TableEntry, String> col1 = new TableColumn<>("Έτος");
        TableColumn<TableEntry, String> col2 = new TableColumn<>("Έσοδα (σε €)");
        TableColumn<TableEntry, String> col3 = new TableColumn<>("Ποσότητα (σε Kg)");

        col1.setCellValueFactory(new PropertyValueFactory<>("year"));
        col2.setCellValueFactory(new PropertyValueFactory<>("income"));
        col3.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        Double[] incomeValues = statistics.getIncomeGraphData().values().toArray(new Double[0]);
        Double[] quantityValues = statistics.getProductionGraphData().values().toArray(new Double[0]);
        Integer[] years = statistics.getIncomeGraphData().keySet().toArray(new Integer[0]);

        List<TableEntry> entriesToAdd = new ArrayList<>();
        for(int i=0;i<incomeValues.length;i++)
            entriesToAdd.add(new TableEntry(years[i], incomeValues[i], quantityValues[i]));

        dataTable.getColumns().addAll(col1, col2, col3);
        data = FXCollections.observableArrayList(entriesToAdd);

        dataTable.setItems(data);
        dataTable.getSortOrder().add(col1);
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

            ApplicationUtilities.createWarning("Δεδομένα!");
            return;
        }

        data.add(new TableEntry(year, income, quantity));
        statistics.getIncomeGraphData().put(year, income);
        statistics.getProductionGraphData().put(year, quantity);

        fieldYear.clear();
        fieldIncome.clear();
        fieldQuantity.clear();
    }

    public void openResultWindow() {
        if(data.size() < 2) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Όχι αρκετά δεδομένα!");
            alert.show();
            return;
        }
        openWindow(ApplicationWindows.STATISTICS_GRAPH_WINDOW);
    }

}