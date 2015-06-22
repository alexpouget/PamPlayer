package mp3tag;

import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.v2.ID3V2Tag;

import java.io.File;

/**
 * Created by alex on 11/06/2015.
 */
public class Tag {
    String title,album,artiste;

    public Tag(String s) throws ID3Exception {
        File file = new File(s);
        MP3File mp3File = new MP3File(file);
        mp3File.getID3V2Tag();
        ID3V2Tag tag = mp3File.getID3V2Tag();
        setTitle(tag.getTitle());
        setAlbum(tag.getAlbum());
        setArtiste(tag.getArtist());
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }
}
