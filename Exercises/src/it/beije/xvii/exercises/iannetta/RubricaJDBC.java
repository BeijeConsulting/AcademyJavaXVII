package it.beije.xvii.exercises.iannetta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RubricaJDBC {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "MySQLPassword1!");
			
			statement = connection.createStatement();
			System.out.println("connection open? " + !connection.isClosed());
			
			//execute Update è meglio per insert, delete
			//INSERT
			//statement.executeUpdate("INSERT INTO rubrica VALUES ( null, 'TEST', 'DEFAULT', '3331233488', 'test.test@beije.it', 'yay contatto');");
			
			String nome = "Filippo";
			String cognome = "Marrone";
			String telefono = "4586912358";
			String email = "filippo.marrone@beije.it";
			StringBuilder query = new StringBuilder("INSERT INTO suor_mary.rubrica (`nome`, `cognome`, `telefono`, `email`) VALUES ('")
					.append(nome).append("', '").append(cognome).append("', '").append(telefono).append("', '").append(email).append("');");
			
			statement.executeUpdate(query.toString());
			
			
			//UPDATE
			//int u = statement.executeUpdate("UPDATE suor_mary.rubrica set telefono = '1234567' WHERE id < 4;");
			//System.out.println(u + " record modificati");
			//u = statement.executeUpdate("DELETE FROM suor_mary.rubrica WHERE id = 12;");
			//System.out.println(u + " record modificati");
			
			
			
			//SELECT
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
//				System.out.println("id : " + rs.getInt(1));
//				System.out.println("nome : " + rs.getString(2));
//				System.out.println("cognome : " + rs.getString(3));
//				System.out.println("telefono : " + rs.getString(4));
//				System.out.println("email : " + rs.getString(5));
//				System.out.println("note : " + rs.getString(6));
				
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));

				System.out.println("---------------");
			}
			rs.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
