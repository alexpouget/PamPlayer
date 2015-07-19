package graphique.evenement;

import graphique.MyWindow;
import graphique.tableau.PlaylistTableModel;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import main.Main;
import mp3Player.PlayerController;
import mp3Player.Status;
import mp3tag.Tag;
import music.ListMusic;
import music.Music;

// Listerner qui permet la gestion des actions du clique droit la liste des musiques
public class MyPlaylist extends MouseAdapter{
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Left mouse click

		// Right mouse click
		if ( SwingUtilities.isRightMouseButton( e ))
		{
			// get the coordinates of the mouse click
			Point p = e.getPoint();

			// get the row index that contains that coordinate
			int rowNumber = MyWindow.tableau.rowAtPoint( p );

			// Get the ListSelectionModel of the JTable
			ListSelectionModel model = MyWindow.tableau.getSelectionModel();

			// set the selected interval of rows. Using the "rowNumber"
			// variable for the beginning and end selects only that one row.
			model.setSelectionInterval( rowNumber, rowNumber );
			JPopupMenu menuPopUpClickDroit= new JPopupMenu();
			MyWindow.tableau.setComponentPopupMenu(menuPopUpClickDroit);
			JMenuItem itemMenuClickDroit=new JMenuItem("Ajouter a la playlist");
			menuPopUpClickDroit.add(itemMenuClickDroit);
			JMenuItem itemMenuClickDroit2=new JMenuItem("Supprimer la chanson");
			menuPopUpClickDroit.add(itemMenuClickDroit2);
			menuPopUpClickDroit.show(e.getComponent(), e.getX(), e.getY());

			// evenement lorsque l'on clique sur ajouter a la playlist
			itemMenuClickDroit.addActionListener(new ActionListener() {



				@Override
				// ajout de la musique Ã  la playlist
				public void actionPerformed(ActionEvent e) {


					int row = MyWindow.tableau.getSelectedRow();
					Object musicAAjouter = MyWindow.tableau.getValueAt(row, 0);
					Music music = null;
					for(Music m : MyWindow.listMusic)
					{
						if(m.getTitle().equals(musicAAjouter.toString()))
							music = m;
					}


					MyWindow.persoTablePlaylist.addMusic(music);

				}
			});

			// suppression de la musique de la bibliotheque
			itemMenuClickDroit2.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int row = MyWindow.tableau.getSelectedRow();
					Object cellule = MyWindow.tableau.getValueAt(row, 0);
					Music music = null;
					for(Music m : MyWindow.listMusic)
					{
						if(m.getTitle().equals(cellule.toString()))
							music = m;
					}

					ListMusic listMusic = new ListMusic();
					listMusic.removeMusic(music);
					MyWindow.persoTableModel.removeMusic(music);
					
					DefaultTreeModel model = (DefaultTreeModel)MyWindow.arbre.getModel();

					DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
					String lartiste = music.getArtiste().getName().toUpperCase().trim();
					String lalbum = music.getAlbum().getName().trim();
					int nbrMusicAyantLalbum=0;
					
					/*parcourt de la listmusic qui implémente le JTree et incrémentation de la vazriable
					 nbrMusicAyantLalbum afin de savoir si l'album de la chanson suppprimée contient plusieurs
					 chansons */
					
					try{
					for(Music son : MyWindow.listMusic){
						if(son.getAlbum().getName().equals(lalbum)){
							nbrMusicAyantLalbum++;
						}
					}
					
					/*Si l'album ne contient qu'une seul chanson alors on parcourt les noeuds de l'arbre
					  de facon a s'arreter sur le noeud concernant lartiste de la chanson supprimée. Si 
					  lartiste contient plusieurs albums alors on ne supprime que le noeud de l'album concerné.
					  Si l'artiste ne contient qu'un seul album alors on supprime le noeud de lartiste.*/
					
							if(nbrMusicAyantLalbum==1){
								for (int i = 0; i <= root.getChildCount(); i++){
									if(root.getChildAt(i).toString().equals(lartiste)){
										for(int k=0; k < root.getChildAt(i).getChildCount(); k++){
											if(root.getChildAt(i).getChildCount()==1){
												model.removeNodeFromParent((MutableTreeNode) root.getChildAt(i));
											}
											else{
												if(root.getChildAt(i).getChildAt(k).toString().equals(lalbum))
													model.removeNodeFromParent((MutableTreeNode) root.getChildAt(i).getChildAt(k));
												}
										}
									}
								}
							}
							//Suppression de la chanson de la listArtist et mise à jour du JTree							
						MyWindow.listMusic.remove(music);
					MyWindow.arbre.updateUI();
					model.reload(root);
				}catch(Exception ev){}
				}
			});

		}
	}
}
