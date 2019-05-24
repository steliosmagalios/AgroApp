package gr.eagro.agroapp.gui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsAddWindow extends ApplicationWindow {

    @FXML
    private TextField fieldYear;
    @FXML
    private TextField fieldQuantity;
    @FXML
    private TextField fieldIncome;
    @FXML
    private TableView<TableEntry> dataTable;

    private ObservableList<TableEntry> data;

    public StatisticsAddWindow() {
        super("Εισαγωγή Στατιστικών", EnumWindowLocation.STATISTICS_ADD_WINDOW);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

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
//        try{
        data.add(new TableEntry(
                Integer.parseInt(fieldYear.getText()),
                Double.parseDouble(fieldIncome.getText()),
                Double.parseDouble(fieldQuantity.getText())
        ));
//        }catch(NumberFormatException e) {
//            fieldYear.clear();
//            fieldIncome.clear();
//            fieldQuantity.clear();
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Δεδομένα!");
//            alert.show();
        // TODO: 20-May-19 Throw alert and wipe the fields
//        }
    }

    public void openResultWindow() {
        // TODO: 20-May-19 Write method
        // TODO: 20-May-19 Check for at least two entries on the table to display statistics

//        if(data.size() < 2) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Όχι αρκετά δεδομένα!");
//            alert.show();
//        }

        openWindow(EnumWindowLocation.STATISTICS_GRAPH_WINDOW);
    }

    public class TableEntry {
        private SimpleIntegerProperty year;
        private SimpleDoubleProperty income;
        private SimpleDoubleProperty quantity;

        TableEntry(int year, double income, double quality) {
            this.year = new SimpleIntegerProperty(year);
            this.income = new SimpleDoubleProperty(income);
            this.quantity = new SimpleDoubleProperty(quality);
        }

        public int getYear() {
            return year.get();
        }

        public SimpleIntegerProperty yearProperty() {
            return year;
        }

        public void setYear(int year) {
            this.year.set(year);
        }

        public double getIncome() {
            return income.get();
        }

        public SimpleDoubleProperty incomeProperty() {
            return income;
        }

        public void setIncome(double income) {
            this.income.set(income);
        }

        public double getQuantity() {
            return quantity.get();
        }

        public SimpleDoubleProperty quantityProperty() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity.set(quantity);
        }
    }

}
