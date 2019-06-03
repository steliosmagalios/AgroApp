package gr.eagro.agroapp.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.io.Serializable;


public abstract class Plant  implements Serializable {
    protected String id;
    protected Fertilizer fertilizer;
    protected String name;
    protected ArrayList<Plant> hybrids = new ArrayList<>() ;
    protected ArrayList<String> diseases = new ArrayList<>();
    protected boolean isHybrid;



    /* Constructor */

    /**
     *
     * @param id
     * @param fertilizer
     * @param name
     * @param isHybrid
     */
    public Plant(String id, Fertilizer fertilizer, String name, boolean isHybrid) {
        this.id = id;
        this.fertilizer = fertilizer;
        this.name = name;
        this.isHybrid = isHybrid;

    }

    /**
     *
     * @param id
     * @param fertilizer
     * @param name
     */
    public Plant(String id, Fertilizer fertilizer, String name) {

        this(id,fertilizer,name,false);
    }


    /**
     *
     * @param p
     */

    public void addHyrbid(Plant p){
        if(p.isHybrid)
            hybrids.add(p); /* methodos poy prosthetei sti lista twn ybridiwn ena fyto*/
    }

    /**
     *
     * @param d
     */
    public void addDisease(String d){

        diseases.add(d);
    }

    /**
     *
     * @return
     */
    public boolean hasHybrids(){
        return this.hybrids.size()>0 ;
    }

    /**
     *
     * @param key
     * @param resultId
     * @return
     */
   public InputStream getInfo(String key, String resultId){
        InputStream fInputStream;
        if(key.toLowerCase().equals("hybrids")){
            fInputStream  = getClass().getResourceAsStream("/info/hybrids/" + this.id + "/" + resultId + ".txt");
        }else if (key.toLowerCase().equals("diseases")){
            fInputStream  = getClass().getResourceAsStream("/info/diseases/" + this.id + "/" + this.getDiseaseId(resultId) + ".txt");
        }else fInputStream=null;



        return fInputStream;
   }

   private String getDiseaseId(String aString){
       return aString.replaceAll("\\s+","").toLowerCase();
   }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return
     */

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    /**
     *
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */

    public ArrayList<Plant> getHybrids() {
        return hybrids;
    }

    /**
     *
     * @return
     */

    public boolean isHybrid() {

        return isHybrid;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getDiseases() {
        return diseases;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {

        return this.name;
    }
}
