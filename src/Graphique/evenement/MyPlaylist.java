package graphique.evenement;

import graphique.MyWindow;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

import main.Main;
import mp3player.PlayerController;
import mp3player.Status;
import mp3tag.Tag;
import music.ListMusic;
import music.Music;

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
						public void actionPerformed(ActionEvent e) {
							
							
					            int row = MyWindow.tableau.getSelectedRow();
					            Object musicAAjouter = MyWindow.tableau.getValueAt(row, 0);
					            Music music = null;
	                            for(Music m : MyWindow.listMusic)
	                            {
	                                if(m.getTitle().equals(musicAAjouter.toString()))
	                                    music = m;
	                            }

	                            ListMusic listMusic = new ListMusic();
					            System.out.println("HAH"+Main.fileName );
					            MyWindow.persoTablePlaylist.addMusic(music);
						}
					});
					
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
					            
						}
					});
				}
		
	}



}
