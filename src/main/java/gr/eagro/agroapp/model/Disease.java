package gr.eagro.agroapp.model;

import java.io.Serializable;

public class Disease implements Serializable {

    private String name;
    private String id;

    public Disease(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
