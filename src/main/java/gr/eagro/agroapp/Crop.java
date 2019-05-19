package gr.eagro.agroapp;

import java.util.ArrayList;

public class Crop extends Plant {

    private double quantity;

    /*Constructor*/

    public Crop(String id, Fertilizer fertilizer, String name, boolean isHybrid, double quantity) {
        super(id, fertilizer, name, isHybrid);
        this.quantity = quantity;
    }

    public Crop(String id, Fertilizer fertilizer, String name, double quantity) {
        super(id, fertilizer, name);
        this.quantity = quantity;
    }
}