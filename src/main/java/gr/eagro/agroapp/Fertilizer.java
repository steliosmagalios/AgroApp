package gr.eagro.agroapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Fertilizer  implements java.io.Serializable{
    private String id;
    private double quantity;
    private double cost;



    public Fertilizer(String id, double quantity, double cost) {
        this.id = id;
        this.quantity = quantity;
        this.cost = cost;

    }

    public double calculateCost(double fertilizerQuantity) {
        double totalcost;
        totalcost = fertilizerQuantity * this.cost;
        return totalcost;
    }

    public double calculateQuantity(Plant aPlant) {
        File f = new File("./info/plants/fertilizer/" + aPlant.getId() + ".txt");

        try {
            Scanner scan = new Scanner(f);

            String q = scan.next();
            if (!(q.isEmpty())) {
                return this.quantity * Double.parseDouble(q);
            } else return 0;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public InputStream getInfo(){
        InputStream fInputStream;

        fInputStream  = getClass().getResourceAsStream("/info/fertilizer/" + this.id + ".txt" );

        return fInputStream;

    }




    }

