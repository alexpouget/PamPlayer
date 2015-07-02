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
    float duree;

    public Tag(String s) throws ID3Exception {
        File file = new File(s);
        MP3File mp3File = new MP3File(file);
        mp3File.getID3V2Tag();
        ID3V2Tag tag = mp3File.getID3V2Tag();
        setTitle(tag.getTitle());
        setAlbum(tag.getAlbum());
        
        String artist3=tag.getArtist();
        String arti;
        String feat="";

        if(artist3 != null) {
            if (artist3.contains("feat"))
                feat = "feat";
            if (artist3.contains("ft"))
                feat = "ft";
            if (artist3.contains("ft."))
                feat = "ft.";
            if (artist3.contains("feat."))
                feat = "feat.";
            if (artist3.contains("featuring."))
                feat = "featuring.";
            if (artist3.contains("featuring"))
                feat = "featuring";

            arti = artist3.substring(0, artist3.indexOf(feat));

            setArtiste(arti);
        }
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
