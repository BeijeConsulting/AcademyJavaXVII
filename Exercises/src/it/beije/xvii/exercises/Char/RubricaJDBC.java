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

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			statement = connection.createStatement();
			statement.executeUpdate("UPDATE rubrica set " + field + " = " + "'" + newField  + "'" + " WHERE id = " + id);
			System.out.println("Modifica eseguita");
			System.out.print("Vuoi modificare un altro contatto? (si/no) : ");
			String scelta = scanner.nextLine();
			if(scelta.equals("si")) updateContactFromRubrica();
		
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
			System.out.print("Informazioni sul nuovo contatto : ");
			System.out.println(c);
			System.out.println("Vuoi modificare qualche informazione? (si/no) : ");
			String scelta = scanner.nextLine();
			if(scelta.equals("si"))  updateContactFromRubrica();
			
		
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
	
	public static List<Contact> findDuplicatedContacts() {
		List<Contact> duplicatedContacts = new ArrayList<>();
		List<Contact> contacts = loadRubricaJDBC();
		for(int i = 0; i < contacts.size(); i++) {
			for(int j = 0; j < contacts.size(); j++ ) {
				if(contacts.get(i) == (contacts.get(j))) continue;
				if(contacts.get(i).equals(contacts.get(j)) && (!duplicatedContacts.contains(contacts.get(i)))) {
					duplicatedContacts.add(contacts.get(i));
				}
			}
		}

		for(Contact c : duplicatedContacts) {
			System.out.println("Contatti duplicati trovati : ");
			System.out.println(c);
		}
		return duplicatedContacts;
		
	}
	public static void mergeDuplicatedContacts() {
		List<Contact> duplicatedContacts = findDuplicatedContacts();
	    
	    List<Contact> contacts = new ArrayList<>();
	    for (int i = 0; i < duplicatedContacts.size(); i++) {
	        boolean isDuplicate = false;
	        
	        for (int j = i + 1; j < duplicatedContacts.size(); j++) {
	            
	            if (duplicatedContacts.get(i).equals(duplicatedContacts.get(j))) {
	                isDuplicate = true;
	                break;
	            }
	        }
	        
	        if (!isDuplicate) {
	            contacts.add(duplicatedContacts.get(i));
	        }
	    }
		Connection connection = null;
		Statement statement = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "12345");
			
			statement = connection.createStatement();
			StringBuilder str = null;
		    for (Contact c : contacts) {
		    	str = new StringBuilder("DELETE FROM rubrica WHERE name = '")
						.append(c.getName()).append("' AND surname = '")
						.append(c.getSurname()).append("' AND email = '")
						.append(c.getEmail()).append("' AND phone = '")
						.append(c.getPhoneNumber()).append("' AND note = '")
						.append(c.getNote()).append("'");
		       statement.executeUpdate(str.toString());
		    }
		    for (Contact c : contacts) {
		    	 str = new StringBuilder("INSERT INTO rubrica (`name`,`surname`,`email`,`phone`,`note`) VALUES ('")
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


