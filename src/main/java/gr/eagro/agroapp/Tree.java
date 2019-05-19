package gr.eagro.agroapp;

import java.util.ArrayList;

public class Tree extends Plant {

    private int quantity;

    /*Constructor*/

    public Tree(String id, Fertilizer fertilizer, String name, boolean isHybrid, int quantity) {
        super(id, fertilizer, name, isHybrid);
        this.quantity = quantity;
    }

    public Tree(String id, Fertilizer fertilizer, String name, int quantity) {
        super(id, fertilizer, name);
        this.quantity = quantity;
    }
}

