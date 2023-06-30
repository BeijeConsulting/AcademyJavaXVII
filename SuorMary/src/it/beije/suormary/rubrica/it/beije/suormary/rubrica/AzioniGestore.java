package it.beije.suormary.rubrica;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//		vedi lista contatti (con possibilità di ordinare per nome e cognome a scelta)  --
//		cerca contatto --
//		inserisci nuovo contatto  --
//		modifica contatto
//		cancella contatto
//		trova contatti duplicati
//		unisci contatti duplicati


public class AzioniGestore {
	
	public static Scanner in = new Scanner(System.in);
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "Rick&Morty63!!");
	}

	public static List<Contact> listContact(){		
		
		Connection connection = null;
		Statement statement= null;

		List<Contact> contacts = new ArrayList<>();
		Contact c = null;
			
		try {
				connection = getConnection();
				statement = connection.createStatement();
				
				System.out.println("Lista contatti ordinata? (y/n): ");
				String answ = in.nextLine().toLowerCase();
			 
				//LISTA NON ORDINATA
				
				if(answ.equals("n")) {
					ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
					while (rs.next()) {
						c = new Contact();
						c.setName(rs.getString("nome"));
						c.setSurname(rs.getString("cognome"));
						c.setPhoneNumber(rs.getString("telefono"));
						c.setEmail(rs.getString("email"));
						c.setNote(rs.getString("note"));
						contacts.add(c);
					}
				} else {
					System.out.println("Ordinamento per nome o cognome? (n/c): ");
					answ=in.nextLine().toLowerCase();
					
					switch(answ) {
					
					case "n":   ResultSet rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY nome");
								while (rs.next()) {
									c = new Contact();
									c.setName(rs.getString("nome"));
									c.setSurname(rs.getString("cognome"));
									c.setPhoneNumber(rs.getString("telefono"));
									c.setEmail(rs.getString("email"));
									c.setNote(rs.getString("note"));
									contacts.add(c);
								}
								break;
					
					case "c": 	ResultSet rs1 = statement.executeQuery("SELECT * FROM rubrica ORDER BY cognome");
								while (rs1.next()) {
									c = new Contact();
									c.setName(rs1.getString("nome"));
									c.setSurname(rs1.getString("cognome"));
									c.setPhoneNumber(rs1.getString("telefono"));
									c.setEmail(rs1.getString("email"));
									c.setNote(rs1.getString("note"));
									contacts.add(c);
								}
								break;
					default: System.out.println("Comando non riconosciuto"); 					
					}
				}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return contacts;
	}
	
	public static List<Contact> findContact() throws ClassNotFoundException, SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement= null;

		List<Contact> contacts = new ArrayList<>();
		Contact c = null;
			
		try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement
						("SELECT * FROM rubrica WHERE ? = ?");
				
				System.out.println("Seleziona campo da cercare: ");
				String searchKey = in.nextLine().toLowerCase().trim();
				preparedStatement.setString(1, in.nextLine());
				System.out.println("Valore da cercare: ");
				String search = in.nextLine().trim();
				preparedStatement.setString(2, in.nextLine());
				
								
						
					
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					c = new Contact();
					c.setName(rs.getString("nome"));
					c.setSurname(rs.getString("cognome"));
					c.setPhoneNumber(rs.getString("telefono"));
					c.setEmail(rs.getString("email"));
					c.setNote(rs.getString("note"));
					contacts.add(c);
					} 					
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return contacts;
	}

	public static void insertContact() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement
					("INSERT INTO rubrica (`nome`, `cognome`, `telefono`, `email`, `note`) VALUES (?, ?, ?, ?, ?)");
			
			System.out.println("Nome:");
			preparedStatement.setString(1, in.nextLine());
			System.out.println("Cognome:");
			preparedStatement.setString(2, in.nextLine());
			System.out.println("Telefono:");
			preparedStatement.setString(3, in.nextLine());
			System.out.println("Email:");
			preparedStatement.setString(4, in.nextLine());
			System.out.println("Note:");
			preparedStatement.setString(5, in.nextLine());
	
			preparedStatement.execute();			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updetContact() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
			
		try {
			connection = getConnection();
			List<Contact> found = findContact();
			
			for (Contact c : found) {
				System.out.println(c.toString());
			}
			
			preparedStatement = connection.prepareStatement
					("UPDATE rubrica SET ? = ? WHERE id = ?");
			
			
	
			preparedStatement.execute();			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
