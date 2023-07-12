package it.beije.suormary.web.mancuso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class JDBCUtils {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "myDatabase1");		
		return connection;
	}
	
	// Fatto metodo in JDBC perchè hibernate e jpql non ammettono subqueries nel from
	public static List<Contact> findDuplicates() {
		List<Contact> contacts = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet rs = null;

		try {
			connection = JDBCUtils.getConnection();
	
			preparedStatement = connection.prepareStatement("SELECT * FROM rubrica AS c, "
					+ "(SELECT sub.nome, sub.cognome, sub.note "
					+ "FROM rubrica AS sub "
					+ "GROUP BY nome, cognome, note HAVING COUNT(*) > 1) "
					+ "AS sub2 WHERE c.nome = sub2.nome and c.cognome = sub2.cognome and "
					+ "and c.note = sub2.note");
			
			
			rs = preparedStatement.executeQuery();
			Contact c = null;
			
			contacts = new ArrayList<Contact>();
			
			while(rs.next()) {
				c = new Contact();
				
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				//String telefono = rs.getString("telefono");
				//String email = rs.getString("email");
				String note = rs.getString("note");
				
				c.setFirstName(nome);
				c.setLastName(cognome);
				//c.setPhoneNumber(telefono);
				//c.setEmail(email);
				c.setNotes(note);
				
				contacts.add(c);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return contacts;
	}
}
