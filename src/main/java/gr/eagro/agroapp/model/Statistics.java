package gr.eagro.agroapp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


public class Statistics  implements Serializable{


    private HashMap<Integer, Double> incomeGraphData;
    private HashMap<Integer, Double> productionGraphData;


    public Statistics() {
        incomeGraphData = new HashMap<>();
        productionGraphData = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public double calculateAverageIncome() {
       return calculateAverage(incomeGraphData);
    }

    /**
     *
     * @return
     */
    public double calculateAverageQuantity() {
       return calculateAverage(productionGraphData);
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
     * @param map The HashMap to get the average value of.
     *
     * @return Returns the average of the values stored inside the HashMap.
     *
    * */
    private double calculateAverage(HashMap<Integer, Double> map) {
        Collection<Double> collection = map.values();

        double total = 0;
        for (Double item : collection)
            total += item;

        return total / (map.keySet().size());
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
