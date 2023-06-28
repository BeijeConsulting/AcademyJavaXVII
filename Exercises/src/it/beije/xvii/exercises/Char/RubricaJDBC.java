package it.beije.xvii.exercises.Char;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class RubricaJDBC {

	public static void main(String[] args) {
//    List<Contact> contacts = loadRubricaJDBC();
//	writeRubricaJDBC(contacts);
//	EsRubrica.writeRubricaXML(contacts, "/v/jdbc.xml");
//    EsRubrica.writeRubricaCSV(contacts, "/v/jdbc2.csv",";");
//    deleteContactFromRubrica(4);

	}
	public static List<Contact> loadRubricaJDBC(){
		List<Contact> contacts = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			
			statement = connection.createStatement();
			Scanner scanner = new Scanner(System.in);
			System.out.print("Vuoi ordinare i contatti per nome e cognome? (si/no) : ");
			String sceltaOrdine = scanner.nextLine();
			scanner.close();
			ResultSet rs = null;
			switch(sceltaOrdine) {
				case "si" :  rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY name,surname"); break;
				case "no" :  rs = statement.executeQuery("SELECT * FROM rubrica"); break;
				default : System.out.println("Errore"); break;
			}
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
	public static void deleteContactFromRubrica() {
		Connection connection = null;
		Statement statement = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Inserisci l'id del contatto da eliminare : ");
			int id = scanner.nextInt();
			scanner.close();

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
	public static void updateContactFromRubrica() {
		Connection connection = null;
		Statement statement = null;
		try {
			
			Scanner scanner = new Scanner(System.in);
			System.out.print("Inserisci l'id del contatto da modificare : "); 
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Inserisci il campo che vuoi  modificare : "); 
			String field = scanner.nextLine();
			System.out.print("Inserisci il nuovo campo : "); 
			String newField = scanner.nextLine();
			scanner.close();

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			
			statement = connection.createStatement();
			statement.executeUpdate("UPDATE rubrica set " + field + " = " + newField + " WHERE id = " + id);
		
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
	public static void createContactFromRubrica() {
		Connection connection = null;
		Statement statement = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Nome : ");
			String name = scanner.nextLine(); 
			System.out.print("Cognome : ");
			String surname = scanner.nextLine(); 
			System.out.print("Email : ");
			String email = scanner.nextLine(); 
			System.out.print("Telefono : ");
			String phone = scanner.nextLine(); 
			System.out.print("Note : ");
			String note = scanner.nextLine(); 
			scanner.close();
			Contact c = new Contact();
			c.setName(name); c.setSurname(surname); c.setEmail(email); c.setPhoneNumber(phone); c.setNote(note);

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			
			statement = connection.createStatement();
			StringBuilder str = new StringBuilder("INSERT INTO rubrica (`name`,`surname`,`email`,`phone`,`note`) VALUES ('")
					.append(c.getName()).append("','")
					.append(c.getSurname()).append("','")
					.append(c.getEmail()).append("','")
					.append(c.getPhoneNumber()).append("','")
					.append(c.getNote()).append("')");
			statement.executeUpdate(str.toString());
		
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


