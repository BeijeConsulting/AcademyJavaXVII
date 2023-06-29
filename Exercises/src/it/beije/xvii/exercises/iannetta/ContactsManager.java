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
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "MySQLPassword1!");
		return connection;
	}
	
	public Contact readData() {
		Scanner in = new Scanner(System.in);
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
		in.close();
		return con;
	}

	public boolean confirm() {
		Scanner in = new Scanner(System.in);
		System.out.println("1: Confirm"+
				 		 "\n0: Discard");
			int answer = in.nextInt();
			in.close();
			if (answer == 1) return true;
		//in.close();
		return false;
	}
	
	public void showContacts(String orderBy) throws ClassNotFoundException, SQLException{
		Connection connection = getConnection();
		if (!orderBy.equals("name") && !orderBy.equals("surname")) orderBy = "id";
		List<Contact> listOfContacts = ContactsList.loadContactListFromDB("telephone_book", orderBy);
		//for (Contact con : listOfContacts) System.out.println(con.getName());
		for (Contact con : listOfContacts) System.out.println(con.toString(true) + "\n");
		connection.close();
	}
	
	public void sorting() throws SQLException, ClassNotFoundException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("1: Sort by name" + 
						 "\n2: Sort by surname" + 
						 "\n0: Sort by id (default sorting)");
		int answer = in.nextInt();
		in.close();
		
		if (answer == 1) showContacts("name");
		else if (answer == 2) showContacts("surname");
		else showContacts("id");
	}

	
	public void searchContact() throws SQLException, ClassNotFoundException{
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter data (or part of it) to look for:");
		String answer = in.nextLine();
		ResultSet rs = statement.executeQuery("SELECT * FROM telephone_book "
				+ "WHERE name LIKE '%" + answer + "%' "
				+ "OR surname LIKE '%" + answer + "%' "
				+ "OR phone_number LIKE '%" + answer + "%' "
				+ "OR email LIKE '%" + answer + "%' "
				+ "OR note LIKE '%" + answer + "%';");
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
		statement.close();
		connection.close();
		in.close();
	}
	
	public void insertContact() throws ClassNotFoundException, SQLException {
		
		
		Contact con;
		con = readData();
		
//		if (confirm()) {
			List<Contact> newContact = new ArrayList<>();
			newContact.add(con);
			ContactsList.writeContactListDB(newContact);
//		}
//		Scanner in = new Scanner(System.in);
//			//System.out.println(con.toString());
//			System.out.println("1: Save contact"+
//							 "\n2: Enter data again"+
//							 "\n0: Exit");
//			String answer = in.nextLine();
//			in.close();
//			if (answer.equals("1")) {
//				List<Contact> newContact = new ArrayList<>();
//				newContact.add(con);
//				ContactsList.writeContactListDB(newContact);
//			}
//			else if (answer.equals("2")) insertContact();
	}
	
	public void editContact(int id) throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter new data");
		Contact con = readData();
		//System.out.println(con.toString(true) + "\n");
		System.out.println("1: Save contact"+
						 "\n2: Enter data again"+
						 "\n0: Exit");
		String answer = in.nextLine();
		if (answer.equals("1")) {
			int rs = statement.executeUpdate("UPDATE telephone_book SET "
					+ "name = " + con.getName() + ", "
					+ "surname = " + con.getSurname() + ", "
					+ "phone_number = " + con.getPhoneNumber() + ", "
					+ "email = " + con.getEmail() + ", "
					+ "note = " + con.getNote() + ", "
					+ "WHERE id = " + id + ";");
		}
		else if (answer.equals("2")) editContact(id);
		statement.close();
		connection.close();
		in.close();
	}
	
	public int searchByID() throws SQLException, ClassNotFoundException {
		int id = 0;
		getConnection();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter id of contact to edit. Enter 0 to see all contacts:");
		String answer = in.nextLine();
		if (answer.equals("0")) {
			sorting();
			System.out.println("Enter id of contact to edit:");	
			answer = in.nextLine();
		}
		id = Integer.parseInt(answer);
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		
			ResultSet rs = statement.executeQuery("SELECT * FROM telephone_book WHERE id = " + id + ";");
			Contact con = new Contact();
			con.setID(rs.getInt("id"));
			con.setName(rs.getString("name"));
			con.setSurname(rs.getString("surname"));
			con.setPhoneNumber(rs.getString("phone_number"));
			con.setEmail(rs.getString("email"));
			con.setNote(rs.getString("note"));
			//System.out.println(con.toString(true) + "\n");
			System.out.println("1: Confirm" +
					 		 "\n2: Enter id again"+
					 		 "\n0: Exit");
			answer = in.nextLine();
			if (answer.equals("1"));
			else if (answer.equals("2")) searchByID();
			else id = 0;
		rs.close();		
		statement.close();
		connection.close();
		in.close();
		return id;
	}


}
