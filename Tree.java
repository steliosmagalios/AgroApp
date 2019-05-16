import java.util.ArrayList;

public class Tree extends Plant {

    private int quantity;

    /*Constructor*/
    public Tree(String id, String name, ArrayList<Plant> hybrids, Fertilizer fertilizer,boolean isHybrid, int quantity){
        super(id,name,hybrids,fertilizer,isHybrid);
        this.quantity = quantity;
    }





}
