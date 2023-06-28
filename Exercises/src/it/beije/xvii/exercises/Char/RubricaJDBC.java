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
	Contact c = new Contact();
	c.setName("Mario");
	c.setSurname("Rossi");
	c.setEmail("mario@gmail.com");
	c.setPhoneNumber("3542524");
	c.setNote("first contact");
	
	Contact c2 = new Contact();
	c2.setName("Luigi");
	c2.setSurname("Verdi");
	c2.setEmail("luigi@gmail.com");
	c2.setPhoneNumber("3552323");
	c2.setNote("second contact");
	
	Contact c3 = new Contact();
	c3.setName("Antonio");
	c3.setSurname("Gialli");
	c3.setEmail("antonio@gmail.com");
	c3.setPhoneNumber("3212945");
	c3.setNote("third contact");
	List<Contact> contacts = new ArrayList<>();
	contacts.add(c);
	contacts.add(c3);
	contacts.add(c2);
	writeRubricaJDBC(contacts);
//	EsRubrica.writeRubricaXML(contacts, "/v/jdbc.xml");


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
	
	public static void writeRubricaJDBC(List<Contact> contatti){
		Connection connection = null;
		Statement statement = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			
			statement = connection.createStatement();
			System.out.println("connection open? " + !connection.isClosed());
			StringBuilder str = null;
			for(Contact c : contatti) {
				str = new StringBuilder("INSERT INTO rubrica(`name`,`surname`,`email`,`phone`,`note`) VALUES ('")
						.append(c.getName()).append("','")
						.append(c.getSurname()).append("','")
						.append(c.getEmail()).append("','")
						.append(c.getPhoneNumber()).append("','")
						.append(c.getNote()).append("')");
				statement.executeUpdate(str.toString());
			}
		
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


