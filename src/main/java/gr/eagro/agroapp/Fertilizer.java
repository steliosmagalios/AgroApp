package gr.eagro.agroapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fertilizer {
    private String id;
    private double quantity;
    private double cost;

    public Fertilizer(String id, double quantity, double cost) {
        this.id = id;
        this.quantity = quantity;
        this.cost = cost;

    }

    public double calculateCost(double fertilizerQuantity, double cost) {
        double totalcost;
        totalcost = fertilizerQuantity * cost;
        return totalcost;
    }

    //public double calculateQuantity(double plantQuiantity) {
        /* somehow i have to return the quantinty of the fertilizer required, based on the quantity of the plants*/

    //}

    public File getInfo(){

        File f = new File("./info/fertilizer/" + this.id + ".txt" );

        // check if the file exists
        try {
            Scanner scan = new Scanner(f);

            String line = scan.nextLine();
            if(line.isEmpty()){
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return f;
    }




    }

