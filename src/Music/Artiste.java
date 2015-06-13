package music;

import java.io.Serializable;

/**
 * Created by alex on 10/06/2015.
 */
public class Artiste implements Serializable {
    private String name;

    public Artiste(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
