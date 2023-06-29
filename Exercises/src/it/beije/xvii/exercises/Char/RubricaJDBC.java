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
		try {
			Scanner scanner = new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
		    Contact c = findByNameSurname();
			System.out.println("Contatto da eliminare : ");
			System.out.println(c);
			System.out.print("Sei sicuro di voler eliminare il contatto? (si/no) : ");
			String scelta = scanner.nextLine();
			if(scelta.equals("si")) {
				PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE name = ? AND surname = ? AND email = ? AND phone = ? AND note = ?");
				preparedStatement.setString(1, c.getName());
				preparedStatement.setString(2, c.getSurname());
				preparedStatement.setString(3, c.getEmail());
				preparedStatement.setString(4, c.getPhoneNumber());
				preparedStatement.setString(5, c.getNote());
				preparedStatement.executeUpdate();
			    System.out.println("Contatto eliminato");
			}
			else System.out.println("Contatto non eliminato");
		
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
	public static void updateContactFromRubrica() {
		Connection connection = null;
		try {
			
			Scanner scanner = new Scanner(System.in);
			Contact c = findByNameSurname();
			System.out.print("Inserisci il campo che vuoi  modificare : "); 
			String field = scanner.nextLine();
			System.out.print("Inserisci il nuovo campo : "); 
			String newField = scanner.nextLine();

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rubrica set " + field + " = ? WHERE name = ? AND surname = ? AND email = ? AND phone = ? AND note = ?");
			preparedStatement.setString(1, newField);
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getSurname());
			preparedStatement.setString(4, c.getEmail());
			preparedStatement.setString(5, c.getPhoneNumber());
			preparedStatement.setString(6, c.getNote());
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
	public static void updateContactFromRubrica(Contact c) {
		Connection connection = null;
		try {
			
			Scanner scanner = new Scanner(System.in);

			System.out.print("Inserisci il campo che vuoi  modificare : "); 
			String field = scanner.nextLine();
			System.out.print("Inserisci il nuovo campo : "); 
			String newField = scanner.nextLine();

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rubrica set " + field + " = ? WHERE name = ? AND surname = ? AND email = ? AND phone = ? AND note = ?");
			preparedStatement.setString(1, newField);
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getSurname());
			preparedStatement.setString(4, c.getEmail());
			preparedStatement.setString(5, c.getPhoneNumber());
			preparedStatement.setString(6, c.getNote());
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
	public static Contact findByNameSurname() {
		Contact contact = null;
		Connection connection = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Inserisci il nome del contatto da cercare : ");
			String name = scanner.nextLine();
			System.out.print("Inserisci il cognome del contatto da cercare : ");
			String surname = scanner.nextLine();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rubrica WHERE name = ? AND surname = ?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			ResultSet res = preparedStatement.executeQuery();
			List<Contact> contacts = new ArrayList<>();
			Contact c = null;
			while(res.next()) {
				c = new Contact();
				c.setName(res.getString("name"));
				c.setSurname(res.getString("surname"));
				c.setEmail(res.getString("email"));
				c.setPhoneNumber(res.getString("phone"));
				c.setNote(res.getString("note"));
				contacts.add(c);
			}
			if(contacts.size() > 1) System.out.println("Sono stati trovati più contatti con quel nome e cognome : ");
			if(contacts.size() == 0) System.out.println("Non è stato trovato nessun contatto con quel nome e cognome");
			for(int i = 0; i< contacts.size(); i++) {
				System.out.print(i + ". "); System.out.println(contacts.get(i));
			}
			System.out.print("Seleziona il numero del contatto che ti interessa : ");
			int numCon = scanner.nextInt();
			 contact = contacts.get(numCon);
			 
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contact;

	}

	public static void findContactFromRubrica() {
		Connection connection = null;
		try {
			Scanner scanner = new Scanner(System.in);

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");

			Contact c = findByNameSurname();

			System.out.println("Contatto trovato : ");
			System.out.println(c);
			System.out.print("Vuoi modificare il contatto? (si/no) : ");
			String scelta = scanner.nextLine();
			if(scelta.equals("si")) updateContactFromRubrica(c);
			
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


