package music;

import java.io.Serializable;

/**
 * Created by alex on 10/06/2015.
 */
public class Music implements Serializable {
    private int duree; //in milliseconds
    private String path,title;
    private Artiste artiste;
    private Album album;

    public Music(int duree, String path, String title, Artiste artiste, Album album) {
        this.duree = duree;
        this.path = path;
        this.title = title;
        this.artiste = artiste;
        this.album = album;
    }

    public Music(int duree, String path, String title) {
        this.duree = duree;
        this.path = path;
        this.title = title;
    }

    public Music(String path, String title) {
        this.path = path;
        this.title = title;
    }

    public Music(){

    }

    @Override
    public String toString() {
        return title ;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
