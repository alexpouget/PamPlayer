package graphique.tableau;

import music.ListMusic;
import music.Music;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 05/07/2015.
 *
 * class pour configurer le model du tableau
 */
public class PersoTableModel extends AbstractTableModel {

    private ArrayList<Music> musicArrayList;
    //on definit les entete de colonne tu tableau
    private final String[] entetes = {"Titre", "Artiste", "Album", "Duree"};

    //constructeur du tableau et on charge la liste
    public PersoTableModel() {
        super();
        ListMusic list = new ListMusic();
        musicArrayList = list.getList();
    }

    public int getRowCount() {
        return musicArrayList.size();
    }

    public int getColumnCount() {
        return entetes.length;
    }

    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                if(musicArrayList.get(rowIndex).getTitle()!=null) {
                    return musicArrayList.get(rowIndex).getTitle();
                }
                else{
                    return "";
                }
            case 1:
                if(musicArrayList.get(rowIndex).getArtiste()!=null) {
                    return musicArrayList.get(rowIndex).getArtiste().getName();
                }
                else{
                    return "";
                }
            case 2:
                if(musicArrayList.get(rowIndex).getAlbum()!=null) {
                    return musicArrayList.get(rowIndex).getAlbum().getName();
                }
                else{
                    return "";
                }
            case 3:
                return musicArrayList.get(rowIndex).getDuree();
            default:
                return null; //Ne devrait jamais arriver
        }
    }

    //ajoute une ligne
   public void addMusic(Music m) {
        musicArrayList.add(m);
        //on insert la ligne en prenant ce qui est en dernier dans la liste
        fireTableRowsInserted(musicArrayList.size() -1, musicArrayList.size() -1);
    }

    //on supprimme l'element de la liste et on suprimme une ligne
    public void removeMusic(Music m) {
        for(int i = 0;musicArrayList.size()>i;i++){
            if(((Music)musicArrayList.get(i)).getTitle().compareToIgnoreCase(m.getTitle())==0){
                musicArrayList.remove(i);
            }
        }
        fireTableRowsDeleted(musicArrayList.size() -1, musicArrayList.size() -1);
    }

}
