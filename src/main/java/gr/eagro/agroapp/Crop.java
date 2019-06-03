package gr.eagro.agroapp;

import java.util.ArrayList;


public class Crop extends Plant {

    private double quantity;

    /*Constructor*/

    /**
     *
     * @param id
     * @param fertilizer
     * @param name
     * @param isHybrid
     * @param quantity
     */
    public Crop(String id, Fertilizer fertilizer, String name, boolean isHybrid, double quantity) {
        super(id, fertilizer, name, isHybrid);
        this.quantity = quantity;
    }

    /**
     *
     * @param id
     * @param fertilizer
     * @param name
     * @param quantity
     */
    public Crop(String id, Fertilizer fertilizer, String name, double quantity) {
        super(id, fertilizer, name);
        this.quantity = quantity;
    }
}