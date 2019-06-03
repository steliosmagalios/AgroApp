package gr.eagro.agroapp.utils;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TableEntry implements Comparable<TableEntry> {
    private SimpleIntegerProperty year;
    private SimpleDoubleProperty income;
    private SimpleDoubleProperty quantity;

    public TableEntry(int year, double income, double quality) {
        this.year = new SimpleIntegerProperty(year);
        this.income = new SimpleDoubleProperty(income);
        this.quantity = new SimpleDoubleProperty(quality);
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public double getIncome() {
        return income.get();
    }

    public void setIncome(double income) {
        this.income.set(income);
    }

    public double getQuantity() {
        return quantity.get();
    }

    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }

    @Override
    public int compareTo(TableEntry o) {
        if(this.getYear() > o.getYear())
            return 1;
        else if(this.getYear() < o.getYear())
            return -1;
        else
            return 0;
    }
}
