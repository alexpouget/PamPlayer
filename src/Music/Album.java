package music;

import java.io.Serializable;

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
