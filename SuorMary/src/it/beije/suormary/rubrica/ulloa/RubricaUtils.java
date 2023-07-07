package it.beije.suormary.rubrica.ulloa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class RubricaUtils {
	private static final String ID_FIELD = "ID";
	private static final String SURNAME_FIELD = "COGNOME";
	private static final String NAME_FIELD = "NOME";
	private static final String PHONE_NUMBER_FIELD = "TELEFONO";
	private static final String EMAIL_FIELD = "EMAIL";
	private static final String NOTE_FIELD = "NOTE";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "cereza");
	}
	
	public static Configuration getConfiguration() {
		Configuration configuration = new Configuration().configure() // "/hibernate.cfg.xml"
				.addAnnotatedClass(Contact.class);
		return configuration;
	}
	
	private String escapeSpecialCharacters(String input) {
	    if (input == null) {
	        return null;
	    }
	    return input.replace("'", "\\'");
	}
	
	public List<Contact> loadRubricaFromCSV(String pathFile, String separator) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		List<Contact> contatti = null;
		try {
			File file = new File(pathFile);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			contatti = new ArrayList<Contact>();
			Map<String, Integer> columnMap = new HashMap<>();
			Contact contatto = null;
			
			//Costrutti per le righe dei valori
			String line = null;
			String[] fields = null;
			StringBuilder stringBuilder = new StringBuilder();
	        int index = -1;
			
			//Costrutti e gestione dell'intestazione
	        String headerLine = bufferedReader.readLine();
	        String[] headers = headerLine.split(separator);
	        
	        //Si riempie la mappa per chiave (nome campo) e valore (l'indice)
	        for (int i = 0; i < headers.length; i++) {
                columnMap.put(headers[i], i);
            }
	        
	        
			while (bufferedReader.ready()) {
				//legge una riga di testo dal file di input
				line = bufferedReader.readLine();
				//l'ultimo campo della riga non viene letto correttamente quando il suo valore è null.
				//indicando -1 nello split manteniamo anche gli elementi vuoti alla fine della riga
				fields = line.split(separator,-1);
				
				if (fields.length == headers.length) {
					contatto = new Contact();
		            
					if (columnMap.containsKey(SURNAME_FIELD)) {
	                    index = columnMap.get(SURNAME_FIELD);
	                    stringBuilder.setLength(0);
	                    stringBuilder.append(fields[index].trim());
	                    contatto.setSurname(stringBuilder.toString());
	                }
					
					if (columnMap.containsKey(NAME_FIELD)) {
	                    index = columnMap.get(NAME_FIELD);
	                    stringBuilder.setLength(0);
	                    stringBuilder.append(fields[index].trim());
	                    contatto.setName(stringBuilder.toString());
	                }
					
					if (columnMap.containsKey(PHONE_NUMBER_FIELD)) {
	                    index = columnMap.get(PHONE_NUMBER_FIELD);
	                    stringBuilder.setLength(0);
	                    stringBuilder.append(fields[index].trim());
	                    contatto.setPhoneNumber(stringBuilder.toString());
	                }
					
					if (columnMap.containsKey(EMAIL_FIELD)) {
	                    index = columnMap.get(EMAIL_FIELD);
	                    stringBuilder.setLength(0);
	                    stringBuilder.append(fields[index].trim());
	                    contatto.setEmail(stringBuilder.toString());
	                }
					
					if (columnMap.containsKey(NOTE_FIELD)) {
	                    index = columnMap.get(NOTE_FIELD);
	                    stringBuilder.setLength(0);
	                    stringBuilder.append(fields[index].trim());
	                    contatto.setNote(stringBuilder.toString());
	                }
		            contatti.add(contatto);
				} else {
	                System.out.println("Riga non valida: " + line);
	            }
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("rows number: " + contatti.size());
		return contatti;
	}
	
	public List<Contact> loadRubricaFromJDBC() {
		Connection connection = null;
		Statement statement = null;
		List<Contact> contatti = null;
		
		try {
			contatti = new ArrayList<Contact>();
			Contact contatto = null;
			
			connection = RubricaUtils.getConnection();
			
			statement = connection.createStatement();
			//System.out.println("connection open? " + !connection.isClosed());
			
			//SELECT
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			
			while (rs.next()) {
				contatto = new Contact();
				//rs.getInt("id")
				contatto.setId(rs.getInt(ID_FIELD));
				contatto.setSurname(rs.getString(SURNAME_FIELD));
				contatto.setName(rs.getString(NAME_FIELD));
				contatto.setPhoneNumber(rs.getString(PHONE_NUMBER_FIELD));
				contatto.setEmail(rs.getString(EMAIL_FIELD));
				contatto.setNote(rs.getString(NOTE_FIELD));
				contatti.add(contatto);
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
		return contatti;
	}
	
	public List<Contact> loadRubricaFromHBM(){
		Session session = null;
		
		List<Contact> contatti = null;
		
		try {
			/*Configuration configuration = RubricaUtils.getConfiguration();
			SessionFactory factory = configuration.buildSessionFactory();
			session = factory.openSession();*/
			session = HBMsessionFactory.openSession();
			//Transaction transaction = session.beginTransaction();
			
			//SELECT HQL
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
			contatti = query.getResultList();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatti;
	}
	
	public List<Contact> loadRubricaFromJPA(){
		EntityManager entityManager = null;
		
		List<Contact> contatti = null;
		
		try {
			/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			EntityManager entityManager = entityManagerFactory.createEntityManager();*/
			entityManager = JPAentityManager.getEntityManager();
			//EntityTransaction transaction = entityManager.getTransaction();
			//transaction.begin();
			
			//SELECT JPQL
			javax.persistence.Query query = entityManager.createQuery("SELECT c from Contact as c"); //SELECT * FROM rubrica
			contatti = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatti;
	}
	
	public List<Contact> loadRubricaFromXML(String pathFile) {
		List<Contact> contacts = null;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(pathFile);

			Element docEl = document.getDocumentElement();

			List<Element> elements = getChildElements(docEl);

			contacts = new ArrayList<Contact>();
			
			Contact c = null;
			
			List<Element> els = null;

			for (Element el : elements) {
				
				els = getChildElements(el);
				
				c = new Contact();

				for (Element e : els) {
					switch (e.getTagName().toUpperCase()) {
						case NAME_FIELD: c.setName(e.getTextContent());
							break;
						case SURNAME_FIELD: c.setSurname(e.getTextContent());
							break;
						case PHONE_NUMBER_FIELD: c.setPhoneNumber(e.getTextContent());
							break;
						case EMAIL_FIELD: c.setEmail(e.getTextContent());
							break;
						case NOTE_FIELD: c.setNote(e.getTextContent());
							break;
						default: System.out.println("TagName non riconosciuto! " + e.getTagName());
							break;
					}
				}
				contacts.add(c);
			}
		} catch (ParserConfigurationException pEx) {
			pEx.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contacts;
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
	
	public void writeRubricaCSV(List<Contact> contatti, String pathFile, String separator) {
		FileWriter fileWriter = null;
		try {
			File file = new File(pathFile);
			boolean append = file.exists();
			
			fileWriter = new FileWriter(pathFile,append);
			
			if(!append) {
				//intestazione
				fileWriter.write(SURNAME_FIELD);
				fileWriter.write(separator);
				fileWriter.write(NAME_FIELD);
				fileWriter.write(separator);
				fileWriter.write(PHONE_NUMBER_FIELD);
				fileWriter.write(separator);
				fileWriter.write(EMAIL_FIELD);
				fileWriter.write(separator);
				fileWriter.write(NOTE_FIELD);
				fileWriter.write('\n');
			}
			
			for (Contact contatto : contatti) {
				if(contatto!=null) {
					fileWriter.write(contatto.getSurname());
					fileWriter.write(separator);
					fileWriter.write(contatto.getName());
					fileWriter.write(separator);
					fileWriter.write(contatto.getPhoneNumber());
					fileWriter.write(separator);
					fileWriter.write(contatto.getEmail());
					fileWriter.write(separator);
					fileWriter.write(contatto.getNote());
					fileWriter.write('\n');
					fileWriter.flush();
				} else {
					System.out.println("Contatto mancante");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeRubricaJDBC(List<Contact> contatti) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = RubricaUtils.getConnection();

			//System.out.println("connection open? " + !connection.isClosed());
			StringBuilder query = new StringBuilder("INSERT INTO rubrica (`");
			query.append(SURNAME_FIELD).append("`, `")
				 .append(NAME_FIELD).append("`, `")
				 .append(PHONE_NUMBER_FIELD).append("`, `")
				 .append(EMAIL_FIELD).append("`, `")
				 .append(NOTE_FIELD)
				 .append("`) VALUES (?, ?, ?, ?, ?)");
			preparedStatement = connection.prepareStatement(query.toString());
			
			for (Contact contatto : contatti) {
				if(contatto!=null) {
					preparedStatement.setString(1, contatto.getSurname());
					preparedStatement.setString(2, contatto.getName());
					preparedStatement.setString(3, contatto.getPhoneNumber());
					preparedStatement.setString(4, contatto.getEmail());
					preparedStatement.setString(5, contatto.getNote());
					
					try {
						preparedStatement.execute();
					} catch (SQLSyntaxErrorException e) {
						System.out.println("Query non valida: " + query);
					}
					
				} else {
					System.out.println("Contatto mancante");
				}
			}
			
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
	}	

	public void writeRubricaHBM(List<Contact> contatti) {
		Session session = null;
		
		try {
			/*Configuration configuration = RubricaUtils.getConfiguration();
			SessionFactory factory = configuration.buildSessionFactory();
			session = factory.openSession();*/
			session = HBMsessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Contact contact =  new Contact();
			
			for (Contact contatto : contatti) {
				if(contatto!=null) {
					try {
						contact.setSurname(contatto.getSurname());
						contact.setName(contatto.getName());
						contact.setPhoneNumber(contatto.getPhoneNumber());
						contact.setEmail(contatto.getEmail());
						contact.setNote(contatto.getNote());
						session.save(contact);
						transaction.commit(); 
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Insert non valido: " + contatto.toString());
						//transaction.rollback();
					} 
					
				} else {
					System.out.println("Contatto mancante");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeRubricaJPA(List<Contact> contatti) {
		EntityManager entityManager = null;
		
		try {
			/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			EntityManager entityManager = entityManagerFactory.createEntityManager();*/
			entityManager = JPAentityManager.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();;
			
			Contact contact =  new Contact();
			for (Contact contatto : contatti) {
				if(contatto!=null) {
					transaction.begin();
					try {
						contact.setSurname(contatto.getSurname());
						contact.setName(contatto.getName());
						contact.setPhoneNumber(contatto.getPhoneNumber());
						contact.setEmail(contatto.getEmail());
						contact.setNote(contatto.getNote());
						transaction.commit();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Insert non valido: " + contatto.toString());
						transaction.rollback();
					} 
					
				} else {
					System.out.println("Contatto mancante");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeRubricaXML(List<Contact> contatti, String pathFile) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document;
			File file = new File(pathFile);

			Element rootElement;
			
			if(file.exists()){
                document = documentBuilder.parse(file);
                rootElement = document.getDocumentElement();
			} else {
                document = documentBuilder.newDocument();
                rootElement = document.createElement("rubrica");
                document.appendChild(rootElement);
			}
			
			Element contact = null;
			
			for (Contact c : contatti) {
				
				if (c != null) {
					contact = document.createElement("contatto");
					
					if (c.getName() != null) {
						Element name = document.createElement(NAME_FIELD);
						name.setTextContent(c.getName());
						contact.appendChild(name);
					}
					if (c.getSurname() != null) {
						Element surname = document.createElement(SURNAME_FIELD);
						surname.setTextContent(c.getSurname());
						contact.appendChild(surname);
					}
					if (c.getPhoneNumber() != null) {
						Element phoneNumber = document.createElement(PHONE_NUMBER_FIELD);
						phoneNumber.setTextContent(c.getPhoneNumber());
						contact.appendChild(phoneNumber);
					}
					if (c.getEmail() != null) {
						Element email = document.createElement(EMAIL_FIELD);
						email.setTextContent(c.getEmail());
						contact.appendChild(email);
					}
					if (c.getNote() != null) {
						Element note = document.createElement(NOTE_FIELD);
						note.setTextContent(c.getNote());
						contact.appendChild(note);
					}

					rootElement.appendChild(contact);
				}else {
                    System.out.println("Contatto mancante");
                }
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(document);

			StreamResult result = new StreamResult(new File(pathFile));

			StreamResult syso = new StreamResult(System.out);

			transformer.transform(source, result);

			transformer.transform(source, syso);

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
	
	public void addContactJDBC(Contact contatto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		try {
			connection = RubricaUtils.getConnection();
			//System.out.println("connection open? " + !connection.isClosed());
			
			StringBuilder query = new StringBuilder("INSERT INTO rubrica (`");
			query.append(SURNAME_FIELD).append("`, `")
						 .append(NAME_FIELD).append("`, `")
						 .append(PHONE_NUMBER_FIELD).append("`, `")
						 .append(EMAIL_FIELD).append("`, `")
						 .append(NOTE_FIELD)
						 .append("`) VALUES (?, ?, ?, ?, ?)");
			preparedStatement = connection.prepareStatement(query.toString());
			
			if(contatto!=null) {
				preparedStatement.setString(1, contatto.getSurname());
				preparedStatement.setString(2, contatto.getName());
				preparedStatement.setString(3, contatto.getPhoneNumber());
				preparedStatement.setString(4, contatto.getEmail());
				preparedStatement.setString(5, contatto.getNote());
				
				try {
					preparedStatement.execute();
					} catch (SQLSyntaxErrorException e) {
						System.out.println("Query non valida: " + query);
					}
				
				} else {
					System.out.println("Contatto mancante");
				}
			
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
	}
	
	public void addContactHBM(Contact contatto) {
		Session session = null;
		
		try {
			/*Configuration configuration = RubricaUtils.getConfiguration();
			SessionFactory factory = configuration.buildSessionFactory();
			session = factory.openSession();*/
			session = HBMsessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Contact contact = new Contact();
			
			if(contatto!=null) {
				try {
					contact.setSurname(contatto.getSurname());
					contact.setName(contatto.getName());
					contact.setPhoneNumber(contatto.getPhoneNumber());
					contact.setEmail(contatto.getEmail());
					contact.setNote(contatto.getNote());
					session.save(contact);
					transaction.commit();
				} catch (Exception e) {
					System.out.println("Insert non valido: " + contatto.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 
					
			} else {
				System.out.println("Contatto mancante");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addContactJPA(Contact contatto) {
		EntityManager entityManager = null;
		
		try {
			/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			EntityManager entityManager = entityManagerFactory.createEntityManager();*/
			entityManager = JPAentityManager.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			Contact contact = new Contact();
			
			if(contatto!=null) {
				try {
					contact.setSurname(contatto.getSurname());
					contact.setName(contatto.getName());
					contact.setPhoneNumber(contatto.getPhoneNumber());
					contact.setEmail(contatto.getEmail());
					contact.setNote(contatto.getNote());
					transaction.commit();
				} catch (Exception e) {
					System.out.println("Insert non valido: " + contatto.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 
					
			} else {
				System.out.println("Contatto mancante");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateContactJDBC(int id, Contact contatto) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = RubricaUtils.getConnection();
			
			statement = connection.createStatement();
			//System.out.println("connection open? " + !connection.isClosed());
			StringBuilder query = new StringBuilder();
			StringBuilder columnsValues = new StringBuilder("UPDATE rubrica set ");
			int nRecord = -1;
			
			if(contatto!=null) {
				query.setLength(0);
				query.append(columnsValues)
					.append(SURNAME_FIELD).append(" = '")
					.append(escapeSpecialCharacters(contatto.getSurname())).append("', ")
					.append(NAME_FIELD).append(" = '")
					.append(escapeSpecialCharacters(contatto.getName())).append("', ")
					.append(PHONE_NUMBER_FIELD).append(" = '")
					.append(escapeSpecialCharacters(contatto.getPhoneNumber())).append("', ")
					.append(EMAIL_FIELD).append(" = '")
					.append(escapeSpecialCharacters(contatto.getEmail())).append("', ")
					.append(NOTE_FIELD).append(" = '")
					.append(escapeSpecialCharacters(contatto.getNote()))
					.append("' WHERE ").append(ID_FIELD).append(" = ").append(id);
					
					try {
						nRecord = statement.executeUpdate(query.toString());
						if (nRecord !=1) {
							System.out.println("Query non aggiornata: " + query);
						}
					} catch (SQLSyntaxErrorException e) {
						System.out.println("Query non valida: " + query);
					}
					
				} else {
					System.out.println("Contatto mancante");
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

	public void updateContactHBM(int id, Contact contatto) {
		Session session = null;
		
		try {
			/*Configuration configuration = RubricaUtils.getConfiguration();
			SessionFactory factory = configuration.buildSessionFactory();
			session = factory.openSession();*/
			session = HBMsessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			StringBuilder hql = new StringBuilder("UPDATE Contact set ");
			hql.append(SURNAME_FIELD).append(" = :").append(SURNAME_FIELD).append(", ")
			   .append(NAME_FIELD).append(" = :").append(NAME_FIELD).append(", ")
			   .append(PHONE_NUMBER_FIELD).append(" = :").append(PHONE_NUMBER_FIELD).append(", ")
			   .append(EMAIL_FIELD).append(" = :").append(EMAIL_FIELD).append(", ")
			   .append(NOTE_FIELD).append(" = :").append(NOTE_FIELD)
			   .append(" WHERE ").append(ID_FIELD).append(" = ").append(id);
			Query query = session.createQuery(hql.toString());
			
			if(contatto!=null) {
				query.setParameter(SURNAME_FIELD, contatto.getSurname());
				query.setParameter(NAME_FIELD, contatto.getName());
				query.setParameter(PHONE_NUMBER_FIELD, contatto.getPhoneNumber());
				query.setParameter(EMAIL_FIELD, contatto.getEmail());
				query.setParameter(NOTE_FIELD, contatto.getNote());
				
					try {
						int nRecord = query.executeUpdate();
						transaction.commit();
						if (nRecord !=1) {
							System.out.println("Query non aggiornata: " + query);
						}
					} catch (Exception e) {
						System.out.println("Query non valida: " + query);
						transaction.rollback();
						throw e; //rilancia eccezione al catch più esterno
					} 
					
				} else {
					System.out.println("Contatto mancante");
				}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateContactJPA(int id, Contact contatto) {
		EntityManager entityManager = null;
		
		try {
			/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			EntityManager entityManager = entityManagerFactory.createEntityManager();*/
			entityManager = JPAentityManager.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			Contact contact = null;
			if(contatto!=null) {
				try {
					contact = entityManager.find(Contact.class, id);
					contact.setSurname(contatto.getSurname());
					contact.setName(contatto.getName());
					contact.setPhoneNumber(contatto.getPhoneNumber());
					contact.setEmail(contatto.getEmail());
					contact.setNote(contatto.getNote());
				
					transaction.commit();
					} catch (Exception e) {
						System.out.println("Contatto id" + id +" non aggiornato: " + contatto.toString());
						transaction.rollback();
						throw e; //rilancia eccezione al catch più esterno
					} 
					
				} else {
					System.out.println("Contatto mancante");
				}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteContactJDBC(int id) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = RubricaUtils.getConnection();
			
			statement = connection.createStatement();
			//System.out.println("connection open? " + !connection.isClosed());
			StringBuilder query = new StringBuilder("DELETE FROM rubrica WHERE id = ");
			query.append(id);
			
			int nRecord = -1;
			try {
				nRecord = statement.executeUpdate(query.toString());
				if (nRecord !=1) {
					System.out.println("Query non aggiornata: " + query);
					}
			} catch (SQLSyntaxErrorException e) {
				System.out.println("Query non valida: " + query);
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
	
	public void deleteContactHBM(int id) {
		Session session = null;
		
		try {
			/*Configuration configuration = RubricaUtils.getConfiguration();
			SessionFactory factory = configuration.buildSessionFactory();
			session = factory.openSession();*/
			session = HBMsessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			
			StringBuilder hql = new StringBuilder("DELETE FROM Contact WHERE id = ");
			hql.append(id);
			Query query = session.createQuery(hql.toString());
				
			try {
				int nRecord = query.executeUpdate();
				transaction.commit();
				if (nRecord !=1) {
					System.out.println("Query non eliminata: " + query);
					}
				} catch (Exception e) {
					System.out.println("Query non valida: " + query);
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteContactJPA(int id) {
		EntityManager entityManager = null;
		
		try {
			/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			EntityManager entityManager = entityManagerFactory.createEntityManager();*/
			entityManager = JPAentityManager.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			Contact contact = null;
			try {
				contact = entityManager.find(Contact.class, id);
				entityManager.remove(contact);
				
				transaction.commit();
				} catch (Exception e) {
					System.out.println("Contatto id" + id +" non eliminato: " + contact.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteContactJDBC(List<Contact> contatti) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = RubricaUtils.getConnection();
			
			statement = connection.createStatement();
			//System.out.println("connection open? " + !connection.isClosed());
			StringBuilder query = new StringBuilder();
			StringBuilder columnsValues = new StringBuilder("DELETE FROM rubrica WHERE id = ");
			int nRecord = -1;
			
			for (Contact contatto : contatti) {
				if(contatto!=null) {
					query.setLength(0);
					query.append(columnsValues).append(contatto.getId());
					
					try {
						nRecord = statement.executeUpdate(query.toString());
						if (nRecord !=1) {
							System.out.println("Contatto non eliminato: " + query);
						}
					} catch (SQLSyntaxErrorException e) {
						System.out.println("Query non valida: " + query);
					}
					
				} else {
					System.out.println("Contatto mancante");
				}
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
	
	public void deleteContactHBM(List<Contact> contatti) {
		Session session = null;
		
		try {
			/*Configuration configuration = RubricaUtils.getConfiguration();
			SessionFactory factory = configuration.buildSessionFactory();
			session = factory.openSession();*/
			session = HBMsessionFactory.openSession();
						
			StringBuilder hql = new StringBuilder("DELETE FROM Contact WHERE id = :id_number");
			Query<Contact> query = session.createQuery(hql.toString());
			Transaction transaction;
			int nRecord = -1;
			
			for (Contact contatto : contatti) {
				transaction = session.beginTransaction();
				if(contatto!=null) {
					query.setParameter("id_number", contatto.getId());					
					try {
						nRecord = query.executeUpdate();
						transaction.commit();
						if (nRecord !=1) {
							System.out.println("Contatto non eliminato: " + query);
						}
					} catch (Exception e) {
						System.out.println("Query non valida: " + query);
						transaction.rollback();
						throw e; //rilancia eccezione al catch più esterno
					} 
					
				} else {
					System.out.println("Contatto mancante");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteContactJPA(List<Contact> contatti) {
		EntityManager entityManager = null;
		
		try {
			/*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			EntityManager entityManager = entityManagerFactory.createEntityManager();*/
			entityManager = JPAentityManager.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			Contact contact = null;
			
			for (Contact contatto : contatti) {
				transaction.begin();
				if(contatto!=null) {			
					try {
						contact = entityManager.find(Contact.class, contatto.getId());
						entityManager.remove(contact);
						
						transaction.commit();
					} catch (Exception e) {
						System.out.println("Contatto id" + contatto.getId() +" non eliminato: " + contact.toString());
						transaction.rollback();
						throw e; //rilancia eccezione al catch più esterno
					} 
					
				} else {
					System.out.println("Contatto mancante");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void printContactList(List<Contact> contacts) {	    
	    if (contacts.isEmpty()) {
	        System.out.println("La lista dei contatti è vuota.");
	    } else {
	        System.out.println("Lista dei contatti:");
	        for (Contact contact : contacts) {
//	            System.out.println("Nome: " + contact.getName()); 
//	            System.out.println("Cognome: " + contact.getSurname()); 
//	            System.out.println("Telefono: " + contact.getPhoneNumber());
//	            System.out.println("Email: " + contact.getEmail());
//	            System.out.println("Note: " + contact.getNote());
//	            System.out.println();
	        	System.out.println(contact.toString()); 
	        }
	    }
	}
	
	//testing
	public static void main(String[] args)  {
//		RubricaUtils ru = new RubricaUtils();
//		String pathFile = "/Users/Padawan/git/file/rubrica.csv";
//		String pathFileW = "/Users/Padawan/git/file/rubrica_scrittura.csv";
//		
//		String pathFileXML = "/Users/Padawan/git/file/rubrica.xml";
//		String pathFileXMLW = "/Users/Padawan/git/file/rubrica_scrittura.xml";
//		
//		String pathFileDynamic = "/Users/Padawan/git/file/rubrica_dinamico_v2.csv";
//		
//		String separator = ";";
//		
//		List<Contact> contatti = ru.loadRubricaFromCSV(pathFile, separator);
//		ru.writeRubricaCSV(contatti, pathFileW, separator);
//		
//		List<Contact> contattiXML = ru.loadRubricaFromXML(pathFileXML);
//		ru.writeRubricaXML(contattiXML, pathFileXMLW);
//		
//		System.out.println();
//		
//		List<Contact> contattiDynamic = ru.loadRubricaFromCSV(pathFileDynamic, separator);
//		RubricaUtils.printContactList(contattiDynamic);
		
		
		/*List<Contact> contatti = ru.loadRubricaFromJDBC();
		RubricaUtils.printContactList(contatti)*/;

		//ru.writeRubricaJDBC(contatti);
	}

}
