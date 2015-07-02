package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import synchronisation.SynchroMusic;

public class MySynchro implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		SynchroMusic synchroMusic = new SynchroMusic();
		
    	try{
		
			MyWindow.labelResultSynchro.setText(synchroMusic.comparerMusique(new File("Ressource/folder/"), (new File("Ressource/folder1/"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}

}
