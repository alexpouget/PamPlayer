package mp3tag;

import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.v2.ID3V2Tag;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by alex on 11/06/2015.
 *
 * Classe qui utilise l'API ID3V2Tag pour lire les meta-data contenue dans les fichier MP3
 * et ainsi connaitre si renseigner le titre l'album etc ...
 */
public class Tag {
    private String title,album,artiste;

    //constructeur
    public Tag(String s) throws ID3Exception, Exception {

        File file = new File(s);
        MP3File mp3File = new MP3File(file);
        // Appel de l'api ID3V2Tag et application sur notre fichier
        ID3V2Tag tag = mp3File.getID3V2Tag();
        setTitle(tag.getTitle());
        setAlbum(tag.getAlbum());

        //initialisation des valeurs en fonction des meta-data et sub-string artiste en featuring contenue dans le titre
        String artist3=tag.getArtist();
        String arti;
        String feat="";

        if(artist3 != null) {
        	if (artist3.contains("FT"))
                feat = "FT";
        	if (artist3.contains("FT."))
                feat = "FT.";
        	if (artist3.contains("Ft"))
                feat = "Ft";
        	if (artist3.contains("Ft."))
                feat = "Ft.";
            if (artist3.contains("feat"))
                feat = "feat";
            if (artist3.contains("Feat."))
                feat = "Feat.";
            if (artist3.contains("Feat"))
                feat = "Feat";
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
            if (artist3.contains(",")){
              	feat = ",";
            }

            if(feat.equalsIgnoreCase("")) {
                arti = artist3;
            }else{
                arti = artist3.substring(0, artist3.indexOf(feat));
            }

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
