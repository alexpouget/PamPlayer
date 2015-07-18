package graphique.evenement;

import graphique.MyWindow;
import graphique.tableau.PlaylistTableModel;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import music.ListMusic;
import music.Music;

public class MyLoad implements ActionListener {
	Frame[] frames=MyWindow.getFrames();
	JDialog popUpBadFile= null;
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooserLoadPlaylist= new JFileChooser();
		
		// filtre pour les fichiers txt
		FileNameExtensionFilter filter = new FileNameExtensionFilter("fichiers txt format Json", "txt");
		chooserLoadPlaylist.setFileFilter(filter);
		if (chooserLoadPlaylist.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {


			ListMusic listMusic = new ListMusic(chooserLoadPlaylist.getSelectedFile().getPath(), true);


		}
	}



}