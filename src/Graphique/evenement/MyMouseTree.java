package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.tree.TreePath;

public class MyMouseTree extends MouseAdapter {
	public void mouseClicked(MouseEvent evt) {
		TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
		
		try{
			String element = path.getLastPathComponent().toString();
			if(MyWindow.listArtist.contains(element))
			System.out.println("You clicked on "+element+". He's an artist, boy.");
			if(MyWindow.listAlbum.contains(element))
				System.out.println("You clicked on"+element+". It's an album, boy.");
				
		}catch(Exception e){System.out.println(e);}
		
		/*if (evt.getClickCount() == 2) {
	        TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
	        if (path != null) {
	            System.out.println(path.getLastPathComponent().toString());
	        }
	    }*/
	}
}

