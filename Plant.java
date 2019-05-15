import java.io.File;
import java.util.ArrayList;

public abstract class Plant {
    protected String id;
    protected String name;
    protected Fertilizer fertilizer;
    protected boolean isHybrid;
    protected boolean hasHybrids;
    protected ArrayList<Plant> hybrids = new ArrayList<>();


    /* Constructor */
    public Plant(String id, String name, ArrayList<Plant> hybrids, Fertilizer fertilizer,boolean isHybrid){
        this.hybrids = hybrids;
        this.fertilizer = fertilizer;
        this.name = name;
        this.id = id;

       this.isHybrid = isHybrid; /* an einai ybridio tha orizetai apo tin klisi toy kataskeyasti os true alliws os false*/

        if(hybrids.size() >= 1) {
            hasHybrids = true; /* an i lista twn ybridiwn exei parapanw toylaxiston 1 stoixeio tote to fyto exei ybridia*/
        }
    }


    public void addHyrbid(Plant p){
        hybrids.add(p); /* methodos poy prosthetei sti lista twn ybridiwn ena fyto*/
    }

    public File[] getInfo(String key)
    {
        File directory = new File(key);

        /* get all the files from a directory */
        File[] fList = directory.listFiles();


        for(File file: fList){
            if(file.isFile()){
                System.out.println(file.getAbsolutePath());
            }
            else if(file.isDirectory()){
                getInfo(file.getAbsolutePath());

            }
        }

        return fList;
    }
}
