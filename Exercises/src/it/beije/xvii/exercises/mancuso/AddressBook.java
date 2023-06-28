package it.beije.xvii.exercises.mancuso;

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

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.Collections;
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
import java.io.BufferedReader;


public class AddressBook {
	
	public List<Contact> contacts;
	
	public AddressBook() {
		contacts = new ArrayList<>();
	}

	private static List<String[]> readCSV(File file, String separator){
		FileReader fReader = null;
		List<String[]> csv = new ArrayList<>();
		BufferedReader bReader = null;
		try {
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			while(bReader.ready()) {
				String line = bReader.readLine();
				String[] values = line.split(separator, -1);
				csv.add(values);
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fReader.close();
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return csv;
	}
	
	public List<Contact> loadAddressesFromCSV(String pathFile, String separator) throws IllegalArgumentException{
		
		File file = null;
		
		file = new File(pathFile);
		
		if(file.exists()) {
			if(!file.isDirectory()) {
				
				String filename = file.getName();

				
				if(filename.contains(".csv")) {
					
					List<Contact> newContacts = new ArrayList<Contact>();
					List<String[]> csv = readCSV(file, separator);
					
					String[] headers = csv.get(0);
					
					int idxFirstName = -1;
					int idxLastName = -1;
					int idxPhone = -1;
					int idxEmail = -1;
					int idxNotes = -1;
					
					for(int i=0;i<headers.length;i++) {
						if(headers[i].toUpperCase().equals("NAME")) {
							idxFirstName = i;
						}
						if(headers[i].toUpperCase().equals("SURNAME")) {
							idxLastName = i;
						}
						if(headers[i].toUpperCase().equals("PHONE")) {
							idxPhone = i;
						}
						if(headers[i].toUpperCase().equals("EMAIL")) {
							idxEmail = i;
						}
						if(headers[i].toUpperCase().equals("NOTES")) {
							idxNotes = i;
						}
					}
					
					Contact c = null;
					for(int i=1; i<csv.size(); i++) {
						c = new Contact();
						if(idxFirstName != -1) {
							c.setFirstName(csv.get(i)[idxFirstName]);
						}
						if(idxLastName != -1) {
							c.setLastName(csv.get(i)[idxLastName]);
						}
						if(idxPhone != -1) {
							c.setPhoneNumber(csv.get(i)[idxPhone]);
						}
						if(idxEmail != -1) {
							c.setEmail(csv.get(i)[idxEmail]);
						}
						if(idxNotes != -1) {
							c.setNotes(csv.get(i)[idxNotes]);
						}				
						newContacts.add(c);
					}
					return newContacts;
				}
				throw new IllegalArgumentException("Specified file is not a .CSV.");
			}
			throw new IllegalArgumentException("File path leads to a directory.");
		}	
		throw new IllegalArgumentException("File does not exist.");
		
	}
	
	public static List<Element> getChildrenElements(Element el) {
		NodeList nodeList = el.getChildNodes();
		//System.out.println("nodeList size: " + nodeList.getLength());
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		
		return elements;
	}
	
	public List<Contact> loadAddressesFromXML(String pathFile) throws IllegalArgumentException{
		File file = new File(pathFile);
		String filename = file.getName();
		List<Contact> newContacts = null;
		if(file.exists()) {
			if(!file.isDirectory()) {
				if(filename.contains(".xml")) {
					try {
						
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse(pathFile);
						
						Element rootElement = document.getDocumentElement();
						
						List<Element> elements = getChildrenElements(rootElement);	
						newContacts = new ArrayList<>();
						List<Element> innerElements;
						Contact c = null;
						for(Element e : elements) {
							
							innerElements = getChildrenElements(e);
							
							c = new Contact();
							for(Element inEl : innerElements) {
											
								switch(inEl.getTagName()) {
								
								case "name":
									c.setFirstName(inEl.getTextContent());
									break;
								case "surname":
									c.setLastName(inEl.getTextContent());
									break;
								case "phone":
									c.setPhoneNumber(inEl.getTextContent());
									break;
								case "email":
									c.setEmail(inEl.getTextContent());
									break;
								case "note":
									c.setNotes(inEl.getTextContent());
									break;
								default:
									System.out.println("Elemento non riconosciuto.");
									break;				
								}
								
							}
							newContacts.add(c);
						}
					} catch (ParserConfigurationException e1) {
						e1.printStackTrace();
					} catch (SAXException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					return newContacts;
				}
				throw new IllegalArgumentException("Specified file is not a .xml.");
			}
			throw new IllegalArgumentException("File path leads to a directory.");
		}	
		throw new IllegalArgumentException("File does not exist.");
		
	}
	
	public List<Contact> loadAddressesFromJDBC(){
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
				
				/*System.out.println("id : " + rs.getInt("id"));
				System.out.println("nome : " + nome);
				System.out.println("cognome : " + cognome);
				System.out.println("telefono : " + telefono);
				System.out.println("email : " + email);
				System.out.println("note : " + note);
				
				System.out.println("------------------");*/
				
				c.setFirstName(nome);
				c.setLastName(cognome);
				c.setPhoneNumber(telefono);
				c.setEmail(email);
				c.setNotes(note);
				
				contacts.add(c);
			}
			rs.close();			
			
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
		return contacts;
	}
	
	public void writeAddressBookCSV(String pathFile, String separator, boolean append) {
		
		FileWriter writer = null;
		
		try {
			
			writer = new FileWriter(pathFile, append);
			
			if(append) {
				File file = new File(pathFile);
				
				if(!file.exists()) {
					writer.write("NAME;SURNAME;PHONE;EMAIL;NOTES\n");
				}
			}else {
				writer.write("NAME;SURNAME;PHONE;EMAIL;NOTES\n");
			}		
			
			for (Contact contact : contacts) {
				if(contact.getFirstName() != null) {
					writer.write(contact.getFirstName());
				}else {
					writer.write("");
				}
				writer.write(separator);
				if(contact.getLastName() != null) {
					writer.write(contact.getLastName());
				}else {
					writer.write("");
				}
				writer.write(separator);
				if(contact.getPhoneNumber() != null) {
					writer.write(contact.getPhoneNumber());
				}else {
					writer.write("");
				}
				writer.write(separator);
				if(contact.getEmail() != null) {
					writer.write(contact.getEmail());
				}else {
					writer.write("");
				}
				writer.write(separator);
				if(contact.getNotes() != null) {
					writer.write(contact.getNotes());
				}else {
					writer.write("");
				}
				writer.write('\n');
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {			
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void writeAddressBookXML(String filePath, boolean append) {
		
		File file = new File(filePath);
		List<Contact> oldContacts = null;
		
		if(append) {
			if(file.exists()) {
				oldContacts = loadAddressesFromXML(filePath);
				for (int i=0; i<oldContacts.size(); i++) {
					contacts.add(oldContacts.get(i));
				}
			}
		}	
		
		try {
		
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			Element root = document.createElement("addressBook");
			document.appendChild(root);
			
			Element el = null;
			
			for (Contact c : contacts) {
				el = document.createElement("contact");
				
				if (c.getFirstName() != null) {
					Element name = document.createElement("name");
					name.setTextContent(c.getFirstName());
					el.appendChild(name);
				}
				if (c.getLastName() != null) {
					Element surname = document.createElement("surname");
					surname.setTextContent(c.getLastName());
					el.appendChild(surname);
				}
				if (c.getPhoneNumber() != null) {
					Element phoneNumber = document.createElement("phone");
					phoneNumber.setTextContent(c.getPhoneNumber());
					el.appendChild(phoneNumber);
				}
				if (c.getEmail() != null) {
					Element email = document.createElement("email");
					email.setTextContent(c.getEmail());
					el.appendChild(email);
				}
				if (c.getNotes() != null) {
					Element note = document.createElement("note");
					note.setTextContent(c.getNotes());
					el.appendChild(note);
				}
				
				root.appendChild(el);
				
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
					
			StreamResult result = new StreamResult(file);
	
			// Output to console for testing
			//StreamResult syso = new StreamResult(System.out);
	
			transformer.transform(source, result);
			//transformer.transform(source, syso);
	
			//System.out.println("File saved!");
		} catch (ParserConfigurationException pEx) {
			pEx.printStackTrace();
		} catch (TransformerConfigurationException tcEx) {
			tcEx.printStackTrace();
		} catch (TransformerException tEx) {
			tEx.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeAddressBookJDBC(boolean overwrite) {
		Connection connection = null;
		Statement statement = null;

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "myDatabase1");
			
			statement = connection.createStatement();
			
			if(overwrite) {
				System.out.println("Deleting all records ...");
				statement.executeUpdate("DELETE FROM rubrica WHERE 1");
				System.out.println("All records deleted.");
			}
			
			//INSERT
			
			for (Contact c : contacts) {
				StringBuilder query = new StringBuilder();
				
				
				
				query.append("INSERT INTO rubrica (id, nome, cognome, telefono, email, note) VALUES (null, '");
				
				query.append(c.getFirstName()).append("', '");
				query.append(c.getLastName()).append("', '");
				query.append(c.getPhoneNumber()).append("', '");
				query.append(c.getEmail()).append("', '");
				query.append(c.getNotes()).append("'");
				
				query.append(")");
				
				statement.executeUpdate(query.toString());
				
				System.out.println("The following contact has been added into the database : ");
				System.out.println(c.toString());
				System.out.println("------------------------------");
			}
			
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
	
	public String toString() {
		int i=0;
		StringBuilder sb = new StringBuilder();
		for(Contact c : contacts) {		
			sb.append("\nIndex : ");
			sb.append(i);
			sb.append("\n\n");
			sb.append(c.toString());
			sb.append("--------------------------\n");
		}
		
		return sb.toString();
	}
	
	public String toString(String orderBy) {
		List<Contact> orderedContacts = new ArrayList<>();
		 // Copy di contacts per VISUALIZZARLI ordinati ma non ordinarli effettivamente
		if(contacts.size()>0) {
			if(orderBy.equals("nome")) {
				orderedContacts.add(contacts.get(0));
				for(int i=1, j=0; i<contacts.size(); i++, j++) {
					if(contacts.get(i).compareToByName(orderedContacts.get(j)) > 0) {
						orderedContacts.add(contacts.get(i));
					}else {
						// element in ordered contacts is equals or less than current
						if(j == 0) {
							orderedContacts.add(j, contacts.get(i));
						}else {
							int cont = 1;
							boolean gotIt = false;
							while(!gotIt && (j-cont) >= 0) {
								int result = contacts.get(i).compareToByName(orderedContacts.get(j-cont));
								if(result > 0) {
									orderedContacts.add(j-cont + 1, contacts.get(i));
									gotIt = true;
								}else {
									if(j-cont == 0) {
										orderedContacts.add(0, contacts.get(i));
									}
								}
								cont++;
							}
						}
						
					}
				}
			} else {
				orderedContacts.add(contacts.get(0));
				for(int i=1, j=0; i<contacts.size(); i++, j++) {
					if(contacts.get(i).compareToBySurname(orderedContacts.get(j)) > 0) {
						orderedContacts.add(contacts.get(i));
					}else {
						// element in ordered contacts is equals or less than current
						if(j == 0) {
							orderedContacts.add(j, contacts.get(i));
						}else {
							int cont = 1;
							boolean gotIt = false;
							while(!gotIt && (j-cont) >= 0) {
								int result = contacts.get(i).compareToBySurname(orderedContacts.get(j-cont));
								if(result > 0) {
									orderedContacts.add(j-cont + 1, contacts.get(i));
									gotIt = true;
								}else {
									if(j-cont == 0) {
										orderedContacts.add(0, contacts.get(i));
									}
								}
								cont++;
							}
						}
						
					}
				}
			}
		}
		String output = "";
		for(Contact c : orderedContacts) {
			output += c.toString();
		}
		return output;
	}

	public boolean findContact(Contact c) {
		for(Contact contact : contacts) {
			if(contact.equals(c)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Contact> findContactByName(String name) {
		List<Contact> conts = new ArrayList<>();
		for(Contact c : contacts) {
			if(c.getFirstName().contains(name)) {
				conts.add(c);
			}
		}
		return conts;
	}
	
	public List<Contact> findContactByName(String name, String surname) {
		List<Contact> conts = new ArrayList<>();
		for(Contact c : contacts) {
			if(c.getLastName().contains(surname) && c.getFirstName().contains(name)) {
				conts.add(c);
			}
		}
		return conts;
	}
	
	public List<Contact> findContactBySurname(String surname) {
		List<Contact> conts = new ArrayList<>();
		for(Contact c : contacts) {
			if(c.getLastName().contains(surname)) {
				conts.add(c);
			}
		}
		return conts;
	}
	
	public List<Contact> findContactByPhone(String phone) {
		List<Contact> conts = new ArrayList<>();
		for(Contact c : contacts) {
			if(c.getPhoneNumber().equals(phone)) {
				conts.add(c);
			}
		}
		return conts;
	}
	
	public List<Contact> findContactByEmail(String email) {
		List<Contact> conts = new ArrayList<>();
		for(Contact c : contacts) {
			if(c.getEmail().equals(email)) {
				conts.add(c);
			}
		}
		return conts;
	}
	
	public List<Contact> findDuplicates() {
		List<Contact> dups = new ArrayList<>();
		List<Contact> parallel = new ArrayList<>();
		
		for(Contact c : contacts) {
			boolean found = false;
			for(Contact p : parallel) {
				if(p.equals(c)) {
					if(!dups.contains(p)) {
						dups.add(p);
					}
					found = true;
					break;
				}
			}
			if(found) {
				
				dups.add(c);
			}else {
				parallel.add(c);
			}
		}
		
		return dups;
	}
	
	// Da mettere se possibile scelta di quale dup tenere in futuro
	public void mergeDuplicates() {
		List<Contact> dups = findDuplicates();
		
		for(Contact dup : dups) {
			int counter = 0;
			for(Contact c : contacts) {
				if(c.equals(dup)) {
					counter++;
				}
			}
			for(int i=0;i<counter-1;i++) {
				contacts.remove(dup);
			}
		}		
		
	}
}
