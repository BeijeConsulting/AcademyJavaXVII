package it.beije.xvii.exercises.mancuso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RubricaJDBC {

	public static void main(String[] args) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost::3306/suor_mary?serverTimezon=CET", "root", "myDatabase1");
			
			Statement statement = connection.createStatement();
			
			//SELECT
			statement.executeQuery("SELECT * FROM rubrica");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		

	}

}
