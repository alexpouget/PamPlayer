package graphique.tableau;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import music.ListMusic;
import music.Music;

public class PlaylistTableModel extends AbstractTableModel{

	public static ArrayList<Music> musicArrayList;
	private final String[] entetes = {"Titre", "Artiste", "Album", "Duree"};
	public PlaylistTableModel() {
		super();
		musicArrayList = new ArrayList<Music>();


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

	public void addMusic(Music m) {
		musicArrayList.add(m);

		fireTableRowsInserted(musicArrayList.size() -1, musicArrayList.size() -1);
	}

	public void removeMusic(int rowIndex) {
		musicArrayList.remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}
}
