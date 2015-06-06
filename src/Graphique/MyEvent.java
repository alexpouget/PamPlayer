package Graphique;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;


public class MyEvent extends WindowAdapter {
	//action sur la fermeture de la fenetre 
		@Override
		public void windowClosing(WindowEvent e) {
			int rep = JOptionPane.showConfirmDialog(null, "Quitter PamPlayer ?");
			if(rep==0)
				System.exit(0);
		}
}


