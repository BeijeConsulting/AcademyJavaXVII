package it.beije.xvii.exercises.ceccarelli.DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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

import it.beije.suormary.rubrica.Contact;

public class ExerciseswithDB {
	
	private Connection connection = null;
	private Statement statement = null;
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	
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
	public static List<Element> getChildElements(Element el) {
		NodeList nodeList = el.getChildNodes();
		//System.out.println("nodeList size: " + nodeList.getLength());
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		
		//System.out.println("elementi: " + elements);
		return elements;
	}
	
	//import data from DB
	public List<Contact> loadRubricaFromDb() throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		Contact c = null;
		if(connectionCheck()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				c = new Contact();
				//System.out.println(rs.getInt("id"));
				c.setId(rs.getString("id"));
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
	
	//import data from XML
	public List<Contact> loadRubricaFromXML()  {
		List<Contact> contacts = null;
		try {
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse("/Users/Padawan/eclipse-workspace/File/contactFromDBtoXml.xml");
			
			//prendo elemento contenitore
			Element docEl = document.getDocumentElement();
			//System.out.println("docE1" + docEl.getTagName());
			//creo lista dei nodi sotto il contenitore(iin questo caso i singoli contatti
			List<Element> elements = getChildElements(docEl);
			
			contacts = new ArrayList<Contact>();
			Contact c = null;
			List<Element> els = null;
			for(Element el: elements) {
				//prende ogni tag sono ogni singolo contatto
				els = getChildElements(el);
				c=new Contact();
				for(Element e : els) {
					//System.out.println(e.getTagName() + " = " + e.getTextContent());
					switch (e.getTagName()) {
					case "id": c.setId(e.getTextContent());
						break;
					case "name": c.setName(e.getTextContent());
						break;
					case "surname": c.setSurname(e.getTextContent());
						break;
					case "phone": c.setPhoneNumber(e.getTextContent());
						break;
					case "email": c.setEmail(e.getTextContent());
						break;
					case "note": c.setNote(e.getTextContent());
						break;
					default: System.out.println("TagName non riconosciuto!");
						break;
				}
				}
				contacts.add(c);
			}
		}catch (ParserConfigurationException pEx) {
			pEx.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		 return contacts;
	}
	
	//import data from CSV
	public List<Contact> loadRubricaFromCSV(String path, String separator){
		File file = null;
		FileReader fileReader=null;
		List<Contact> contacts = null;
		try {
			file = new File(path);
			//read file
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// new list
			List<String> rows = new ArrayList<String>();
			while (bufferedReader.ready()) {
				
				String r = bufferedReader.readLine();
				rows.add(r);
			}
			
			contacts = new ArrayList<Contact>();
			Contact c = null;
			for(int i=1;i<rows.size();i++) {
				String[] contact = rows.get(i).split(separator);
				System.out.println(Arrays.toString(contact));
					c = new Contact();
					if(contact[0]!=null) {
						c.setSurname(contact[0].trim());
					} else {
						c.setSurname(" ");
					}
					if(contact[1]!= null) {
						c.setName(contact[1].trim());
					}else {
						c.setName(" ");
					}
					if(contact[2]!=null) {
						c.setPhoneNumber(contact[2].trim());
					}else {
						c.setPhoneNumber(" ");
					}
					if(contact[3]!=null) {
						c.setEmail(contact[3].trim());
					}else {
						c.setEmail(" ");
					}
					if(contact[4]!= null) {
						c.setNote(contact[4].trim());
					}else {
						c.setNote(" ");
					}
					
					contacts.add(c);
			}
			
			bufferedReader.close();
			fileReader.close();
			 
			} catch(NullPointerException e) {
				//System.out.println("file non trovato");
				e.printStackTrace();
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return contacts;
	}
	
	//writing data from DB to CSV
	public void writeRubricaFromDbToCSV(List<Contact> list) {
		FileWriter fileWriter= null;
		
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
	
	//writing data from DB to XML
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
				id.setTextContent(c.getId());
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
	
	//writing data from XML to DB
	public void writeRubricaFromXMLtoDb(List<Contact> list) throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		if(connectionCheck()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			for(Contact con: list) {
				//System.out.println("stampa il nome: " + con.getName());
				StringBuilder str = new StringBuilder("INSERT INTO rubrica(`nome`, `cognome`, `telefono`, `email`, `note`) VALUES ('")
						.append(con.getName().toString()).append("', '").append(con.getSurname().toString()).append("', '")
						.append(con.getPhoneNumber().toString()).append("', '").append(con.getEmail().toString()).append("', '").append(con.getNote().toString()).append("')");
				statement.executeUpdate(str.toString());
			}
		}
	}
	
	
	//writing data from CSV to DB
	public void writeRubricaFromCSVtoDb(List<Contact> list) throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		if(connectionCheck()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			for(Contact con: list) {
				//System.out.println("stampa il nome: " + con.getName());
				StringBuilder str = new StringBuilder("INSERT INTO rubrica(`nome`, `cognome`, `telefono`, `email`, `note`) VALUES ('")
						.append(con.getName().toString()).append("', '").append(con.getSurname().toString()).append("', '")
						.append(con.getPhoneNumber().toString()).append("', '").append(con.getEmail().toString()).append("', '").append(con.getNote().toString()).append("')");
				statement.executeUpdate(str.toString());
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExerciseswithDB db = new ExerciseswithDB();
		try {
			
			// DA DB A CSV E XML
			//List<Contact> list = db.loadRubricaFromDb();
			//db.writeRubricaFromDbToCSV(list);
			//db.writeRubricaFromDbToXML(list);
			
			// FROM XML TO DB
			//List<Contact> list2 = db.loadRubricaFromXML();
			//db.writeRubricaFromXMLtoDb(list2);
			
			//FROM CSV TO DB
			String path = "/Users/Padawan/eclipse-workspace/File/rubrica.csv";
			String separator = ";";
			List<Contact> list3 = db.loadRubricaFromCSV(path,separator);
			db.writeRubricaFromCSVtoDb(list3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("FINE ");
	}

}
