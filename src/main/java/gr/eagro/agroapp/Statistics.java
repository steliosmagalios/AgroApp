package gr.eagro.agroapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.Serializable;


public class Statistics  implements Serializable{


    HashMap<Integer, Double> incomeGraphData;
    HashMap<Integer, Double> productionGraphData;


    public Statistics() {
        incomeGraphData = new HashMap<Integer, Double>();
        productionGraphData = new HashMap<Integer, Double>();
    }

    /**
     *
     * @return
     */
    public double calculateAverageIncome() {
        ArrayList<Double> values = ((ArrayList<Double>) incomeGraphData.values());
        Set keys =  productionGraphData.keySet();

        double total = 0;

        for (Double item : values) {
            total += item;
        }

        return total/keys.size();
    }

    /**
     *
     * @return
     */
    public double calculateAverageQuantity() {
        ArrayList<Double> values = ((ArrayList<Double>) productionGraphData.values());
        Set keys =  incomeGraphData.keySet();

        double total = 0;

        for (Double value : values) {
            total += value;
        }

        return total/keys.size();
    }

    /**
     *
     * @return
     */
    public double calculateEstimatedGrowth() {
        Set<Integer> yearSet = productionGraphData.keySet();

        int last = 0;
        for (Integer item : yearSet) {
            last = item;
        }

        double lastValue = productionGraphData.get(last);

        return lastValue + calculateAverageQuantity();
    }

    /**
     *
     * @return
     */
    public HashMap<Integer, Double> getIncomeGraphData() {
        return incomeGraphData;
    }

    /**
     *
     * @return
     */
    public HashMap<Integer, Double> getProductionGraphData() {
        return productionGraphData;
    }
}
