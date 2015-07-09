package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.tree.TreePath;

public class MyMouseTree extends MouseAdapter {
	public void mouseClicked(MouseEvent evt) {
		TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
		
		try{
			String element = path.getLastPathComponent().toString(); //recuperation du composant du dernier click
			if(MyWindow.listArtist.contains(element))				
			System.out.println("You clicked on "+element+". He's an artist, boy.");
			ArrayList<String> musicDeLalbum = new ArrayList<String>(); //creation d une liste qui stockera les musiques de l album clicke
			if(MyWindow.listAlbum.contains(element))					//si l element clicke est dans la liste des albums
			{															//on parcourt la list des music et on ajoute a musicDeLalbum
																		//celle qui font parti de l album clicke
				System.out.println("You clicked on "+element+". It's an album, boy.");
				for(music.Music son : MyWindow.listMusic){
					if(son.getAlbum()==null)
						continue;
					if(son.getAlbum().toString().equals(element))
						musicDeLalbum.add(son.getTitle()); //System.out.println(son.getTitle());	
				}
			}
			for(String titre : musicDeLalbum)
			{
			System.out.println(titre);
			}
		}catch(Exception e){}
		
		/*if (evt.getClickCount() == 2) {
	        TreePath path = MyWindow.arbre.getPathForLocation(evt.getX(), evt.getY());
	        if (path != null) {
	            System.out.println(path.getLastPathComponent().toString());
	        }
	    }*/
	}
}

