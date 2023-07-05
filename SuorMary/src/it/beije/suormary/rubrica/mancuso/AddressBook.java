package it.beije.suormary.rubrica.mancuso;

import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class AddressBook {
	
	public List<Contact> contacts;
	
	public AddressBook() {
		contacts = new ArrayList<>();
	}

	public static String print(List<Contact> conts) {
		int i=0;
		StringBuilder sb = new StringBuilder();
		for(Contact c : conts) {		
			sb.append("\nIndex : ");
			sb.append(i);
			sb.append("\n\n");
			sb.append(c.toString());
			sb.append("--------------------------\n");
			i++;
		}
		
		return sb.toString();
	}
	
	public static Session getSession() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = factory.openSession();
		
		return session;
	}
	
	public List<Contact> loadAddressesFromCSV(String pathFile, String separator) throws IllegalArgumentException{
		
		File file = null;
		
		file = new File(pathFile);
		
		if(file.exists()) {
			if(!file.isDirectory()) {
				
				String filename = file.getName();

				
				if(filename.contains(".csv")) {
					
					List<Contact> newContacts = new ArrayList<Contact>();
					List<String[]> csv = CSVUtils.readCSV(file, separator);
					
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

	public List<Contact> loadAddressesFromXML(String pathFile) throws IllegalArgumentException{
		File file = new File(pathFile);
		String filename = file.getName();
		List<Contact> newContacts = null;
		if(file.exists()) {
			if(!file.isDirectory()) {
				if(filename.contains(".xml")) {
					try {					
						Document document = XMLUtils.getDocument(pathFile);
				
						Element rootElement = document.getDocumentElement();
						
						List<Element> elements = XMLUtils.getChildrenElements(rootElement);	
						newContacts = new ArrayList<>();
						List<Element> innerElements;
						Contact c = null;
						for(Element e : elements) {
							
							innerElements = XMLUtils.getChildrenElements(e);
							
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
		PreparedStatement preparedStatement = null;
		List<Contact> contacts = null;
		try {
			
			connection = JDBCUtils.getConnection();
			
			preparedStatement = connection.prepareStatement("SELECT * FROM rubrica");
			
			//SELECT
			ResultSet rs = preparedStatement.executeQuery();
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
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return contacts;
	}
	
	public List<Contact> loadAddressesHBM(){
		
		Session session = null;
		
		List<Contact> contacts = null;
		
		try {
			
			session = getSession();
			
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
			contacts = query.getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return contacts;
	}
	
	public void writeAddressBookCSV(String pathFile, String separator, boolean append) {
		
		
		StringBuilder sb = new StringBuilder();
		
			
		if(append) {
			File file = new File(pathFile);
				
			if(!file.exists()) {
				sb.append("NAME;SURNAME;PHONE;EMAIL;NOTES\n");
			}
		}else {
			sb.append("NAME;SURNAME;PHONE;EMAIL;NOTES\n");
		}		
		
		for (Contact contact : contacts) {
						
			if(contact.getFirstName() != null) {
				sb.append(contact.getFirstName());
			}else {
				sb.append("");
			}
			sb.append(separator);
			if(contact.getLastName() != null) {
				sb.append(contact.getLastName());
			}else {
				sb.append("");
			}
			sb.append(separator);
			if(contact.getPhoneNumber() != null) {
				sb.append(contact.getPhoneNumber());
			}else {
				sb.append("");
			}
			sb.append(separator);
			if(contact.getEmail() != null) {
				sb.append(contact.getEmail());
			}else {
				sb.append("");
			}
			sb.append(separator);
			if(contact.getNotes() != null) {
				sb.append(contact.getNotes());
			}else {
				sb.append("");
			}
			sb.append('\n');
		}
			
		CSVUtils.writeCSV(pathFile, separator, append, sb.toString());	
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
		
			Document document = XMLUtils.getDocument();
			
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
			
			XMLUtils.write(document, file);
	
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
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		try {
			
			connection = JDBCUtils.getConnection();
			
			
			if(overwrite) {
				System.out.println("Deleting all records ...");
				statement = connection.createStatement();
				int deleted = statement.executeUpdate("DELETE FROM rubrica WHERE 1");
				System.out.println(deleted + " records deleted.");
			}
			
			//INSERT
			preparedStatement = connection.prepareStatement("INSERT INTO rubrica (id, nome, cognome, telefono, email, note) VALUES (null, ?, ?, ?, ?, ?)");
			for (Contact c : contacts) {
				
				preparedStatement.setString(1, c.getFirstName());
				preparedStatement.setString(2, c.getLastName());
				preparedStatement.setString(3, c.getPhoneNumber());
				preparedStatement.setString(4, c.getEmail());
				preparedStatement.setString(5, c.getNotes());
				
				preparedStatement.execute();
				/*StringBuilder query = new StringBuilder();
				
				query.append("INSERT INTO rubrica (id, nome, cognome, telefono, email, note) VALUES (null, '");
				
				query.append(c.getFirstName()).append("', '");
				query.append(c.getLastName()).append("', '");
				query.append(c.getPhoneNumber()).append("', '");
				query.append(c.getEmail()).append("', '");
				query.append(c.getNotes()).append("'");
				
				query.append(")");
				
				statement.executeUpdate(query.toString());*/
				
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
				preparedStatement.close();
				connection.close();
			}catch (NullPointerException ex) {
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void writeAddressBookHBM(boolean overwrite) {
		Session session = null;
		
		try {
			
			session = getSession();
			
			
			
			if(overwrite) {
				Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
				List<Contact> conts = query.getResultList();
				for (Contact c : conts) {
					Transaction transaction = session.beginTransaction();
					session.delete(c);
					transaction.commit();
				}
			}
			
			for(Contact ct : contacts) {
				Transaction transaction = session.beginTransaction();
				session.save(ct);
				transaction.commit();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public String toString() {
		return print(contacts);
	}
	
	public List<Contact> orderByName(){
		List<Contact> orderedContacts = new ArrayList<>();
		
		if(contacts.size()>0) {
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
		}
		return orderedContacts;
	}
	
	public List<Contact> orderBySurname(){
		List<Contact> orderedContacts = new ArrayList<>();
		
		if(contacts.size()>0) {
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
		return orderedContacts;
	}
	
	public String toString(String orderBy) {
		List<Contact> orderedContacts = new ArrayList<>();
		 // Copy di contacts per VISUALIZZARLI ordinati ma non ordinarli effettivamente
		if(contacts.size()>0) {
			if(orderBy.equals("nome")) {
				orderedContacts = orderByName();
			} else {
				orderedContacts = orderBySurname();
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
			if(c.getFirstName().toLowerCase().contains(name.toLowerCase())) {
				conts.add(c);
			}
		}
		return conts;
	}
	
	public List<Contact> findContactByName(String name, String surname) {
		List<Contact> conts = new ArrayList<>();
		for(Contact c : contacts) {
			if(c.getLastName().toLowerCase().contains(surname.toLowerCase()) && c.getFirstName().toLowerCase().contains(name.toLowerCase())) {
				conts.add(c);
			}
		}
		return conts;
	}
	
	public List<Contact> findContactBySurname(String surname) {
		List<Contact> conts = new ArrayList<>();
		for(Contact c : contacts) {
			if(c.getLastName().toLowerCase().contains(surname.toLowerCase())) {
				conts.add(c);
			}
		}
		return conts;
	}
	
	public List<Contact> findContactByPhone(String phone) {
		phone = phone.trim();
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
			if(c.getEmail().toLowerCase().contains(email.toLowerCase())) {
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
	public void mergeDuplicates(Scanner input) {
		List<Contact> dups = findDuplicates();
		List<Contact> merged = new ArrayList<>();
		
		//Così non va bene perchè cicla di nuovo anche dopo che è stato mergiato il primo duplicato
		for(Contact dup : dups) {
			if(!merged.contains(dup)) {
				//int counter = 0;
				List<Contact> toDelete = new ArrayList<>();
				for(Contact c : contacts) {
					if(c.equals(dup)) {
						toDelete.add(c);
						//counter++;
					}
				}
				int index = -1;
				while(index<0 || index>=toDelete.size()) {
					System.out.println("\nSelezionare tra i seguenti l'indice del contatto da tenere in memoria: ");
					System.out.println(print(toDelete));
					String response = input.nextLine();
					try {
						index = Integer.valueOf(response);
					}catch(NumberFormatException ex) {
						System.out.println("Inserire un indice numerico.");
					}
				}
				for(int i=0;i<toDelete.size();i++) {
					merged.add(toDelete.get(i));
					if(i!=index) {
						contacts.remove(toDelete.get(i));	
					}
				}
			}
			
			/*for(int i=0;i<counter-1;i++) {
				contacts.remove(dup);
			}*/
		}		
		
	}

	public void addContactHBM(Contact c) {
		Session session = null;

		try {
			
			session = getSession();			
			session.save(c);
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	
	public void editContactHBM(Contact c, String name, String surname, String email, String phone, String notes) {
		
		Session session = null;

		try {
			
			session = getSession();
			c.setFirstName(name);
			c.setLastName(surname);
			c.setEmail(email);
			c.setPhoneNumber(phone);
			c.setNotes(notes);
			session.save(c);
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	
	public List<Contact> getContactByNameHBM(String name){
		Session session = null;
		List<Contact> conts = new ArrayList<Contact>();
		try {
			
			session = getSession();			
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c WHERE c.firstName LIKE :value");
			query.setParameter("value", name);
			conts = query.getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return conts;
	}
	
	public List<Contact> getContactBySurnameHBM(String surname){
		Session session = null;
		List<Contact> conts = new ArrayList<Contact>();
		try {
			
			session = getSession();			
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c WHERE c.lastName LIKE :value");
			query.setParameter("value", surname);
			conts = query.getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return conts;
	}
	
	public List<Contact> getContactByEmailHBM(String email){
		Session session = null;
		List<Contact> conts = new ArrayList<Contact>();
		try {
			
			session = getSession();			
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c WHERE c.email LIKE :value");
			query.setParameter("value", email);
			conts = query.getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return conts;
	}
}
