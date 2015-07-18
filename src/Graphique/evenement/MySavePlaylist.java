package graphique.evenement;

import graphique.MyWindow;
import graphique.tableau.PlaylistTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import music.ListMusic;
import music.Music;

public class MySavePlaylist implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooserSavePlaylist= new JFileChooser();
	    if (chooserSavePlaylist.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
		ArrayList<Music> listMusics=PlaylistTableModel.musicArrayList;
		// on recupère le chemin du fichier de playlist voulue
		ListMusic listMusic = new ListMusic(chooserSavePlaylist.getSelectedFile().getPath());
		// on parcours celui ci
		for(Music song: listMusics)
		{
			// on ajoute dans le fichier les musiques
			listMusic.addMusic(song,chooserSavePlaylist.getSelectedFile().getPath());
		}
	
	}

}
}
