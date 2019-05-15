import java.io.File;

public class Fertilizer {
    private String id;
    private double quantity;
    private double cost;

    public Fertilizer (String id, double quantity, double cost)
    {
        this.id = id;
        this.quantity = quantity;
        this.cost = cost;

    }

    public double calculateCost(double quantity, double cost)
    {
        double totalcost;
        totalcost = quantity*cost;
        return totalcost;
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
