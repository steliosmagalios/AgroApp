package gr.eagro.agroapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Statistics {


    HashMap<Integer, Double> hmap_income;
    HashMap<Integer, Double> hmap_quantity;

    public Statistics() {
        hmap_income = new HashMap<Integer, Double>();
        hmap_quantity = new HashMap<Integer, Double>();
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

}
