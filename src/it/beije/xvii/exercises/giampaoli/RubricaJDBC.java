package it.beije.xvii.exercises.giampaoli;

import java.sql.*;

public class RubricaJDBC {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/esercizi?serverTimezone=CET", "root", "PolleriaOsvaldo5267"); //Connessione al server locale
		
			statement = connection.createStatement();
			
			//Select
			ResultSet rs = statement.executeQuery(
									"SELECT * FROM rubrica");
			
			while (rs.next()) {
				System.out.println("Id : " + rs.getInt("id"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("note : " + rs.getString("note"));
			}
		
			rs.close();
			
		}	catch (ClassNotFoundException e) {
				e.printStackTrace();
		}	catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		

	}

}
