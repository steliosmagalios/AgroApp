package gr.eagro.agroapp;

import java.util.ArrayList;

public class Tree extends Plant {

    private int quantity;

    /*Constructor*/

    /**
     *
     * @param id
     * @param fertilizer
     * @param name
     * @param isHybrid
     * @param quantity
     */
    public Tree(String id, Fertilizer fertilizer, String name, boolean isHybrid, int quantity) {
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
    public Tree(String id, Fertilizer fertilizer, String name, int quantity) {
        super(id, fertilizer, name);
        this.quantity = quantity;
    }
}

