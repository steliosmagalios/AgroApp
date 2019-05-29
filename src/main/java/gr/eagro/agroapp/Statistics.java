package gr.eagro.agroapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Statistics  implements java.io.Serializable{


    HashMap<Integer, Double> incomeGraphData;
    HashMap<Integer, Double> productionGraphData;

    public Statistics() {
        incomeGraphData = new HashMap<Integer, Double>();
        productionGraphData = new HashMap<Integer, Double>();
    }

    public double calculateAverageIncome() {
        ArrayList<Double> values = ((ArrayList<Double>) incomeGraphData.values());
        Set keys =  productionGraphData.keySet();

        double total = 0;

        for (Double item : values) {
            total += item;
        }

        return total/keys.size();
    }

    public double calculateAverageQuantity() {
        ArrayList<Double> values = ((ArrayList<Double>) productionGraphData.values());
        Set keys =  incomeGraphData.keySet();

        double total = 0;

        for (Double value : values) {
            total += value;
        }

        return total/keys.size();
    }

    public double calculateEstimatedGrowth() {
        Set<Integer> yearSet = productionGraphData.keySet();

        int last = 0;
        for (Integer item : yearSet) {
            last = item;
        }

        double lastValue = productionGraphData.get(last);

        return lastValue + calculateAverageQuantity();
    }

    public HashMap<Integer, Double> getIncomeGraphData() {
        return incomeGraphData;
    }

    public HashMap<Integer, Double> getProductionGraphData() {
        return productionGraphData;
    }
}
