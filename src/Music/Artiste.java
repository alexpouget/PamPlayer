package music;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by alex on 10/06/2015.
 *
 * Classe Artiste qui contiens un champ name de l'artiste
 */
public class Artiste implements Serializable {
    private String name;

    public Artiste() {
    }

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
