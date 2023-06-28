package it.beije.xvii.exercises.iannetta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {

	static Connection connection;
	static Statement statement;
	
	public static void setConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "MySQLPassword1!");
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Contact readData() {
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

	public static void showContacts(String orderBy) throws SQLException{
		setConnection();
		try {
			if (!orderBy.equals("name") && !orderBy.equals("surname")) orderBy = "id";
			List<Contact> listOfContacts = ContactsList.loadContactListFromDB("telephone_book", orderBy);
			for (Contact con : listOfContacts) System.out.println(con.toString(true) + "\n");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		statement.close();
		connection.close();
	}
	
	public static void sorting() throws SQLException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("1: Sort by name" + 
						 "\n2: Sort by surname" + 
						 "\n0: Sort by id (default sorting)");
		String answer = in.nextLine();
		
		if (answer.equals("1")) showContacts("name");
		else if (answer.equals("2")) showContacts("surname");
		else showContacts("id");
		
		in.close();
	}
	
	public static void searchContact() throws SQLException{
		setConnection();
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
	
	public static void insertContact() throws ClassNotFoundException, SQLException {
		Scanner in = new Scanner(System.in);
		String answer;
		Contact con;
		
			con = readData();
			
//			System.out.print("Enter name: ");
//			answer = in.nextLine();
//			con.setName(answer);
//			System.out.print("Enter surname: ");
//			answer = in.nextLine();
//			con.setSurname(answer);
//			System.out.print("Enter phone number: ");
//			answer = in.nextLine();
//			con.setPhoneNumber(answer);
//			System.out.print("Enter email: ");
//			answer = in.nextLine();
//			con.setEmail(answer);
//			System.out.print("Enter note: ");
//			answer = in.nextLine();
//			con.setNote(answer);
			
			//System.out.println(con.toString());
			System.out.println("1: Save contact"+
							 "\n2: Enter data again"+
							 "\n0: Exit");
			answer = in.nextLine();
			if (answer.equals("1")) {
				List<Contact> newContact = new ArrayList<>();
				newContact.add(con);
				ContactsList.writeContactListDB(newContact);
			}
			else if (answer.equals("2")) insertContact();
		in.close();
	}
	
	public static void editContact(int id) throws SQLException {
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
	
	public static int searchByID() throws SQLException {
		int id = 0;
		setConnection();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter id of contact to edit. Enter 0 to see all contacts:");
		String answer = in.nextLine();
		if (answer.equals("0")) {
			ContactsManager.sorting();
			System.out.println("Enter id of contact to edit:");	
			answer = in.nextLine();
		}
		id = Integer.parseInt(answer);
			
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
