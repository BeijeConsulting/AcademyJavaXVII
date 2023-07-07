package it.beije.suormary.rubrica.trapani.JDBC;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.suormary.rubrica.trapani.Contact;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//		COMPLETA E FUNZIONANTE


public class AzioniGestoreJDBC extends MenuGestioneRubricaJDBC{
	
	public static Scanner in = new Scanner(System.in);
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "Rick&Morty63!!");
		
		return connection;
	}

	
	//1
	public static List<Contact> listContact(){		
		
		Statement statement= null;

		List<Contact> contacts = new ArrayList<>();
		Contact c = null;
			
		try {
				statement = getConnection().createStatement();
				
				System.out.println("Lista contatti ordinata? (y/n): ");
				String answ = in.next().toLowerCase();
			 
				//LISTA NON ORDINATA
				
				if(answ.equals("n")) {
					ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
					while (rs.next()) {
						c = new Contact();
						c.setId(rs.getInt("id"));
						c.setName(rs.getString("nome"));
						c.setSurname(rs.getString("cognome"));
						c.setPhoneNumber(rs.getString("telefono"));
						c.setEmail(rs.getString("email"));
						c.setNote(rs.getString("note"));
						System.out.println(c.toString());
						contacts.add(c);
					}
				} else {		//LISTA CONTATTI ORDINATA
					System.out.println("Ordinamento per nome o cognome? (n/c): ");
					answ=in.next().toLowerCase();
					
					switch(answ) {
					
					case "n":   ResultSet rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY nome");
								while (rs.next()) {
									c = new Contact();
									c.setId(rs.getInt("id"));
									c.setName(rs.getString("nome"));
									c.setSurname(rs.getString("cognome"));
									c.setPhoneNumber(rs.getString("telefono"));
									c.setEmail(rs.getString("email"));
									c.setNote(rs.getString("note"));
									System.out.println(c.toString());
									contacts.add(c);
								}
								break;
					
					case "c": 	ResultSet rs1 = statement.executeQuery("SELECT * FROM rubrica ORDER BY cognome");
								while (rs1.next()) {
									c = new Contact();
									c.setId(rs1.getInt("id"));
									c.setName(rs1.getString("nome"));
									c.setSurname(rs1.getString("cognome"));
									c.setPhoneNumber(rs1.getString("telefono"));
									c.setEmail(rs1.getString("email"));
									c.setNote(rs1.getString("note"));
									System.out.println(c.toString());
									contacts.add(c);
								}
								break;
					default:	System.out.println("Comando non riconosciuto"); 
								break;
					}
				}
				System.out.println();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return contacts;
	}
	
	//2
	public static List<Contact> findContact(){
		
		PreparedStatement preparedStatement= null;

		List<Contact> contacts = new ArrayList<Contact>();
		Contact c = null;
			
		try {
				
				preparedStatement = getConnection().prepareStatement
						("SELECT * FROM rubrica WHERE nome LIKE ? OR cognome LIKE ? OR telefono LIKE ? OR email LIKE ? OR note LIKE ?");
				
//				System.out.println("Seleziona campo da cercare: ");
//				preparedStatement.setString(1, in.nextLine());
				System.out.println("Valore da cercare: ");	
				String search ="%" + in.next() +"%";
				preparedStatement.setString(1, search);		
				preparedStatement.setString(2, search);	
				preparedStatement.setString(3, search);	
				preparedStatement.setString(4, search);	
				preparedStatement.setString(5, search);	
					
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					c = new Contact();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("nome"));
					c.setSurname(rs.getString("cognome"));
					c.setPhoneNumber(rs.getString("telefono"));
					c.setEmail(rs.getString("email"));
					c.setNote(rs.getString("note"));
					System.out.println(c.toString());
					contacts.add(c);
					} 
				System.out.println("Contatto trovato in elenco? (y/n)");
				String anString = in.next();
				if(anString.equals("n")) {
					findContact();
				}
				
				
			getConnection().close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return contacts;
	}

	//3
	public static void insertContact() {

		PreparedStatement preparedStatement = null;
			
		try {
			
			preparedStatement = getConnection().prepareStatement
					("INSERT INTO rubrica (`nome`, `cognome`, `telefono`, `email`, `note`) VALUES (?, ?, ?, ?, ?)");
			
			System.out.println("Nome:");
			preparedStatement.setString(1, in.next().trim());
			System.out.println("Cognome:");
			preparedStatement.setString(2, in.next().trim());
			System.out.println("Telefono:");
			preparedStatement.setString(3, in.next().trim());
			System.out.println("Email:");
			preparedStatement.setString(4, in.next().trim());
			System.out.println("Note:");
			preparedStatement.setString(5, in.next().trim());
	
			preparedStatement.execute();
			
			getConnection().close();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//4
	public static void updateContact() {

		PreparedStatement preparedStatement = null;
		List<Contact> found = new ArrayList<>();
		Contact contact = null;
					
		try {
			
			preparedStatement = getConnection().prepareStatement
					("UPDATE rubrica SET nome = ?, cognome = ?, telefono = ?, email = ?, note = ? WHERE id = ?");
			
			found = findContact();		
			
			System.out.println("Indica id contatto da aggiornare: ");
				int idcanc = in.nextInt();

			for (Contact c : found ) {		
				if(c.getId()==idcanc) {
					contact = c;
					
				}
			}
			
			System.out.println(contact.toString());
			System.out.println();
			
			//nome
			System.out.println("Nuovo nome: (se invariato null)");
			String name = in.next().trim();
			if(name.equals("null")){
				name = contact.getName();			
			} 
			
			
			//cognome
			System.out.println("Nuovo cognome: (se invariato null)");
			String surname = in.next().trim();
			if(surname.equals("null")){
				surname = contact.getSurname();			
			} 
			
			//telefono
			System.out.println("Nuovo telefono: (se invariato null)");
			String phoneNumber = in.next().trim();
			if(phoneNumber.equals("null")){
				phoneNumber = contact.getPhoneNumber();
			} 
			
			//email
			System.out.println("Nuovo email: (se invariato null)");
			String email = in.next().trim();
			if(email.equals("null")){
				email = contact.getEmail();			
			} 
			
			//note
			System.out.println("Nuovo note: (se invariato null)");
			String note = in.next();
			if(note.equals("null")){
				note = contact.getNote();			
			} 
			
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, note);
			preparedStatement.setInt(6, idcanc);

			preparedStatement.execute();	
			
			getConnection().close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//5
	public static void deleteContact() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		try {
			connection = getConnection();
			List<Contact> found = findContact();
			preparedStatement = connection.prepareStatement
					("DELETE FROM rubrica WHERE id = ?");
			
//			for (Contact c : found) {
//				System.out.println(c.toString());
//			}
			
			System.out.println("Indica id contatto da eliminare ");
			preparedStatement.setInt(1, in.nextInt());

			preparedStatement.execute();	
			
			
			System.out.println("Contatto eliminato correttamente");
			
			getConnection().close();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//6
	public static List<Contact> findDuplicateContact() {
		
		Statement statement= null;
		PreparedStatement preparedStatement= null;

		List<Contact> contacts = new ArrayList<Contact>();
		List<Contact> duplicates = new ArrayList<Contact>();
		Contact c = null;
			
		try {
			statement = getConnection().createStatement();

			ResultSet rs1 = statement.executeQuery("SELECT nome,cognome,telefono,email, COUNT(*) FROM rubrica "
					+ "GROUP BY nome,cognome,telefono,email HAVING COUNT(*) > 1;");
			while (rs1.next()) {
				c = new Contact();	
				c.setName(rs1.getString("nome"));
				c.setSurname(rs1.getString("cognome"));
				c.setPhoneNumber(rs1.getString("telefono"));
				c.setEmail(rs1.getString("email"));
				contacts.add(c);
			}
			
//			String nome = (rs1.getString("nome"));
//			String cognome = (rs1.getString("cognome"));
//			String telefono = (rs1.getString("telefono"));
//			String emailString = (rs1.getString("email"));
			
			preparedStatement = getConnection().prepareStatement
					("SELECT * FROM rubrica WHERE nome = ? AND cognome = ? AND telefono = ? AND email = ?");
			for (Contact cn : contacts) {
				preparedStatement.setString(1, cn.getName());
				preparedStatement.setString(2, cn.getSurname());
				preparedStatement.setString(3, cn.getPhoneNumber());
				preparedStatement.setString(4, cn.getEmail());
				ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				c = new Contact();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				c.setPhoneNumber(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				c.setNote(rs.getString("note"));

				duplicates.add(c);
			}
			}
			
				
			if(contacts.size()==0) {
				System.out.println("Nessun contatto duplicato");
				return null;
			}
				
			getConnection().close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return duplicates;
	}

	//7
	public static void mergeDuplicateContac() {
		PreparedStatement preparedStatement= null;

		List<Contact> contacts = new ArrayList<Contact>();
//		Contact c = null;
			
		try {
				contacts = findDuplicateContact();
				preparedStatement = getConnection().prepareStatement
						("DELETE FROM rubrica WHERE id = ?");
				
				if(contacts.size()==0) {
					System.out.println("Nessun contatto duplicato da eliminare");
					return;
				}
				for(int i=0; i<contacts.size(); i++) {
					for(int j=i+1; j<contacts.size(); j++) {
						if(contacts.get(i).toStringDup().equals(contacts.get(j).toStringDup())) {
							int id = contacts.get(j).getId();
							preparedStatement.setInt(1,id);
							preparedStatement.execute();
						}
					}
				}
				getConnection().close();

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
}
