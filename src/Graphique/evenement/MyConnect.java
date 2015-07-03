package graphique.evenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyConnect implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(MyConnexion.fieldLogin.getText().equals("ben") && MyConnexion.fieldMdp.getText().equals("ben"))
		{
			System.out.println("Vous etes co!!!");
			MyConnexion.labelErreurCo.setVisible(false);
			MyConnexion.connexion.dispose();
		}
		else
		{
			MyConnexion.labelErreurCo.setText("Erreur Login/mdp");
		}
		
	}

}
