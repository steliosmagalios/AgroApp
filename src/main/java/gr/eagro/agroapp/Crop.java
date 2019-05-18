package gr.eagro.agroapp;

import java.util.ArrayList;

public class Crop extends Plant {

    private double quantity;

    /*Constructor*/

    public Crop(String id, Fertilizer fertilizer, String name, ArrayList<Plant> hybrids, boolean isHybrid, boolean hasHybrids, double quantity) {
        super(id, fertilizer, name, hybrids, isHybrid, hasHybrids);
        this.quantity = quantity;
    }

    public Crop(String id, Fertilizer fertilizer, String name, boolean isHybrid, boolean hasHybrids, double quantity) {
        super(id, fertilizer, name, isHybrid, hasHybrids);
        this.quantity = quantity;
    }

    public Crop(double quantity) {
        this.quantity = quantity;
    }
}