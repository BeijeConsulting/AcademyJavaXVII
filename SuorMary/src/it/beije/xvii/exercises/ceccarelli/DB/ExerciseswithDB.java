package it.beije.xvii.exercises.ceccarelli.DB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.beije.suormary.rubrica.Contact;

public class ExerciseswithDB {
	
	private Connection connection = null;
	private Statement statement = null;
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	
	//connection check
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
	
	
	//importare dati da DB
	public List<Contact> loadRubricaFromDb() throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		Contact c = null;
		if(connectionCheck()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				c = new Contact();
				//System.out.println(rs.getInt("id"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				c.setPhoneNumber(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				c.setNote(rs.getString("note"));
				
				//System.out.println(c);
				contacts.add(c);
				}
		} else {
			throw new SQLException();
		}
		return contacts;
		
	}
	
	public void writeRubricaFromDbToCSV(List<Contact> list) {
		FileWriter fileWriter= null;
		
		//scrivere dati su CSV
		try {
			fileWriter = new FileWriter("/Users/Padawan/eclipse-workspace/File/rubricaFromDb.csv");
			for(Contact cr : list) {
				System.out.println(cr.getId());
				fileWriter.write(cr.getId());
				fileWriter.write(';');
				fileWriter.write(cr.getName());
				fileWriter.write(';');
				fileWriter.write(cr.getSurname());
				fileWriter.write(';');
				fileWriter.write(cr.getPhoneNumber());
				fileWriter.write(';');
				fileWriter.write(cr.getEmail());
				fileWriter.write(';');
				fileWriter.write(cr.getNote());
				fileWriter.write('\n');
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeRubricaFromDbToXML(List<Contact> list) {
		try {
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();
			Element contacts = document.createElement("contacts");
			document.appendChild(contacts);
			
			Element contact = null;
			for (Contact c : list) {
				contact = document.createElement("contact");
				
				Element id = document.createElement("id");
				id.setTextContent(Integer.toString(c.getId()));
				contact.appendChild(id);
				if (c.getName() != null) {
					Element name = document.createElement("name");
					name.setTextContent(c.getName());
					contact.appendChild(name);
				}
				if (c.getSurname() != null) {
					Element surname = document.createElement("surname");
					surname.setTextContent(c.getSurname());
					contact.appendChild(surname);
				}
				if (c.getPhoneNumber() != null) {
					Element phoneNumber = document.createElement("phone");
					phoneNumber.setTextContent(c.getPhoneNumber());
					contact.appendChild(phoneNumber);
				}
				if (c.getEmail() != null) {
					Element email = document.createElement("email");
					email.setTextContent(c.getEmail());
					contact.appendChild(email);
				}
				if (c.getNote() != null) {
					Element note = document.createElement("note");
					note.setTextContent(c.getNote());
					contact.appendChild(note);
				}
				
				contacts.appendChild(contact);
			}
			
			// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
				
				StreamResult result = new StreamResult(new File("/Users/Padawan/eclipse-workspace/File/contactFromDBtoXml.xml"));
				// trasforma il documento in xml
				transformer.transform(source, result);
		
		}catch (TransformerConfigurationException tcEx) {
			tcEx.printStackTrace();
		}catch (ParserConfigurationException pEx) {
			pEx.printStackTrace();
		} catch (TransformerException tEx) {
			tEx.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExerciseswithDB db = new ExerciseswithDB();
		try {
			List<Contact> list = db.loadRubricaFromDb();
			db.writeRubricaFromDbToCSV(list);
			db.writeRubricaFromDbToXML(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("FINE DA DB A CSV");
	}

}
