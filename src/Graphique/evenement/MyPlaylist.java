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
import music.Music;

public class MyPlaylist extends MouseAdapter{
	private static PlayerController player;
	public static int maxLen;
	   private void startPlayer() {
        player = new PlayerController(Main.fileName);
        MyWindow.jSlider.setValue(0);
       try {
           Tag t = new Tag(Main.fileName);
           MyWindow.infoMusic.setText(t.getTitle());
       } catch (Exception e1) {
           e1.printStackTrace();
       }
       if (player == null) {
           System.out.println("mauvais fichier");
           Main.fileName = null;
           return;
       }
           player.play();


       maxLen = player.getFramesNumber();
       MyWindow.play.setText("pause");
   }
	public void mouseClicked(MouseEvent e) {

		System.out.println("édjdjdjjd");
        if (e.getClickCount() == 2) {
        	
               int row = MyWindow.tableau.getSelectedRow();
               System.out.println(row);
               Main.fileName = MyWindow.listMusic.get(row).getPath();
               System.out.println(Main.fileName);
               System.out.println(MyWindow.jSlider.getValue());
            if (Main.fileName == null)
                return;

            if (player == null) {
                startPlayer();
            } else if (player.getStatus() == Status.PAUSED) {
                player.resume();
                MyWindow.play.setText("pause");
            } else {
                player.stop();
                player = null;
                try {
                    Thread.sleep(100);
                    MyWindow.jSlider.setValue(0);
                    
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                startPlayer();
            }
        }
		
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
					menuPopUpClickDroit.show(e.getComponent(), e.getX(), e.getY());
					
					// evenement lorsque l'on clique sur ajouter a la playlist
					itemMenuClickDroit.addActionListener(new ActionListener() {
						
			
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							
					            int row = MyWindow.tableau.getSelectedRow();
					            Music musicAAjouter = MyWindow.listMusic.get(row);
					            System.out.println("HAH"+Main.fileName );
					            MyWindow.persoTablePlaylist.addMusic(musicAAjouter);
						}
					});
				}
		
	}



}
