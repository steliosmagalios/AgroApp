package gr.eagro.agroapp.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.Serializable;

public class Fertilizer  implements Serializable{
    private String id;
    private double quantity;
    private double cost;

    /**
     *
     * @param id
     * @param quantity
     * @param cost
     */

    public Fertilizer(String id, double quantity, double cost) {
        this.id = id;
        this.quantity = quantity;
        this.cost = cost;

    }

    /**
     *
     * @param fertilizerQuantity
     * @return
     */
    public double calculateCost(double fertilizerQuantity) {

        return   fertilizerQuantity * this.cost;
    }

    /**
     *
     * @param aPlant
     * @return
     */
    public double calculateQuantity(Plant aPlant) {

        if(aPlant.getClass().getSimpleName().equals("Tree")){
            return this.quantity * ((Tree)aPlant).getQuantity();
        }else return this.quantity * ((Crop)aPlant).getQuantity();

    }

    /**
     *
     * @return
     */
    public InputStream getInfo(){
        InputStream fInputStream;

        fInputStream  = getClass().getResourceAsStream("/info/fertilizer/" + this.id + ".txt" );

        return fInputStream;

    }


}

