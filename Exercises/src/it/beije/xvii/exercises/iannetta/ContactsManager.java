package it.beije.xvii.exercises.iannetta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
	
	Scanner in;
	
	public void end() {
		in.close();
	}
	
	public ContactsManager() {
		this.in = new Scanner(System.in);
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "MySQLPassword1!");
		return connection;
	}
	
	public Contact readData() {
		String answer;
		Contact con = new Contact();
		System.out.print("Enter name: ");
		answer = in.nextLine();
		con.setName(answer);
		System.out.print("Enter surname: ");
		answer = in.nextLine();
		con.setSurname(answer);
		System.out.print("Enter phone number: ");
		answer = in.nextLine();
		con.setPhoneNumber(answer);
		System.out.print("Enter email: ");
		answer = in.nextLine();
		con.setEmail(answer);
		System.out.print("Enter note: ");
		answer = in.nextLine();
		con.setNote(answer);
		return con;
	}

/*	public boolean confirm() {
		Scanner in = new Scanner(System.in);
		System.out.println("1: Confirm"+
				 		 "\n0: Discard");
			int answer = in.nextInt();
			in.close();
			if (answer == 1) return true;
		//in.close();
		return false;
	}*/
	
	public void showContacts(String orderBy) throws ClassNotFoundException, SQLException{
		Connection connection = getConnection();
		
		if (!orderBy.equals("name") && !orderBy.equals("surname")) orderBy = "id";
		List<Contact> listOfContacts = ContactsList.loadContactListFromDB("telephone_book", orderBy);
		//for (Contact con : listOfContacts) System.out.println(con.getName());
		for (Contact con : listOfContacts) System.out.println(con.toString(true) + "\n");
		connection.close();
	}
	
	public void sorting() throws SQLException, ClassNotFoundException {		
		System.out.println("1: Sort by name" + 
						 "\n2: Sort by surname" + 
						 "\n0: Sort by id (default sorting)");
		int answer = in.nextInt();		
		if (answer == 1) showContacts("name");
		else if (answer == 2) showContacts("surname");
		else showContacts("id");
	}
	
	public void searchContact() throws SQLException, ClassNotFoundException{
		Connection connection = getConnection();
		
		System.out.println("Enter data (or part of it) to look for:");
		String answer = in.nextLine();
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM telephone_book WHERE name LIKE ? OR surname LIKE ? OR phone_number LIKE ? OR email LIKE ? OR note LIKE ? ;");
		preparedStatement.setString(1, "%" + answer + "%");
		preparedStatement.setString(2, "%" + answer + "%");
		preparedStatement.setString(3, "%" + answer + "%");
		preparedStatement.setString(4, "%" + answer + "%");
		preparedStatement.setString(5, "%" + answer + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		//List<Contact> listOfContacts = new ArrayList<>();
		Contact con = null; new Contact();
		while (rs.next()) {
			con = new Contact();
			con.setID(rs.getInt("id"));
			con.setName(rs.getString("name"));
			con.setSurname(rs.getString("surname"));
			con.setPhoneNumber(rs.getString("phone_number"));
			con.setEmail(rs.getString("email"));
			con.setNote(rs.getString("note"));
			System.out.println(con.toString(true) + "\n");
		}
		rs.close();		
		preparedStatement.close();
		connection.close();
	}
	
	public void insertContact() throws ClassNotFoundException, SQLException {
		
		
		Contact con;
		con = readData();
		
//		if (confirm()) {
			List<Contact> newContact = new ArrayList<>();
			newContact.add(con);
			ContactsList.writeContactListDB(newContact);
/*		}
		Scanner in = new Scanner(System.in);
			//System.out.println(con.toString());
			System.out.println("1: Save contact"+
							 "\n2: Enter data again"+
							 "\n0: Exit");
			String answer = in.nextLine();
			in.close();
			if (answer.equals("1")) {
				List<Contact> newContact = new ArrayList<>();
				newContact.add(con);
				ContactsList.writeContactListDB(newContact);
			}
			else if (answer.equals("2")) insertContact();
*/
	}
	
	public void editContact(int id) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE telephone_book SET "
				+ "name = ?, surname = ?, "
				+ "phone_number = ?, email = ?, "
				+ "note = ? WHERE id = " + id + ";");
		
		System.out.println("Enter new data");
		Contact con = readData();
		//System.out.println(con.toString(true) + "\n");
		System.out.println("1: Save contact"+
						 "\n2: Enter data again"+
						 "\n0: Exit");
		String answer = in.nextLine();
		if (answer.equals("1")) {			
			preparedStatement.setString(1, con.getName());
			preparedStatement.setString(2, con.getSurname());
			preparedStatement.setString(3, con.getPhoneNumber());
			preparedStatement.setString(4, con.getEmail());
			preparedStatement.setString(5, con.getNote());
			preparedStatement.execute();
			}
		else if (answer.equals("2")) editContact(id);
		preparedStatement.close();
		connection.close();
	}
	
	public int searchByID() throws ClassNotFoundException, SQLException {
		int id = 0;
		System.out.println("Enter id of contact. Enter 0 to see all contacts:");
		String answer = in.next();
		in.nextLine();
		if (answer.equals("0")) {
			sorting();
			System.out.println("Enter id of contact to edit:");	
			String a = in.next();
			in.nextLine();
			id = Integer.parseInt(a);
		} else id = Integer.parseInt(answer);
		
		Connection connection;
			connection = getConnection();
			Statement statement = connection.createStatement();
			
			
			ResultSet rs = statement.executeQuery("SELECT * FROM telephone_book WHERE id = " + id +";");
			Contact con = new Contact();
			while (rs.next()) {
				con.setID(id);
				con.setID(rs.getInt("id"));
				con.setName(rs.getString("name"));
				con.setSurname(rs.getString("surname"));
				con.setPhoneNumber(rs.getString("phone_number"));
				con.setEmail(rs.getString("email"));
				con.setNote(rs.getString("note"));
				System.out.println(con.toString(true) + "\n");
			}
			rs.close();		
			statement.close();
			connection.close();
			
//			if (confirm()) return id;
//			else id = 0;
		
		return id;
	}

	public void deleteContact(int id) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM telephone_book WHERE id = " + id +";");
		statement.close();
		connection.close();
		System.out.println("ID: " + id + " deleted");
	}

	public List<Contact[]> findDuplicates() throws ClassNotFoundException, SQLException{
		Connection connection = getConnection();
		ResultSet rs = null;
		Statement statement;
		List<Contact> listOfContacts = ContactsList.loadContactListFromDB("telephone_book", "name");
		List<Contact> listOfDuplicates = new ArrayList<>();
		Contact con1 = new Contact();
		Contact con2 = new Contact();
		String phoneNumber;
		while (!listOfContacts.isEmpty()) {
			con1 = listOfContacts.get(0);
			phoneNumber = con1.getPhoneNumber();
			rs = statement.executeQuery("SELECT * FROM telephone_book WHERE phone_number = '" + phoneNumber + "';");
			while (rs.next()) {
				con2.setID(rs.getInt("id"));
				con2.setName(rs.getString("name"));
				con2.setSurname(rs.getString("surname"));
				con2.setPhoneNumber(rs.getString("phone_number"));
				con2.setEmail(rs.getString("email"));
				con2.setNote(rs.getString("note"));
				listOfDuplicates.add(con2);
				listOfContacts.remove(con2);
			}
			listOfContacts.remove(0);
			
		}
		
		
//		int count = 0;
//		System.out.println("Duplicates");
//		for (int i = listOfDuplicates.size() - 1; i > 0; i--) {
//			con1 = listOfDuplicates.get(i);
//			con2 = listOfDuplicates.get(i-1);
//			
//			if (isDuplicate) System.out.println(con1.toString(true) + "\n" + con1.toString(true) + "\n");
//			else listOfDuplicates
//		}
//		System.out.println("//");
//		connection.close();
//		return listOfDuplicates;
	}
	
	public boolean areNumberDuplicates(Contact con1, Contact con2) {
		boolean areDuplicates = (con1.getName().equalsIgnoreCase(con2.getName()) && con1.getSurname().equalsIgnoreCase(con2.getSurname())
				 || con1.getPhoneNumber().equals(con2.getPhoneNumber()));
		return areDuplicates;
	}
	
	public void mergeDuplicates () {}
}
