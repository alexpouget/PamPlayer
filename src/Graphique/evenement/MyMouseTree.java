package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.MouseEvent;

import javax.swing.tree.TreePath;

public class MyMouseTree {
	public void mouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 1) {
	        TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
	        if (path != null) {
	            System.out.println(path.getLastPathComponent().toString());
	        }
	    }
	}
}

