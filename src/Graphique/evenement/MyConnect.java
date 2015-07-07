package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyConnect implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String mdpEnString= new String(MyConnexion.fieldMdp.getPassword());
		if(database.VerifConnexion.verifConnexion(MyConnexion.fieldLogin.getText(), mdpEnString)==true)
		{
			MyConnexion.labelErreurCo.setVisible(false);
			MyConnexion.connexion.dispose();
			MyWindow.labelConnected.setText("Vous est connecté en tant que "+MyConnexion.fieldLogin.getText());
		}
		else
		{
			MyConnexion.labelErreurCo.setText("Erreur Login/mdp");
		}
		
	}

}
