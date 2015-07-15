package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.RowFilter;
import javax.swing.tree.TreePath;

import music.ListMusic;

public class MyMouseTree extends MouseAdapter {
	public void mouseClicked(MouseEvent evt) {		
		TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
		try{
		if(path.toString().equals("[Bibliotheque]"))
		{
			new music.ListMusic();
			MyWindow.sorter.setRowFilter(null);
		}
		
			String element = path.getLastPathComponent().toString(); //recuperation du composant du dernier click
			if(MyWindow.listArtist.contains(element))
				MyWindow.sorter.setRowFilter(RowFilter.regexFilter("(?i)"+element, 1));
			if(MyWindow.listAlbum.contains(element))
				MyWindow.sorter.setRowFilter(RowFilter.regexFilter(element, 2));
		}catch(Exception e){}
		
		
	}
}

