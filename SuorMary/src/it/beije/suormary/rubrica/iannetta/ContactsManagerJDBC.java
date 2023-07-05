package it.beije.suormary.rubrica.iannetta;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class ContactsManagerJDBC {
	
	Scanner in;
	private final String[] contactFields = {"id", "name", "surname", "telephone number", "email", "note"};
	
	public void end() {
		in.close();
	}
	
	public ContactsManagerJDBC() {
		this.in = new Scanner(System.in);
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "MySQLPassword1!");
		return connection;
	}
	
	public boolean[] setDataToRead() {
		int answer;
		boolean[] result = new boolean[contactFields.length];
		result[0] = false; //id
		for (int i = 1; i < contactFields.length; i++) {
			System.out.println("Do you want to edit " + contactFields[i] + "? \n0: NO\n1: YES");
			answer = in.nextInt();
			if (answer == 1) result[i] = true;
			else result[i] = false;
		}
		return result;
	}
	
	public String[] readData(boolean[] dataToRead){
		String result[] = new String[contactFields.length];
	String answer;
	for (int i = 1; i < contactFields.length; i++) {
		if (dataToRead[i]) {
			System.out.println("Enter " + contactFields[i] + ":");
			answer = in.nextLine();
			result[i] = answer;
		}
		else result[i] = null;
	}
	return result;
}
		
	public void showContacts(String orderBy) throws ClassNotFoundException, SQLException{
		ContactsList contactsList = new ContactsList();
		if (!orderBy.equals("name") && !orderBy.equals("surname")) orderBy = "id";
		List<Contact> listOfContacts = contactsList.loadContactListFromDB("telephone_book", orderBy);
		for (Contact con : listOfContacts) System.out.println(con.toString() + "\n");
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
			System.out.println(con.toString() + "\n");
		}
		rs.close();		
		preparedStatement.close();
		connection.close();
	}
	
	public void insertContact() throws ClassNotFoundException, SQLException {
		ContactsList contactsList = new ContactsList();
		List<Contact> list = new ArrayList<>();
		Contact contact = new Contact();
		String [] newContactData = readData(new boolean[]{false, true, true, true, true, true});
		
		contact.setName(newContactData[1]);
		contact.setSurname(newContactData[2]);
		contact.setPhoneNumber(newContactData[3]);
		contact.setEmail(newContactData[4]);
		contact.setNote(newContactData[5]);
		list.add(contact);
		contactsList.writeContactListDB(list);
	}
	
	public void editContact(int id) throws SQLException, ClassNotFoundException {
		
		Connection connection = getConnection();
		
		System.out.println("Enter new data");
		String [] updateContact = readData(setDataToRead());
		
		StringBuilder sb = new StringBuilder("UPDATE telephone_book SET");
		if (updateContact[1] != null) sb.append(" name = '" + updateContact[1] + "'");
		if (updateContact[2] != null) sb.append(", surname = '" + updateContact[2] + "'");
		if (updateContact[3] != null) sb.append(", phone_number = '" + updateContact[3] + "'");
		if (updateContact[4] != null) sb.append(", email = '" + updateContact[4] + "'");
		if (updateContact[5] != null) sb.append(", note = '" + updateContact[5] + "'");
		
		if (sb.charAt(0) == ',') sb.deleteCharAt(0);
		if (sb.charAt(sb.length() - 1) == ',') sb.deleteCharAt(sb.length() - 1);
		sb.append(" WHERE id = " + id + ";");
		Statement statement = connection.createStatement();
		statement.executeUpdate(sb.toString());
		statement.close();
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
		
		Connection connection = getConnection();
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
			System.out.println(con.toString());
		}
		rs.close();		
		statement.close();
		connection.close();
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

	public List<List<Contact>> findDuplicates() throws ClassNotFoundException, SQLException{
		
		Connection connection = getConnection();
		ContactsList contactsList = new ContactsList();
		Statement statement = connection.createStatement();
		
		ResultSet rs = null;
		List<Contact> listOfContacts = contactsList.loadContactListFromDB("telephone_book", "name");
		List<List<Contact>> listOfDuplicates = new ArrayList<>();
		String number;
		List<Contact> duplicates = new ArrayList<>();
		
		Contact con1 = new Contact();
		Contact con2 = new Contact();
		while (listOfContacts.size() > 0) {
			con1 = listOfContacts.get(0);
			number = con1.getPhoneNumber();
			rs = statement.executeQuery("SELECT * FROM telephone_book WHERE phone_number = '" + number + "';");
			while (rs.next()) {
				con2.setID(rs.getInt("id"));
				con2.setName(rs.getString("name"));
				con2.setSurname(rs.getString("surname"));
				con2.setPhoneNumber(rs.getString("phone_number"));
				con2.setEmail(rs.getString("email"));
				con2.setNote(rs.getString("note"));
				duplicates.add(con2);
				listOfContacts.remove(con2);
			}
			listOfDuplicates.add(duplicates);
		}
		
		for (List<Contact> duplicatesContacts : listOfDuplicates) {
			System.out.println(duplicatesContacts);
		}
		rs.close();
		statement.close();
		connection.close();
		return listOfDuplicates;
	}
		
	public void mergeDuplicates() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		ContactsList contactsList = new ContactsList();
		Statement statement = connection.createStatement();
		
		List<Contact> list = new ArrayList<Contact>();
		List<List<Contact>> listOfDuplicates = findDuplicates();
		
		Contact keepThisContact;
		Contact checkThisContact;
		int id;
		String name;
		String surname;
		String email;
		String note;
		String checkName;
		String checkSurname;
		String checkEmail;
		String checkNote;
		
		String answer;
		
		for (List<Contact> duplicates: listOfDuplicates) {
			keepThisContact = duplicates.get(0);
			
			id = keepThisContact.getID();
			
			name = keepThisContact.getName();
			if (name == null) {
				name = "";
				keepThisContact.setName(name);
			}
			
			surname = keepThisContact.getSurname();
			if (surname == null) {
				surname = "";
				keepThisContact.setName(surname);
			}
			
			//non dovrebbe essere null in generale
			if (keepThisContact.getPhoneNumber() == null) keepThisContact.setPhoneNumber("");
			
			email = keepThisContact.getEmail();
			if (email == null) {
				email = "";
				keepThisContact.setEmail(email);
			}
			
			note = keepThisContact.getNote();
			if (note == null) {
				note = "";
				keepThisContact.setNote(note);
			}
			
			for (int i = 1; i < duplicates.size(); i++) {
				checkThisContact = duplicates.get(i);
				//conflitti
				
				//nome
				checkName = checkThisContact.getName();
				if (checkName == null || checkName.contentEquals("") || name.equals(checkName));
				else {
					System.out.println("Conflict: Which name do you want to keep?");
					System.out.println("0: " + name);
					System.out.println("1: " + checkName);
					answer = in.nextLine();
					if (answer.equals("1")) keepThisContact.setName(checkName);
				}
				
				//cognome
				checkSurname = checkThisContact.getSurname();
				if (checkSurname == null || checkSurname.contentEquals("") || surname.equals(checkSurname));
				else {
					System.out.println("Conflict: Which surname do you want to keep?");
					System.out.println("0: " + surname);
					System.out.println("1: " + checkThisContact.getSurname());
					answer = in.nextLine();
					if (answer.equals("1")) keepThisContact.setSurname(checkThisContact.getSurname());
				}
				
				//email
				checkEmail = checkThisContact.getEmail();
				if (checkEmail == null || checkEmail.contentEquals("") || email.equals(checkEmail));
				else {
					System.out.println("Conflict: Which email do you want to keep?");
					System.out.println("0: " + email);
					System.out.println("1: " + checkThisContact.getEmail());
					answer = in.nextLine();
					if (answer.equals("1")) keepThisContact.setEmail(checkThisContact.getEmail());
				}
				
				//note
				checkNote = checkThisContact.getNote();
				if (checkNote == null || checkNote.contentEquals("") || note.equals(checkNote));
				else {
					System.out.println("Conflict: Which note do you want to keep?");
					System.out.println("0: " + note);
					System.out.println("1: " + checkThisContact.getNote());
					answer = in.nextLine();
					if (answer.equals("1")) keepThisContact.setNote(checkThisContact.getNote());
				}
				
				deleteContact(checkThisContact.getID());
			}
			list.add(keepThisContact);
			contactsList.writeContactListDB(list);
			deleteContact(id);
		}
		statement.close();
		connection.close();
		System.out.println("Contacts merged");
	}

	public boolean checkFile (String path) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("File does not exist");
			return false;
		}
		if (!path.endsWith(".xml") && !path.endsWith(".csv")) {
			System.out.println("Invalid format");
			return false;
		}
		return true;
	}
	
	public void importFrom() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, SQLException {
		System.out.println("Enter path of file containing contacts to add (.xml or .csv) : ");
		String path = in.nextLine();
		List<Contact> listOfContacts = null;
		ContactsList contactsList = new ContactsList();
		
		if (checkFile(path)) {
			if (path.endsWith(".xml")) {
				listOfContacts = contactsList.loadContactListFromXML(path);
				contactsList.writeContactListDB(listOfContacts);
			}
			else if (path.endsWith(".csv")) {
				System.out.println("Enter separator: ");
				String separator = in.nextLine();
				listOfContacts = contactsList.loadContactListFromCSV(path, separator);
				contactsList.writeContactListDB(listOfContacts);
			}
		}
		System.out.println("Contacts imported");
	}
	
	public void exportTo() throws IOException, ParserConfigurationException, SAXException, TransformerException, ClassNotFoundException, SQLException {
		System.out.println("Enter path of file where export contacts to (.xml or .csv) : ");
		String path = in.nextLine();
		List<Contact> listOfContacts = new ArrayList<>();
		ContactsList contacstList = new ContactsList();
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("SELECT * FROM telephone_book;");
		Contact con = new Contact();
		
		if (path.endsWith(".xml")) {
			while (rs.next()) {
				con.setID(rs.getInt("id"));
				con.setName(rs.getString("name"));
				con.setSurname(rs.getString("surname"));
				con.setPhoneNumber(rs.getString("phoneNumber"));
				con.setEmail(rs.getString("email"));
				con.setNote( rs.getString("nome"));
				listOfContacts.add(con);
			}
			contacstList.writeContactListXLM(listOfContacts, path);
		}
		else if (path.endsWith(".cvs")) {
			System.out.println("Enter separator: ");
			String separator = in.nextLine();
			while (rs.next()) {
				con.setID(rs.getInt("id"));
				con.setName(rs.getString("name"));
				con.setSurname(rs.getString("surname"));
				con.setPhoneNumber(rs.getString("phoneNumber"));
				con.setEmail(rs.getString("email"));
				con.setNote( rs.getString("nome"));
				listOfContacts.add(con);
			}
			contacstList.writeContactListCSV(listOfContacts, path, separator);
		}
		else System.out.println("Invalid format");
		rs.close();
		statement.close();
		connection.close();
	}
}
