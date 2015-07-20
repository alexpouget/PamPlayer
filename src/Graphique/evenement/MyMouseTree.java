package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.RowFilter;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import main.Main;
import music.ListMusic;

public class MyMouseTree extends MouseAdapter implements TreeModelListener {
	public void mouseClicked(MouseEvent evt) {		
		TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
		try{
			if(path.toString().equals("[Bibliotheque]"))
			{
				new music.ListMusic();
				MyWindow.sorter.setRowFilter(null);
			}

			String element = path.getLastPathComponent().toString(); //recuperation du composant du dernier click

			/*si lelement clique est dans la liste des artist alors on filtre pour afficher
			 seulement les elements de cet artiste ((?i) pour ne pas faire attention a la case)
			  Pareil pour l'album. L'indice pour indiquera partit de quelle colonne de la JTable 
			  filtrer */

			if(MyWindow.listArtist.contains(element))
				MyWindow.sorter.setRowFilter(RowFilter.regexFilter("(?i)"+element, 1));
			if(MyWindow.listAlbum.contains(element))
				MyWindow.sorter.setRowFilter(RowFilter.regexFilter(element, 2));
		}catch(Exception e){
			Main.logger.error(e.getMessage());
		}

	}

	@Override
	public void treeNodesChanged(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesInserted(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeStructureChanged(TreeModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

