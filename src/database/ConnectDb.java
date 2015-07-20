package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Main;

public class ConnectDb {
	public static Connection connectDb(String url, String utilisateur, String motDePasse){

		Connection connexion = null;
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	    } catch ( ClassNotFoundException e ) {
           e.printStackTrace();
           Main.logger.error(e.getMessage());
        } catch ( SQLException e ) {
           e.printStackTrace();
           Main.logger.error(e.getMessage());
        }
        return connexion;
}
}
