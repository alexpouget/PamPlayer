package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.RowFilter;
import javax.swing.tree.TreePath;

public class MyMouseTree extends MouseAdapter {
	public void mouseClicked(MouseEvent evt) {
		TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
		
		try{
			
			String element = path.getLastPathComponent().toString(); //recuperation du composant du dernier click
			if(MyWindow.listAlbum.contains(element))
				MyWindow.sorter.setRowFilter(RowFilter.regexFilter(element, 2));
		}catch(Exception e){}
		
		/*if (evt.getClickCount() == 2) {
	        TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
	        if (path != null) {
	            System.out.println(path.getLastPathComponent().toString());
	        }
	    }*/
	}
}

