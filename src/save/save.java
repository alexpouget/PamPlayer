package save;

import music.ListMusic;

import java.io.File;

/**
 * Created by alex on 29/06/2015.
 */
public class save {
    File saveDir;

    public save(File saveDir) {
        this.saveDir = saveDir;
    }

    public File getSaveDir() {
        return saveDir;
    }

    public void setSaveDir(File saveDir) {
        this.saveDir = saveDir;
    }
}
