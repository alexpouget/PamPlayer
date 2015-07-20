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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.Main;
import music.Album;
import music.Artiste;
import music.ListMusic;
import music.Music;
import database.ConnectDb;

public class MySharedPlaylist implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		
		Connection connexion=ConnectDb.connectDb("jdbc:mysql://127.0.0.1/pam","root","root");

		// recupération des valeurs de la jtable de playlist pour les mettre dans la hashmap
		ArrayList<Music> publi = new ArrayList<Music>();

		for(int i=0;i<MyWindow.tableauPlaylist.getRowCount();i++){
			Music m = new Music();
			if(MyWindow.tableauPlaylist.getValueAt(i, 0)!=null){
				m.setTitle(MyWindow.tableauPlaylist.getValueAt(i, 0).toString());
			}
			if(MyWindow.tableauPlaylist.getValueAt(i, 1)!=null){
				m.setArtiste(new Artiste(MyWindow.tableauPlaylist.getValueAt(i, 1).toString()));
			}
			if(MyWindow.tableauPlaylist.getValueAt(i, 2)!=null){
				m.setAlbum(new Album(MyWindow.tableauPlaylist.getValueAt(i, 2).toString()));
			}
			publi.add(m);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String playlistApresTraitement="";
		
		try {
			playlistApresTraitement = mapper.writeValueAsString(publi);
			
		} catch (JsonProcessingException e2) {
			// TODO Auto-generated catch block
			Main.logger.error(e2.getMessage());
			e2.printStackTrace();
		}

		int userId=0;
		
		// recupération de l'id du user pour ensuite la partager
		try {
			userId = database.UserUtils.recupererIdUser(MyConnect.currentUser.getLogin());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			Main.logger.error(e1.getMessage());
			e1.printStackTrace();
		}
		// insertion dans la table playlist pour ce user
		String requete = "INSERT INTO playlist(`contenu_json`,`user_id`) VALUES ('"+playlistApresTraitement+"',"+userId+")";

		try {

			Statement stmt = connexion.createStatement();

			int res= stmt.executeUpdate(requete);



		} catch (SQLException ex) {
			Main.logger.error(ex.getMessage());
			ex.printStackTrace();

		}


	}

}
