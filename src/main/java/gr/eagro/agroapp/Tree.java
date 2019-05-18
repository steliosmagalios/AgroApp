package gr.eagro.agroapp;

import java.util.ArrayList;

public class Tree extends Plant {

    private int quantity;

    /*Constructor*/

    public Tree(String id, Fertilizer fertilizer, String name, ArrayList<Plant> hybrids, boolean isHybrid, boolean hasHybrids, int quantity) {
        super(id, fertilizer, name, hybrids, isHybrid, hasHybrids);
        this.quantity = quantity;
    }

    public Tree(String id, Fertilizer fertilizer, String name, boolean isHybrid, boolean hasHybrids, int quantity) {
        super(id, fertilizer, name, isHybrid, hasHybrids);
        this.quantity = quantity;
    }

    public Tree(int quantity) {
        this.quantity = quantity;
    }
}
