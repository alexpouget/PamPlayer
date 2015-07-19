package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDb {
	public static Connection connectDb(String url, String utilisateur, String motDePasse){

		Connection connexion = null;
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	    } catch ( ClassNotFoundException e ) {
           e.printStackTrace();
        } catch ( SQLException e ) {
           e.printStackTrace();
        }
        return connexion;
}
}
