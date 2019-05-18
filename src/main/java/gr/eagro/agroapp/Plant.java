package gr.eagro.agroapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Plant {
    protected String id;
    protected Fertilizer fertilizer;
    protected String name;
    protected ArrayList<Plant> hybrids = new ArrayList<Plant>();
    protected boolean isHybrid;
    protected boolean hasHybrids;



    /* Constructor */

    public Plant(String id, Fertilizer fertilizer, String name, ArrayList<Plant> hybrids, boolean isHybrid, boolean hasHybrids) {
        this.id = id;
        this.fertilizer = fertilizer;
        this.name = name;
        this.hybrids = hybrids;
        this.isHybrid = isHybrid;

        if(this.isHybrid){
            this.addHyrbid(this);
        }

        this.hasHybrids = hasHybrids;
    }
    public Plant(String id, Fertilizer fertilizer, String name,  boolean isHybrid, boolean hasHybrids) {
        this.id = id;
        this.fertilizer = fertilizer;
        this.name = name;
        this.isHybrid = isHybrid;
        this.hasHybrids = hasHybrids;
    }

    public Plant(){}

    public void addHyrbid(Plant p){
        if(this.isHybrid)
            hybrids.add(p); /* methodos poy prosthetei sti lista twn ybridiwn ena fyto*/
    }

   public File getInfo(String key, String resultId){
        File f;
        if(key.toLowerCase().equals("hybrids")){
            f = new File("./info/hybrids/" + this.id + "/" + resultId + ".txt");
        }else if (key.toLowerCase().equals("diseases")){
            f = new File("./info/diseases/" + this.id + "/" + resultId + ".txt");
        }else f=null;

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

   public File getInfo(String key){
        File f;
        if (key.toLowerCase().equals("fertilizer")){
            f= this.fertilizer.getInfo();
        }else f = null;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Plant> getHybrids() {
        return hybrids;
    }

    public void setHybrids(ArrayList<Plant> hybrids) {
        this.hybrids = hybrids;
    }

    public boolean isHybrid() {
        return isHybrid;
    }

    public void setHybrid(boolean hybrid) {
        isHybrid = hybrid;
    }

    public boolean isHasHybrids() {
        return hasHybrids;
    }

    public void setHasHybrids(boolean hasHybrids) {
        this.hasHybrids = hasHybrids;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id='" + id + '\'' +
                ", fertilizer=" + fertilizer +
                ", name='" + name + '\'' +
                ", hybrids=" + hybrids +
                ", isHybrid=" + isHybrid +
                ", hasHybrids=" + hasHybrids +
                '}';
    }
}
