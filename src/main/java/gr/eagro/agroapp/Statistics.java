package gr.eagro.agroapp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Statistics  implements java.io.Serializable{


    private HashMap<Integer, Double> incomeGraphData;
    private HashMap<Integer, Double> productionGraphData;

    public Statistics() {
        incomeGraphData = new HashMap<>();
        productionGraphData = new HashMap<>();
    }

    public double calculateAverageQuantity() {
        Collection<Double> quantities = productionGraphData.values();

        double total=0;
        for (Double q: quantities) {
            total+=q;
        }
        return total/productionGraphData.keySet().size();
    }

    public double calculateAverageIncome() {
        Collection<Double> incomes = incomeGraphData.values();

        double total=0;
        for (Double q: incomes) {
            total+=q;
        }
        return total/productionGraphData.keySet().size();
    }

//    public double calculateAverageIncome() {
//        ArrayList<Double> values = ((ArrayList<Double>) incomeGraphData.values());
//        Set keys =  productionGraphData.keySet();
//
//        double total = 0;
//
//        for (Double item : values) {
//            total += item;
//        }
//
//        return total/keys.size();
//    }
//
//    public double calculateAverageQuantity() {
//        ArrayList<Double> values = ((ArrayList<Double>) productionGraphData.values());
//        Set keys =  incomeGraphData.keySet();
//
//        double total = 0;
//
//        for (Double value : values) {
//            total += value;
//        }
//
//        return total/keys.size();
//    }

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
