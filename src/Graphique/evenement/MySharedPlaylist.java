package graphique.evenement;

import graphique.MyWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import music.ListMusic;
import music.Music;
import database.ConnectDb;

public class MySharedPlaylist implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		HashMap<String, String> playlistToShare= new HashMap();
		Connection connexion=ConnectDb.connectDb("jdbc:mysql://127.0.0.1/pam","root","");

		// recupération des valeurs de la jtable de playlist pour les mettre dans la hashmap
		for(int i=0;i<MyWindow.tableauPlaylist.getRowCount();i++)
		{
			playlistToShare.put(MyWindow.tableauPlaylist.getValueAt(i, 0).toString(), MyWindow.tableauPlaylist.getValueAt(i, 1).toString());
		}

		String recupPlaylist=playlistToShare.entrySet().toString();
		String playlistApresTraitement=recupPlaylist.replaceAll("=", "-");


		int userId=0;
		
		// recupération de l'id du user pour ensuite la partager
		try {
			userId = database.UserUtils.recupererIdUser(MyConnect.currentUser.getLogin());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// insertion dans la table playlist pour ce user
		String requete = "INSERT INTO playlist(`contenu_json`,`user_id`) VALUES ('"+playlistApresTraitement+"',"+userId+")";

		try {

			Statement stmt = connexion.createStatement();

			int res= stmt.executeUpdate(requete);



		} catch (SQLException ex) {

			ex.printStackTrace();

		}


	}

}
