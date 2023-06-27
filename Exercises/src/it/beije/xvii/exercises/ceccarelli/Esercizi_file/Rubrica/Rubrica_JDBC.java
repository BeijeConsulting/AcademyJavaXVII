package it.beije.xvii.exercises.ceccarelli.Esercizi_file.Rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Rubrica_JDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306//alice_ceccarelli?serverTimezone=CET", "root","Ali1196");
		
		Statement statement = connection.createStatement();
		System.out.println("connection open? " + !connection.isClosed());
		//SELECT
		statement.executeQuery("SELECT * FROM rubrica");
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
