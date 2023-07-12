package it.beije.suormary.web.ceccarelli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class NuovaRubricaMetodi {
	//metodi con nuova tabella "riferimento" usando JDBC
	
	public Connection connection = null;
	public Statement statement = null;
	public DocumentBuilderFactory documentBuilderFactory;
	public DocumentBuilder documentBuilder;
	public Document document;
	
	//connection check and take child elements
	public boolean connectionCheck() {
		boolean check=true;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alice_ceccarelli?serverTimezone=CET", "root", "Ali1196");
			statement = connection.createStatement();
			if(connection.isClosed()) {
				check= false;
			}
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//lista contatti nuovi
	public List<Contact2> contactNew() throws SQLException {
		List<Contact2> contacts = new ArrayList<>();
		Contact2 c = null;
		if(connectionCheck()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				c = new Contact2();
				//System.out.println(rs.getInt("id"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				c.setNote(rs.getString("note"));
				//System.out.println(c);
				contacts.add(c);
				}
		} else {
			throw new SQLException();
		}
		return contacts;
	}
	
	//lista contatti con JOIN
		public List<Contact2> contactNewJOIN() throws SQLException {
			List<Contact2> contacts = new ArrayList<>();
			Contact2 c = null;
			ContactDetail cd = null;
			if(connectionCheck()) {
				ResultSet rs = statement.executeQuery("SELECT r.id, r.nome, r.cognome, r.note, rif.contatto, rif.label, rif.tipo FROM riferimento as rif JOIN rubrica as r ON r.id = rif.id_rubrica;");
				while (rs.next()) {
					c = new Contact2();
					cd = new ContactDetail();
					//System.out.println(rs.getInt("id"));
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("nome"));
					c.setSurname(rs.getString("cognome"));
					c.setNote(rs.getString("note"));
					cd.setId_contact(rs.getInt("id"));
					cd.setType(rs.getString("tipo").charAt(0));
					cd.setLabel(rs.getString("label"));
					c.setDetails(cd);
					//System.out.println(c);
					contacts.add(c);
					}
			} else {
				throw new SQLException();
			}
			return contacts;
		}
}
