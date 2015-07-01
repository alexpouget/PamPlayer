package music;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
