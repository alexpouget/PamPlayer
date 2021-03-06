package music;

import java.io.Serializable;
/**
 * Created by alex on 10/06/2015.
 *
 * Classe Album qui contiens un name qui est le titre de l'album
 */

public class Album implements Serializable {
    String name;

    public Album(String name) {
        this.name = name;
    }

    public Album() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString()
    {
    	return this.name;
    }
}
