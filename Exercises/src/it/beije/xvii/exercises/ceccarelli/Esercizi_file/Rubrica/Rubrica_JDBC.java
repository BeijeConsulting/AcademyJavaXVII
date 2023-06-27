package it.beije.xvii.exercises.ceccarelli.Esercizi_file.Rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rubrica_JDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		try {
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306//alice_ceccarelli?serverTimezone=CET", "root","Ali1196");
		
		statement = connection.createStatement();
		System.out.println("connection open? " + !connection.isClosed());
		
		//SELECT
		ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
		while(rs.next()) {
			System.out.println("id: " + rs.getString("id"));
			System.out.println("nome: " + rs.getString("nome"));
			System.out.println("cognome: " + rs.getString("cognome"));
			System.out.println("telefono: " + rs.getString("telefono"));
			System.out.println("email: " + rs.getString("email"));
			System.out.println("note: " + rs.getString("note"));
			
			System.out.println("----------------");
		}
		
		rs.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
