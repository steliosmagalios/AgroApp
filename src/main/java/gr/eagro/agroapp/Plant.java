package gr.eagro.agroapp;

import java.io.InputStream;
import java.util.ArrayList;

public abstract class Plant  implements java.io.Serializable {
    protected String id;
    protected Fertilizer fertilizer;
    protected String name;
    protected ArrayList<Plant> hybrids = new ArrayList<>() ;
    protected ArrayList<String> diseases = new ArrayList<>();
    protected boolean isHybrid;



    /* Constructor */


    public Plant(String id, Fertilizer fertilizer, String name, boolean isHybrid) {
        this.id = id;
        this.fertilizer = fertilizer;
        this.name = name;
        this.isHybrid = isHybrid;

    }
    public Plant(String id, Fertilizer fertilizer, String name) {
       this(id,fertilizer,name,false);
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
        InputStream fInputStream;
        if(key.toLowerCase().equals("hybrids")){
            fInputStream  = getClass().getResourceAsStream("/info/hybrids/" + this.id + "/" + resultId + ".txt");
        }else if (key.toLowerCase().equals("diseases")){
            fInputStream  = getClass().getResourceAsStream("/info/diseases/" + this.id + "/" + resultId + ".txt");
        }else fInputStream=null;

        return fInputStream;
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


    public ArrayList<String> getDiseases() {
        return diseases;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
