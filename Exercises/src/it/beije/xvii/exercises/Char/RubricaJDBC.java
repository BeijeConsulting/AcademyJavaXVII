package it.beije.xvii.exercises.Char;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class RubricaJDBC {

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
			System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());
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
			StringBuilder str = null;
			PreparedStatement preparedStatement= null;
			for(Contact c : contatti) {
				 preparedStatement= connection.prepareStatement("INSERT INTO rubrica(`name`,`surname`,`email`,`phone`,`note`) VALUES (?,?,?,?,?)");
				 preparedStatement.setString(1,c.getName());
				 preparedStatement.setString(2,c.getSurname());
				 preparedStatement.setString(3,c.getEmail());
				 preparedStatement.setString(4,c.getPhoneNumber());
				 preparedStatement.setString(5,c.getNote());
				preparedStatement.executeUpdate();
			}
			System.out.println("Operazione eseguita");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());
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
            scanner.nextLine();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM rubrica WHERE id = " + id);
			res.next();
		    Contact c = new Contact();
		    c.setName(res.getString("name"));
		    c.setSurname(res.getString("surname"));
		    c.setEmail(res.getString("email"));
		    c.setPhoneNumber(res.getString("phone"));
		    c.setNote(res.getString("note"));
			System.out.println("Contatto da eliminare : ");
			System.out.println(c);
			System.out.print("Sei sicuro di voler eliminare il contatto? (si/no) : ");
			String scelta = scanner.nextLine();
			if(scelta.equals("si")) {
			    statement.executeUpdate("DELETE FROM rubrica WHERE id = " + id);
			    System.out.println("Contatto eliminato");
			}
			else System.out.println("Contatto non eliminato");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());
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
		try {
			
			Scanner scanner = new Scanner(System.in);
			System.out.print("Inserisci l'id del contatto da modificare : "); 
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Inserisci il campo che vuoi  modificare : "); 
			String field = scanner.nextLine();
			System.out.print("Inserisci il nuovo campo : "); 
			String newField = scanner.nextLine();

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rubrica set " + field + " = ? WHERE id = ?");
			preparedStatement.setString(1, newField);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("Modifica eseguita");
			System.out.print("Vuoi modificare un altro contatto? (si/no) : ");
			String scelta = scanner.nextLine();
			if(scelta.equals("si")) updateContactFromRubrica();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void createContactFromRubrica() {
		Connection connection = null;
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
			Contact c = new Contact();
			c.setName(name); c.setSurname(surname); c.setEmail(email); c.setPhoneNumber(phone); c.setNote(note);

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rubrica (`name`,`surname`,`email`,`phone`,`note`) VALUES (?,?,?,?,?)");
			 preparedStatement.setString(1,c.getName());
			 preparedStatement.setString(2,c.getSurname());
			 preparedStatement.setString(3,c.getEmail());
			 preparedStatement.setString(4,c.getPhoneNumber());
			 preparedStatement.setString(5,c.getNote());
			 preparedStatement.executeUpdate();
			System.out.print("Informazioni sul nuovo contatto : ");
			System.out.println(c);
			System.out.print("Vuoi modificare qualche informazione? (si/no) : ");
			String scelta = scanner.nextLine();
			if(scelta.equals("si"))  updateContactFromRubrica();
			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void findContactFromRubrica() {
		Connection connection = null;
		Statement statement = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Inserisci l`id del contatto da cercare : ");
			int id = scanner.nextInt();
			scanner.nextLine();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM rubrica WHERE ID = " + id);
			res.next();
			Contact c = new Contact();
		    c.setName(res.getString("name"));
		    c.setSurname(res.getString("surname"));
		    c.setEmail(res.getString("email"));
		    c.setPhoneNumber(res.getString("phone"));
		    c.setNote(res.getString("note"));
			System.out.println("Contatto trovato : ");
			System.out.println(c);
			System.out.print("Vuoi modificare il contatto? (si/no) : ");
			String scelta = scanner.nextLine();
			if(scelta.equals("si")) updateContactFromRubrica();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<Contact> findDuplicatedContacts() {
		List<Contact> duplicatedContacts = new ArrayList<>();
		List<Contact> contacts = loadRubricaJDBC();

		 for (int i = 0; i < contacts.size(); i++) {
		        for (int j = i + 1; j < contacts.size(); j++) {
		            if (contacts.get(i).equals(contacts.get(j))) {
		                duplicatedContacts.add(contacts.get(i));
		                break; 
		            }
		        }
		    }
		System.out.println("Contatti duplicati trovati : ");
		for(Contact c : duplicatedContacts) {
			System.out.println(c);
		}
		return duplicatedContacts;
		
	}
	public static void mergeDuplicatedContacts() {
		List<Contact> duplicatedContacts = findDuplicatedContacts();
	    
		Connection connection = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");

			 PreparedStatement  preparedStatement = null;
		    for (Contact c : duplicatedContacts) {
		    	preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE name = ? AND surname = ? AND email = ? AND phone = ? AND note = ?");
		   	    preparedStatement.setString(1,c.getName());
			    preparedStatement.setString(2,c.getSurname());
			    preparedStatement.setString(3,c.getEmail());
			    preparedStatement.setString(4,c.getPhoneNumber());
			    preparedStatement.setString(5,c.getNote());
			    preparedStatement.executeUpdate();
		    }
		    for (Contact c : duplicatedContacts) {
		    	 preparedStatement = connection.prepareStatement("INSERT INTO rubrica (`name`,`surname`,`email`,`phone`,`note`) VALUES (?,?,?,?,?)");
				 preparedStatement.setString(1,c.getName());
				 preparedStatement.setString(2,c.getSurname());
				 preparedStatement.setString(3,c.getEmail());
				 preparedStatement.setString(4,c.getPhoneNumber());
				 preparedStatement.setString(5,c.getNote());
				 preparedStatement.executeUpdate();
		    }
		    System.out.println("I contatti duplicati sono stati uniti");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

}


