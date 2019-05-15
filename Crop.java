import java.util.ArrayList;

public class Crop extends Plant {

    private double quantity;

    /*Constructor*/
    public Crop(String id, String name, ArrayList<Plant> hybrids, Fertilizer fertilizer,boolean isHybrid, double quantity) {
        super(id, name, hybrids, fertilizer,isHybrid);
        this.quantity = quantity;
    }

}