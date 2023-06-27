package it.beije.xvii.exercises.mancuso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RubricaJDBC {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		List<Contact> contacts = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "myDatabase1");
			
			statement = connection.createStatement();
			
			//SELECT
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			Contact c = null;
			
			contacts = new ArrayList<Contact>();
			
			while(rs.next()) {
				/*System.out.println("id : " + rs.getInt(1));
				System.out.println("nome : " + rs.getString(2));
				System.out.println("cognome : " + rs.getString(3));
				System.out.println("telefono : " + rs.getString(4));
				System.out.println("email : " + rs.getString(5));
				System.out.println("note : " + rs.getString(6));*/
				
				c = new Contact();
				
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String telefono = rs.getString("telefono");
				String email = rs.getString("email");
				String note = rs.getString("note");
				
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("nome : " + nome);
				System.out.println("cognome : " + cognome);
				System.out.println("telefono : " + telefono);
				System.out.println("email : " + email);
				System.out.println("note : " + note);
				
				System.out.println("------------------");
				
				c.setFirstName(nome);
				c.setLastName(cognome);
				c.setPhoneNumber(telefono);
				c.setEmail(email);
				c.setNotes(note);
				
				contacts.add(c);
			}
			rs.close();
			
			AddressBook ab = new AddressBook();
			
			ab.writeAddressBookCSV("/Temp/addressBookFromDB.csv", ";", contacts);
			
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
