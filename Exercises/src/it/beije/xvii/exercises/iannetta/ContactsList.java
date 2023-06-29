package it.beije.xvii.exercises.iannetta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ContactsList {

	public static List<Contact> loadContactListFromCSV (String pathFile, String separator) throws IOException{
		FileReader fileReader = new FileReader(pathFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		List<String> contactInfo = new ArrayList<>();
		Contact con = null;
		List<Contact> listOfContacts = new ArrayList<>();
 		
		String line;
		int name;
		int surname;
		int telephone;
		int email;
		int note;
		
		if (bufferedReader.ready()) {
			line = bufferedReader.readLine();
			contactInfo = Arrays.asList(line.split(separator));
			name = contactInfo.indexOf("NOME");
			surname = contactInfo.indexOf("COGNOME");
			telephone = contactInfo.indexOf("TELEFONO");
			email = contactInfo.indexOf("EMAIL");
			note = contactInfo.indexOf("NOTE");
		
			while (bufferedReader.ready()) {
				line = bufferedReader.readLine();
				contactInfo = Arrays.asList(line.split(separator));
				con = new Contact();
				if (name != -1) con.setName(contactInfo.get(name));
				if (surname != -1) con.setSurname(contactInfo.get(surname));
				if (telephone != -1) con.setPhoneNumber(contactInfo.get(telephone));
				if (email != -1)con.setEmail(contactInfo.get(email));
				if (note != -1)con.setNote(contactInfo.get(note));
				listOfContacts.add(con);
			}
		}
		bufferedReader.close();
		fileReader.close();
		
		return listOfContacts;
	}
	
	public static List<Contact> loadContactListFromXML(String pathFile) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(pathFile);
		
		Element docEl = document.getDocumentElement();
		List<Element> elements = getChildElements(docEl);
		
		Contact con = null;
		List<Contact> listOfContacts = new ArrayList<>();
		List<Element> els = null;
		
		for (Element el : elements) {
			els = getChildElements(el);
			con = new Contact();
			for (Element e : els) {
				switch (e.getTagName()) {
					case "nome": con.setName(e.getTextContent());
						break;
					case "cognome": con.setSurname(e.getTextContent());
						break;
					case "telefono": con.setPhoneNumber(e.getTextContent());
						break;
					case "email": con.setEmail(e.getTextContent());
						break;
					case "note": con.setNote(e.getTextContent());
						break;
					default: System.out.println("TagName non riconosciuto!");
						break;
				}
			}	
			listOfContacts.add(con);
		}
		return listOfContacts;
	}
	
	public static List<Element> getChildElements(Element el) {
		NodeList nodeList = el.getChildNodes();
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		return elements;
	}
	
	public static List<Contact> loadContactListFromDB (String table, String... orderBy) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");	
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "MySQLPassword1!");Statement statement = null;
		statement = connection.createStatement();
		//System.out.println("connection open? " + !connection.isClosed());
		
		ResultSet rs = statement.executeQuery("SELECT * FROM " + table + " ORDER BY " + orderBy[0] + ";");
		List<Contact> listOfContacts = new ArrayList<>();
		Contact con = null;
		while (rs.next()) {
			con = new Contact();
			con.setID(rs.getInt("id"));
			con.setName(rs.getString("name"));
			con.setSurname(rs.getString("surname"));
			con.setPhoneNumber(rs.getString("phone_number"));
			con.setEmail(rs.getString("email"));
			con.setNote(rs.getString("note"));
			listOfContacts.add(con);
		}
		rs.close();
		statement.close();
		connection.close();
		return listOfContacts;
	}
	
	public static void writeContactListsXLM(List<Contact> listOfContacts, String pathFile) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document;
		
	
		Element contacts;
		if (new File(pathFile).exists()) {
			document = documentBuilder.parse(pathFile);
			contacts = document.getDocumentElement();
		}
		else {
			document = documentBuilder.newDocument();
			contacts = document.createElement("contacts");
			document.appendChild(contacts);
		}
		
		Element contact = null;
		for (Contact con : listOfContacts) {
			contact = document.createElement("contact");
			
			if (con.getName() != null) {
				Element name = document.createElement("name");
				name.setTextContent(con.getName());
				contact.appendChild(name);
			}
			if (con.getSurname() != null) {
				Element surname = document.createElement("surname");
				surname.setTextContent(con.getSurname());
				contact.appendChild(surname);
			}
			if (con.getPhoneNumber() != null) {
				Element phoneNumber = document.createElement("phone");
				phoneNumber.setTextContent(con.getPhoneNumber());
				contact.appendChild(phoneNumber);
			}
			if (con.getEmail() != null) {
				Element email = document.createElement("email");
				email.setTextContent(con.getEmail());
				contact.appendChild(email);
			}
			if (con.getNote() != null) {
				Element note = document.createElement("note");
				note.setTextContent(con.getNote());
				contact.appendChild(note);
			}
			
			contacts.appendChild(contact);
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		// Output to console for testing
		StreamResult result = new StreamResult(new File(pathFile));
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);
		
		System.out.println("Contacts added to XML file");
	}	
	
 	public static void writeContactListCSV(List<Contact> listOfContacts, String pathFile, String separator) throws IOException{
		
		FileWriter fileWriter = null;
		StringBuilder line;
		if ((new File(pathFile)).exists()) fileWriter = new FileWriter(pathFile, true);
		else {
			fileWriter = new FileWriter(pathFile);
			line = new StringBuilder("NOME;COGNOME;TELEFONO;EMAIL;NOTE\n");
			fileWriter.write(line.toString());
		}
		
		line = null;
		
		for (Contact con : listOfContacts) {
			line = new StringBuilder(con.getName() + separator + con.getSurname() + separator + con.getPhoneNumber() 
												   + separator + con.getEmail() + separator + con.getNote() + "\n");
			fileWriter.write(line.toString());
			line = null;
		}
		fileWriter.flush();
		fileWriter.close();
		
		System.out.println("Contacts added to CSV file");
	}
	
 	public static void writeContactListDB(List<Contact> listOfContacts) throws ClassNotFoundException, SQLException {
 		Class.forName("com.mysql.cj.jdbc.Driver");	
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "MySQLPassword1!");
		Statement statement = null;			
		statement = connection.createStatement();
		StringBuilder query;
		for (Contact con : listOfContacts) {
			query = new StringBuilder("INSERT INTO telephone_book (`name`, `surname`, `phone_number`, `email`, `note`) VALUES ('")
					.append(con.getName()).append("', '").append(con.getSurname()).append("', '").append(con.getPhoneNumber()).append("', '")
					.append(con.getEmail()).append("', '").append(con.getNote()).append("');");
			statement.executeUpdate(query.toString());
		}
		statement.close();
		connection.close();
		//System.out.println("Contacts added to database");
 	}
 	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException, ClassNotFoundException, SQLException{
		
		String pathReadFile = "C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\rubricanotel.csv";
		String separator = ";";
		String pathWriteFile = "db";
		
		//read
		List<Contact> listOfContacts = new ArrayList<>();
		if (pathReadFile.endsWith(".csv")) listOfContacts = loadContactListFromCSV(pathReadFile, separator);
		else if (pathReadFile.endsWith(".xml")) listOfContacts = loadContactListFromXML(pathReadFile);
		else if (pathReadFile.equals("db")) listOfContacts = loadContactListFromDB("rubrica");
		
		//write
		if (pathWriteFile.endsWith(".csv")) writeContactListCSV(listOfContacts, pathWriteFile, separator);
		else if (pathWriteFile.endsWith(".xml")) writeContactListsXLM(listOfContacts, pathWriteFile);
		else if (pathWriteFile.equals("db")) writeContactListDB(listOfContacts);
		
	}

}
