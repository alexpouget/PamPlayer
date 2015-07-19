package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserUtils {

	public static int recupererIdUser(String pseudo) throws SQLException
	{
		Integer userId=0;
		Connection connexion=ConnectDb.connectDb("jdbc:mysql://127.0.0.1/pam","root","root");
		PreparedStatement statement = connexion.prepareStatement("SELECT Id FROM user WHERE Pseudo=?");

		statement.setString(1, pseudo);

		ResultSet resultat = statement.executeQuery();
		/* Récupération des données du résultat de la requête de lecture on test si l'utilisateur existe ou non*/
		while(resultat.next())
		{
			userId=resultat.getInt("Id");
		}
		if(userId==0)
			System.out.println("L'utilisateur avec le pseudo "+pseudo+" n'existe pas");
		return userId;
	}

}
