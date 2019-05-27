package gr.eagro.agroapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Statistics implements Serializable {


    private HashMap<Integer, Double> hmap_income;
    private HashMap<Integer, Double> hmap_quantity;

    public Statistics() {
        hmap_income = new HashMap<>();
        hmap_quantity = new HashMap<>();
    }

    public double calculateAverageIncome() {
        ArrayList<Double> values = ((ArrayList<Double>) hmap_income.values());
        Set keys =  hmap_income.keySet();

        double total = 0;

        for (Double item : values) {
            total += item;
        }

        return total/keys.size();
    }

    public double calculateAverageQuantity() {
        ArrayList<Double> values = ((ArrayList<Double>) hmap_quantity.values());
        Set keys =  hmap_income.keySet();

        double total = 0;

        for (Double value : values) {
            total += value;
        }

        return total/keys.size();
    }

    public double calculateEstimatedGrowth() {
        Set<Integer> yearSet = hmap_quantity.keySet();

        int last = 0;
        for (Integer item : yearSet) {
            last = item;
        }

        double lastValue = hmap_quantity.get(last);

        return lastValue + calculateAverageQuantity();
    }

    public HashMap<Integer, Double> getIncomeGraphData() {
        return hmap_income;
    }

    public HashMap<Integer, Double> getQuantitygraphData() {
        return hmap_quantity;
    }
}
