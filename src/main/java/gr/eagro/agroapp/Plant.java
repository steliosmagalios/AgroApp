package gr.eagro.agroapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Plant  implements java.io.Serializable {
    protected String id;
    protected Fertilizer fertilizer;
    protected String name;
    protected ArrayList<Plant> hybrids ;
    protected ArrayList<String> diseases;
    protected boolean isHybrid;



    /* Constructor */


    public Plant(String id, Fertilizer fertilizer, String name, boolean isHybrid) {
        this.id = id;
        this.fertilizer = fertilizer;
        this.name = name;
        this.isHybrid = isHybrid;

    }
    public Plant(String id, Fertilizer fertilizer, String name) {
        this.id = id;
        this.fertilizer = fertilizer;
        this.name = name;
        this.isHybrid = false;
    }


    public void addHyrbid(Plant p){
        if(p.isHybrid)
            hybrids.add(p); /* methodos poy prosthetei sti lista twn ybridiwn ena fyto*/
    }

    public void addDisease(String d){

        diseases.add(d);
    }

    public boolean hasHybrids(){
        return this.hybrids.size()>0 ;
    }

   public InputStream getInfo(String key, String resultId){
        File f;
        if(key.toLowerCase().equals("hybrids")){
            f = new File("resources/info/hybrids/" + this.id + "/" + resultId + ".txt");
        }else if (key.toLowerCase().equals("diseases")){
            f = new File("resources/info/diseases/" + this.id + "/" + resultId + ".txt");
        }else f=null;


        // check if the file exists
        if(f.exists()){
            try {
                 InputStream fInputStream = new FileInputStream(f);
                 return fInputStream;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
   }



    public String getId() {
        return id;
    }



    public Fertilizer getFertilizer() {
        return fertilizer;
    }



    public String getName() {
        return name;
    }



    public ArrayList<Plant> getHybrids() {
        return hybrids;
    }



    public boolean isHybrid() {
        return isHybrid;
    }





    @Override
    public String toString() {
        return this.name;
    }
}
