package it.beije.suormary.rubrica.trapani;


import java.util.ArrayList;
import java.util.Iterator;
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
//		modifica contatto --
//		cancella contatto --
//		trova contatti duplicati
//		unisci contatti duplicati


public class AzioniGestore extends MenuGestioneRubrica{
	
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
				String answ = in.nextLine().toLowerCase();
			 
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
						contacts.add(c);
					}
				} else {		//LISTA CONTATTI ORDINATA
					System.out.println("Ordinamento per nome o cognome? (n/c): ");
					answ=in.nextLine().toLowerCase();
					
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
									contacts.add(c);
								}
								break;
					default:	System.out.println("Comando non riconosciuto"); 
								break;
					}
				}
		
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
						("SELECT * FROM rubrica WHERE nome = ? OR cognome = ? OR telefono = ? OR email = ? OR note = ?");
				
//				System.out.println("Seleziona campo da cercare: ");
//				preparedStatement.setString(1, in.nextLine());
				System.out.println("Valore da cercare: ");	
				String search = in.nextLine();
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
					contacts.add(c);
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
			
			getConnection().close();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//4
	public static void updetContact() {

		PreparedStatement preparedStatement = null;
			
		try {
			
			List<Contact> found = findContact();
			preparedStatement = getConnection().prepareStatement
					("UPDATE rubrica SET ? = ? WHERE id = ?");
			
			for (Contact c : found) {
				System.out.println(c.toString());
			}
			
			System.out.println("Indica id contatto da aggiornare: ");
			preparedStatement.setString(3, in.nextLine().toLowerCase().trim());
			System.out.println("Indica il campo da aggiornare: ");
			preparedStatement.setString(1, in.nextLine().toLowerCase().trim());
			System.out.println("Inserisci nuovo valore: ");
			preparedStatement.setString(2, in.nextLine());

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
					("DELETE rubrica WHERE id = ?");
			
			for (Contact c : found) {
				System.out.println(c.toString());
			}
			
			System.out.println("Indica id contatto da eliminare ");
			preparedStatement.setString(3, in.nextLine().toLowerCase().trim());

			preparedStatement.execute();	
			
			if(preparedStatement.execute()) {
				System.out.println("Contatto eliminato correttamente");
			}
			getConnection().close();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//6
	public static void findDuplicateContact() {
		
		Connection connection = null;
		PreparedStatement preparedStatement= null;

		List<Contact> contacts = new ArrayList<>();
		Contact cont = null;
		List<Contact> duplicate = new ArrayList<>(); 
			
		try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement
						("SELECT * FROM rubrica WHERE nome = ? AND cognome = ? AND telefono = ? AND email = ?");
				
				contacts = listContact();
				int i = 0;
				for(Contact c : contacts) {
					preparedStatement.setString(1, c.getName());
					preparedStatement.setString(2, c.getSurname());
					preparedStatement.setString(3, c.getPhoneNumber());
					preparedStatement.setString(4, c.getEmail());
				
				}
						
					
					
				
				
				
				
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
	}

	//7
	public static void mergeDuplicateContac() {
		
		
	}
}
