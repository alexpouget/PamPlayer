package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import synchronisation.SynchroMusic;

// Listener pour la synchronisation de musiques
public class MySynchro implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooserDossier1 = new JFileChooser(new File("."));

		// selection du premier dossier a synchroniser
		if (e.getActionCommand().equalsIgnoreCase("Choisir dossier 1")) {
			chooserDossier1 = new JFileChooser(new File("."));
			chooserDossier1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooserDossier1.setAcceptAllFileFilterUsed(false);
			if (chooserDossier1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

				MyWindow.pathDossier1=chooserDossier1.getSelectedFile().getPath();

			}
		}
		// selection du second dossier a synchroniser
		if (e.getActionCommand().equalsIgnoreCase("Choisir dossier 2")) {
			chooserDossier1 = new JFileChooser(new File("."));
			chooserDossier1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooserDossier1.setAcceptAllFileFilterUsed(false);
			if (chooserDossier1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

				MyWindow.pathDossier2=chooserDossier1.getSelectedFile().getPath();

			}
		}
		
		// si les deux dossiers ont été choisis
		if(MyWindow.pathDossier1!=null && MyWindow.pathDossier2!=null)
		{
			
			MyWindow.buttonSynchro.setEnabled(true);
			// si le boutton lancer synchro est appuyé
			if(e.getActionCommand().equals("Lancer Synchro musique"))
			{
				// on lance la synchronisation et on affiche le résultat
				try{
					SynchroMusic synchroMusic = new SynchroMusic();
					String resSyncrho=synchroMusic.comparerMusique(new File(MyWindow.pathDossier1), (new File(MyWindow.pathDossier2)));
					String partieAjoute=resSyncrho.substring(resSyncrho.lastIndexOf("]")+1);
					String resApresTraitement=resSyncrho.substring(1, resSyncrho.lastIndexOf("]"))+partieAjoute;
					resApresTraitement="<html>"+resApresTraitement.replaceAll(",", "<br>")+"</html>";
					MyWindow.labelResultSynchro.setText(resApresTraitement);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}



}
