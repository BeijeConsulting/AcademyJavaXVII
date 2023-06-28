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
			
			//INSERT
			// statement.executeUpdate("INSERT INTO rubrica (nome, cognome, telefono, email, note) "
			//		+ "VALUES ('Serena','Rossi','3925841223','serena.rossi@beije.it','Questo è il quarto contatto.')");
			
			/*String inputNome = "Elena";
			String inputCognome = "Verdi";
			String inputTelefono = "3664855212";
			String inputEmail = "elena.verdi@beije.it";
			
			StringBuilder query = new StringBuilder("INSERT INTO rubrica (nome, cognome, telefono, email) VALUES(");
			
			query.append("'");
			query.append(inputNome);
			query.append("', '");
			query.append(inputCognome);
			query.append("', '");
			query.append(inputTelefono);
			query.append("', '");
			query.append(inputEmail);
			query.append("')");
			
			statement.executeUpdate(query.toString());*/
			
			//DELETE
			int u = statement.executeUpdate("DELETE FROM rubrica WHERE id > 5");
			// returns number of deleted records
			
			System.out.println("Deleted records : " + u);
			
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
				
				c.setFirstName(nome);
				c.setLastName(cognome);
				c.setPhoneNumber(telefono);
				c.setEmail(email);
				c.setNotes(note);
				
				System.out.println(c.toString());
				
				System.out.println("---------------------------");
				
				contacts.add(c);
			}
			rs.close();
			
			//AddressBook ab = new AddressBook();
			
			//ab.writeAddressBookCSV("/Temp/addressBookFromDB.csv", ";", contacts);
			//ab.writeAddressBookXML(contacts, "/Temp/addressBookFromDB.xml");
			
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
