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
//    List<Contact> contacts = loadRubricaJDBC();
//	writeRubricaJDBC(contacts);
//	EsRubrica.writeRubricaXML(contacts, "/v/jdbc.xml");
//    EsRubrica.writeRubricaCSV(contacts, "/v/jdbc2.csv",";");
    deleteContactFromRubrica(4);


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
	public static void deleteContactFromRubrica(int id) {
		Connection connection = null;
		Statement statement = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			
			statement = connection.createStatement();
		    int n = statement.executeUpdate("DELETE FROM rubrica WHERE id = " + id);
		    System.out.println("Row deleted : " + n);
		
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


