package it.beije.suormary.rubrica.ceccarelli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.suormary.rubrica.Contact;

public class DbWithHBM {
	
	public Configuration configuration;
	public SessionFactory factory;
	public Session session;
	public Transaction transaction;
	public DocumentBuilderFactory documentBuilderFactory;
	public DocumentBuilder documentBuilder;
	public Document document;
	
	// check connection
	public boolean connectionCheck() {
		boolean check = true;
		try {
		//creazione configurazione
			
		configuration = new Configuration().configure(new File("./src/hibernate.cfg.xml"))
				.addAnnotatedClass(Contact.class);
		
		//creazione sessione
		factory = configuration.buildSessionFactory();
		//inizializzazione sessione
		session = null;
		
		}catch (Exception e) {
			check = false;
			e.printStackTrace();
		}
		return check;
	}
	
	//list contact from DB
	public List<Contact> listContactHBM() {
		List<Contact> contacts = null;
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				//transaction = session.beginTransaction();
				
				//SELECT HQL
				Query<Contact> query = session.createQuery("SELECT c from Contact as c");
				contacts = query.getResultList();
				
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return contacts;
	}
	
	// search contact with name
	public List<Contact> searchContactsName(String name){
		List<Contact> selected= new ArrayList<Contact>();
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.name = :nome");
				query.setParameter("nome", name);
				selected = query.getResultList();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return selected;
	}
	
	// search contact with surnname
		public List<Contact> searchContactsSurname(String surname){
			List<Contact> selected= new ArrayList<Contact>();
			if(connectionCheck()) {
				try {
					session = factory.openSession();
					Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.surname = :cognome");
					query.setParameter("cognome", surname);
					selected = query.getResultList();
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					session.close();
				}
			}
			return selected;
		}
		
	// search contact with name and surname
	public List<Contact> searchContactsNameSurname(String name, String surname){
				List<Contact> selected= new ArrayList<Contact>();
				if(connectionCheck()) {
					try {
						session = factory.openSession();
						Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.name = :nome and c.surname = :cognome");
						query.setParameter("nome", name);
						query.setParameter("cognome", surname);
						
						selected = query.getResultList();
					}catch (Exception e) {
						e.printStackTrace();
					} finally {
						session.close();
					}
				}
				return selected;
			}
	
	//inserti contact
	public void insertContacts(Contact contact) {
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				transaction = session.beginTransaction();
				session.save(contact);
				transaction.commit();
				System.out.println("Contatto/i inserito/i");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
//	
	//update contact
	public void updateContact(Contact contact) {
		System.out.println(contact.getId());
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				transaction = session.beginTransaction();
				session.save(contact);
				transaction.commit();
				System.out.println("Contatto modificato");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
	
	//delete contact
	public void deleteContact(Contact contact) {
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				transaction = session.beginTransaction();
				session.delete(contact);
				transaction.commit();
				System.out.println("Contatto eliminato");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
	
	//import data from XML
		public List<Contact> loadRubricaFromXML(String path)  {
			List<Contact> contacts = null;
			try {
				documentBuilderFactory = DocumentBuilderFactory.newInstance();
				documentBuilder = documentBuilderFactory.newDocumentBuilder();
				document = documentBuilder.parse(path);
				
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
						case "id": c.setId(Integer.parseInt(e.getTextContent()));
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
				String[] confronto = {"nome","cognome","telefono","email","note"};
				for(int i=0;i<rows.size();i++) {
					if(i==0) {
						for(String s : confronto) {
							if(rows.get(i).contains(s)) {
								continue;
							}
						}
					}
					
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
					//System.out.println(cr.getId());
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
					id.setTextContent(String.valueOf(c.getId()));
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
					// transform document into xml
					transformer.transform(source, result);
			
			}catch (TransformerConfigurationException tcEx) {
				tcEx.printStackTrace();
			}catch (ParserConfigurationException pEx) {
				pEx.printStackTrace();
			} catch (TransformerException tEx) {
				tEx.printStackTrace();
			}
		}
}
