package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerifConnexion {

	public static boolean verifConnexion(String login, String mdp)
	{
		boolean isIdentified=false;
		try{
			// connexion a la base de donnees
			Connection connexion=ConnectDb.connectDb("jdbc:mysql://127.0.0.1/pam","root","root");
			// preparation de la requete
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM user WHERE pseudo=? AND pass=?");
			
			// on insere les valeurs pour les parametres
			statement.setString(1, login);
			statement.setString(2, mdp);
			ResultSet resultat = statement.executeQuery();
			/* Récupération des données du résultat de la requête de lecture on test si l'utilisateur existe ou non*/
			while(resultat.next())
			{
				isIdentified=true;
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return isIdentified;
	}
	
	
}
