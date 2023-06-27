package it.beije.xvii.exercises.Char;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class RubricaJDBC {

	public static void main(String[] args) {
	List<Contact> contacts = loadRubricaJDBC();
	EsRubrica.writeRubricaXML(contacts, "/v/jdbc.xml");
	for(Contact c : contacts) {
		System.out.println(c.getName());
	}

	}
	public static List<Contact> loadRubricaJDBC(){
		List<Contact> contacts = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			
			statement = connection.createStatement();
			System.out.println("connection open? " + !connection.isClosed());
			
			//SELECT
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			Contact contact = null;
			while (rs.next()) {
				contact = new Contact();
				contact.setName(rs.getString("name"));
				contact.setSurname(rs.getString("surname"));
				contact.setPhoneNumber(rs.getString("phone"));
				contact.setEmail(rs.getString("email"));
				contact.setNote(rs.getString("note"));
				contacts.add(contact);
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
		return contacts;
		
	}

}


