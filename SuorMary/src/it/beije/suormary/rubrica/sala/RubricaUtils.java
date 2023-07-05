package it.beije.suormary.rubrica.sala;

import java.util.List;
import java.util.Map;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RubricaUtils {
	private static final String COGNOME_FIELD="COGNOME";
	private static final String NOME_FIELD="NOME";
	private static final String TELEFONO_FIELD="TELEFONO";
	private static final String EMAIL_FIELD="EMAIL";
	private static final String NOTE_FIELD="NOTE";
	
	
	public static void main(String[] args) {
		
		
		//String pathFile = "/Users/Padawan/Desktop/fileAcademy/rubrica_dinamico_v2.csv";
		
		
		//String separator = ";";
		RubricaUtils ru = new RubricaUtils();
		
		
		
		/*List<Contact> contatti = ru.loadRubricaFromCSV(pathFile, separator);
		
		
		String pathFile1 = "/Users/Padawan/Desktop/fileAcademy/rubricaScritta.csv";
		
		ru.writeRubricaCSV(contatti, pathFile1, separator);
		
		try {
			List<Contact> c1 = ru.loadRubricaFromXML(pathFile1);
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		}
		
		*/
		
		//List<Contact> contatti = ru.loadRubricaDynamicFromCSV(pathFile, separator);
		
		
		
		/*
		List<Contact> co = ru.loadRubricaFromDB(nomeDB, account, password);
		//ru.writeRubricaDBInsert(c);
		
		for(Contact c : co) {
			if(c!=null) {
				System.out.println(c.toString());
			}
		}*/
		String ordinata="n";
		List<Contact> co = ru.loadRubricaFromDBOrdinata(ordinata);
		//ru.writeRubricaDBInsert(c);
		
		for(Contact c : co) {
			if(c!=null) {
				System.out.println(c.toString());
			}
		}
		
		//ru.writeFromDBDelete();
		//sostituito da un toString() nel bin
		//ru.stampaContatti(c);

	}
	
	public List<Contact> loadRubricaFromCSV(String pathFile, String separator) {
		FileReader fr = null;
		BufferedReader br = null;
		List<Contact> contatti=null;
		
		try {
			File file = new File(pathFile);
			System.out.println("exists? " + file.exists());
			
			fr = new FileReader(file);
			
			br = new BufferedReader(fr);
			contatti = new ArrayList<Contact>();
			Contact c = null;
			String riga = null;
			
			String [] campi =null;
			
			StringBuilder sb = new StringBuilder();
			
			br.readLine();
			
			while (br.ready()) {
				riga = br.readLine();
				campi=riga.split(separator);

				if(campi.length==5) {
					
					c=new Contact();
					
					sb.setLength(0);
					sb.append(campi[0].trim());
					c.setSurname(sb.toString());
					
					sb.setLength(0);
					sb.append(campi[1].trim());
					c.setName(sb.toString());
					
					sb.setLength(0);
					sb.append(campi[2].trim());
					c.setPhoneNumber(sb.toString());
					
					sb.setLength(0);
					sb.append(campi[3].trim());
					c.setEmail(sb.toString());
					
					sb.setLength(0);
					sb.append(campi[4].trim());
					c.setNote(sb.toString());
					
					contatti.add(c);
				} else {
				
					System.out.println("La seguente linea non è corretta: "+riga);
				}
				
				
				
			}
		}catch(FileNotFoundException fne) {
			fne.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally {
			try {
				br.close();
				fr.close();
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		
	return contatti;
		
	}

	//LETTURA DINAMICA DA CSV
	public List<Contact> loadRubricaDynamicFromCSV(String pathFile, String separator){
				
				FileReader fr=null;
				BufferedReader br = null;
				List<Contact> contatti=null;
				
				try {
					File file = new File(pathFile);
					System.out.println("exists? " + file.exists());
					
					fr = new FileReader(file);
					
					br = new BufferedReader(fr);
					
					contatti = new ArrayList<Contact>();
					Map<String, Integer> map = new HashMap<>();
					Contact c = null;
					
					//costrutti per le righe dei valori
					String line = null;
					String [] fields =null;
					StringBuilder sb = new StringBuilder();
					int index=-1;
					
					//costrutti e gestione dell'intestazione
					String headerLine = br.readLine();
					String [] headers = headerLine.split(separator);
					
					//così riempio la mappa per chiave (la stringa) e valore (l'indice)
					for(int i=0; i<headers.length; i++) {
						map.put(headers[i], i);
					}
	                
					
			        while (br.ready()) {
		                //legge una riga di testo dal file di input
		                
			        	line = br.readLine();
			        	
			        	//l'ultimo campo della riga non viene letto correttamente quando il suo valore è null
			        	//indicando -1 nello split, manteniamo anche gli elementi vuoti alla fine della riga
		                fields = line.split(separator, -1);
		                
		                //CONTROLLO PER VERIFICARE CHE LA LUNGHEZZA DEI DUE ARRAY SIA
			        	//LA STESSA, ALTRIMENTI GENERO UNA LINEA NON VALIDA
		                if(headers.length==fields.length) {
		                	c = new Contact();
			                
			                //controllo se nella mappa è presente la chiave (ovvero il campo cognome in questo caso)
			                if(map.containsKey(COGNOME_FIELD)) {
			                	//ottengo l'indice a partire dalla chiave
			                	index=map.get(COGNOME_FIELD);
			                	sb.setLength(0);
			                	//l'indice di prima viene usato per ottenere il valore nell'array ottenuto dalla riga
			                	//l'array dell'intestazione e quello delle linee avranno gli stessi campi nella stessa posizione
			                	sb.append(fields[index].trim());
			                	c.setSurname(sb.toString());
			                }
			                
			                if(map.containsKey(NOME_FIELD)) {
			                	index=map.get(NOME_FIELD);
			                	sb.setLength(0);
			                	sb.append(fields[index].trim());
			                	c.setName(sb.toString());
			                }
							
			                if(map.containsKey(EMAIL_FIELD)) {
			                	index=map.get(EMAIL_FIELD);
			                	sb.setLength(0);
			                	sb.append(fields[index].trim());
			                	c.setEmail(sb.toString());
			                }
			                
			                if(map.containsKey(TELEFONO_FIELD)) {
			                	index=map.get(TELEFONO_FIELD);
			                	sb.setLength(0);
			                	sb.append(fields[index].trim());
			                	c.setPhoneNumber(sb.toString());
			                }
							
			                if(map.containsKey(NOTE_FIELD)) {
			                	index=map.get(NOTE_FIELD);
			                	sb.setLength(0);
			                	sb.append(fields[index].trim());
			                	c.setNote(sb.toString());
			                }
								
								//DEVO AGGIUNGERE l'oggetto dopo averlo settato per poterlo avere nella mia lista che cotiene contatti
								contatti.add(c);
		                } else {
		                	//è buona norma tenere una traccia di tutte le linee che sono sbagliate
		                	System.out.println("riga non valida: "+line);
		                }
		           		
						
					}	
					
				} catch(FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				} finally {
					try {
						//prima si chiude il bufferreader
						br.close();
						//poi chiudo il filereader
						fr.close();
					} catch(IOException ioe) {
						ioe.printStackTrace();
					}
					
				}
				
				return contatti;
			}

	public List<Contact> loadRubricaFromXML(String pathFile) {
		List<Contact> contacts =null;
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
					
					switch (e.getTagName()) {
						case "nome": c.setName(e.getTextContent());
							break;
						case "cognome": c.setSurname(e.getTextContent());
							break;
						case "telefono": c.setPhoneNumber(e.getTextContent());
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
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException saxe) {
			saxe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(Exception e){
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
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "Arlabunakti");
	}
	
	//CONTIENE STATEMENT
	public List<Contact> loadRubricaFromDB(){
		
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
					.addAnnotatedClass(Contact.class);
			
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = null;
			List<Contact> contacts=null;
			
			try {
				session = factory.openSession();
			
				Transaction transaction = session.beginTransaction();
				
				Contact contact = null;
				
				Query<Contact> query=null;
				
				query = session.createQuery("SELECT c FROM Contact as c");
				
				
				contacts = query.getResultList();
							
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			
		return contacts;
	}
	
	public List<Contact> loadRubricaFromDBOrdinata(String ordine){
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = null;
		List<Contact> contacts=null;
		
		try {
			session = factory.openSession();
		
			Transaction transaction = session.beginTransaction();
			
			Contact contact = null;
			
			Query<Contact> query=null;
			if(ordine.equalsIgnoreCase("n")) {
				query = session.createQuery("SELECT c FROM Contact as c ORDER BY c.name");
			} else if(ordine.equalsIgnoreCase("c")) {
				query = session.createQuery("SELECT c FROM Contact as c ORDER BY c.surname");
			} else {
				query = session.createQuery("SELECT c FROM Contact as c");
			}
			
			contacts = query.getResultList();
						
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return contacts;
	}
	
	public List<Contact> loadRubricaFromDBCerca(String searchName, String searchSurname){
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = null;
		//List<Contact> contacts=null;
		List<Contact> results=null;
		
		try {
			session = factory.openSession();
		
			//Transaction transaction = session.beginTransaction();
			
			String hql="SELECT DISTINCT c FROM Contact c WHERE c.name = ?1 AND c.surname = ?2";
			Query query=session.createQuery(hql);
			query.setParameter(1, searchName);
			query.setParameter(2, searchSurname);
			
			results = query.getResultList();
			
			/*contacts=new ArrayList<>();
			Contact c = null;
			for(Contact contatto : results) {
				c=new Contact();
				c.setId(contatto.getId());
				c.setName(contatto.getName());
				c.setSurname(contatto.getSurname());
				c.setPhoneNumber(contatto.getPhoneNumber());
				c.setEmail(contatto.getEmail());
				c.setNote(contatto.getNote());
				contacts.add(c);
			}*/
						
			//selezionavo tutte le righe che presentavano stesso nome e cognome di quelli passati in ingresso
			//Se ci sono righe duplicate nella tabella, solo una di esse sarà inclusa nel risultato della query per via di
			//DISTINCT
			//creavo nuovi contatti da aggiungere a una lista a partire dalla lista di contatti ottenuta dalla query
			//??POTREI RITORNARE DIRETTAMENTE LA LISTA DI CONTATTI CHE MI RESTITUISCE LA QUERY DATO CHE è GIà UNA LISTA
			//DI CONTATTI?
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}
	
	public Map<Integer, String> searchID(){
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = null;
		
		Map<Integer, String> map = null;
		
		try {
			session = factory.openSession();
		
			//Transaction transaction = session.beginTransaction();
			
			//String hql="SELECT new Contact(c.id as id, concat(c.name, ' ', c.surname) as fullName) FROM Contact c";
			String hql = "SELECT new Contact(c.id as id, c.name as nome, c.surname as cognome) FROM Contact c";
			Query<Contact> query = session.createQuery(hql, Contact.class);
			
			List<Contact> results = query.getResultList();
			
			
			map= new HashMap<>();
			
			for(Contact c : results ) {
				int index = c.getId();
				String name = c.getName();
				String surname = c.getSurname();
				String fullName=name+" "+surname;
				map.put(index, fullName);
			}
			
			
			//io selezionavo le colonne che mi servivano,
			//salvavo l'id corrente dentro un variabile intera
			//salvavo in uno stringBuilder nome e cognome separati dallo spazio
			//mappavo in una mappa ogni singolo valore
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		return map;
	}
	
	//SCRITTURA CSV
 	public void writeRubricaCSV(List<Contact> contatti, String pathFile, String separator) {
			FileWriter fw =null;
			try {
				//creo un nuovo oggetto della classe File che punti al path
				File f = new File(pathFile);
				
				//controllo se il file esiste o meno
				boolean append =f.exists();
				
				
				//Se ho solo bisogno di scrivere caratteri o stringhe di testo semplici nel file, FileWriter può essere sufficiente
				//PrintWrinter serve invece a fare formattazioni
				//a FileWriter passo il valore di append, se risulta true, il file esisteva già e continua a scrivere in append,
				//se invece il file non esisteva, append sarà false e quindi non scriverà in append
				fw = new FileWriter(pathFile, append);
				
				
				
				//DEVO aggiungere un'intestazione, lo faccio manualmente o ricavo i campi da altro?
				String intestazione = COGNOME_FIELD+separator+NOME_FIELD+separator+TELEFONO_FIELD+separator+EMAIL_FIELD+
						separator+NOTE_FIELD;
				
				//poichè l'intestazione va scritta una sola volta devo controllare se il file esiste già, uso il boolean append
				if(!append) {
					fw.write(intestazione);
					fw.write('\n');
				}
				
				
				for(Contact c : contatti) {
					if(c!=null) {
						if(c.getSurname()!=null) {
							fw.write(c.getSurname());
						}
						fw.write(separator);
						if(c.getName()!=null) {
							fw.write(c.getName());
						}
						fw.write(separator);
						if(c.getPhoneNumber()!=null) {
							fw.write(c.getPhoneNumber());
						}
						fw.write(separator);
						if(c.getEmail()!=null) {
							fw.write(c.getEmail());
						} 
						fw.write(separator);
						if(c.getNote()!=null) {
							fw.write(c.getNote());
						}
						fw.write('\n');
						fw.flush();
					} else {
						System.out.println("contatto mancante");
					}
					
					
				}
			}catch(IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {
					fw.close();
				}catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}	
		}
		
	public void writeRubricaXML(List<Contact> contatti, String pathFile) {
			
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				
				DocumentBuilder db = dbf.newDocumentBuilder();
				
				Document document;
				
	            File file = new File(pathFile);
	            Element root;

	            if (file.exists()) {
	             
	                document = db.parse(file);
	               
	                root = document.getDocumentElement();
	            } else {
	            	
	                document = db.newDocument();  
	                
	                root = document.createElement("rubrica");
	                
	                 
	                document.appendChild(root);
	            }
				
				
				Element nuovoContatto = null;
				
				for(Contact c : contatti) {
					if(c!=null) { 
						nuovoContatto = document.createElement("contatto");
						
						if(c.getName()!=null) {
							Element name=document.createElement("nome");
							name.setTextContent(c.getName());
							nuovoContatto.appendChild(name);
						}
						if(c.getSurname()!=null) {
							Element surname=document.createElement("cognome");
							surname.setTextContent(c.getSurname());
							nuovoContatto.appendChild(surname);
						}
						if(c.getPhoneNumber()!=null) {
							Element phone=document.createElement("telefono");
							phone.setTextContent(c.getPhoneNumber());
							nuovoContatto.appendChild(phone);
						}
						if(c.getEmail()!=null) {
							Element email=document.createElement("email");
							email.setTextContent(c.getEmail());
							nuovoContatto.appendChild(email);
						}
						if(c.getNote()!=null) {
							Element note=document.createElement("note");
							note.setTextContent(c.getNote());
							nuovoContatto.appendChild(note);
						}

						root.appendChild(nuovoContatto);
					} else {
						System.out.println("Missing contact");
					}
					
				}
				
				TransformerFactory tf = TransformerFactory.newInstance();
				
				Transformer transformer = tf.newTransformer();
				
				DOMSource source = new DOMSource(document);
				
				StreamResult result = new StreamResult(new File(pathFile));
		
				StreamResult syso = new StreamResult(System.out);
				
				transformer.transform(source, result);
				
				transformer.transform(source, syso);
				
			
			}catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerConfigurationException tce) {
				tce.printStackTrace();
			} catch (TransformerException te) {
				te.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}
	
	
	public void writeRubricaDBInsert(List<Contact> contatti) {
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = null;
		
		try {
			session = factory.openSession();

			Transaction transaction = session.beginTransaction();
			
			int numeroInseriti=0;
			for(Contact c : contatti) {
				if(c!=null) {
					session.save(c);
					numeroInseriti++;
				}
			}
			transaction.commit();
			System.out.println("sono stati inseriti: "+ numeroInseriti+" contatti");
			
			//INSERT
//			contact = new Contact();
//			//contact.setId(20);
//			contact.setName("Pippo");
//			contact.setSurname("Gialli");
//			contact.setPhoneNumber("09876543");
//			contact.setEmail("Pippo@beije.it");
//			contact.setNote("contatto inserito con Hibernate");
//			System.out.println("contact PRE : " + contact);
//			session.save(contact);
//			System.out.println("contact POST : " + contact);
			
//			transaction.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void writeFromDBDelete(Integer id) {
		
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = null;
		
		try {
			session = factory.openSession();

			Transaction transaction = session.beginTransaction();
			
			
			Contact c=session.get(Contact.class, id);
			
			if(c!=null) {
				session.delete(c);
				transaction.commit();
				System.out.println("il tuo contatto è stato cancellato");
			} else {
				System.out.println("il contatto con l'id specificato non è stato cancellato");
			}
			
			//DELETE
     		//session.delete(contact);
			//transaction.commit();
			
			
		} catch (Exception e) {
			//if(transaction!=null){
			//transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Contact writeFromDBSet(String campo, String valore, Integer id) {
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = null;
		
		Contact c=null;
		
		try {
			session = factory.openSession();

			Transaction transaction = session.beginTransaction();
			
			//per fare update sembra vanno estratti tutti i contatti
			//Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); 
			//List<Contact> contacts = query.getResultList();
			
			
			
			//estraggo il contatto che ha l'id desiderato
			c=session.get(Contact.class, id);
			System.out.println("contatto da modificare: " + c);
			if(campo.equalsIgnoreCase("nome")) {
				c.setName(valore);
				session.update(c);
			} else if(campo.equalsIgnoreCase("cognome")) {
				c.setSurname(valore);
				session.update(c);
			} else if(campo.equalsIgnoreCase("telefono")) {
				c.setPhoneNumber(valore);
				session.update(c);
			} else if(campo.equalsIgnoreCase("email")) {
				c.setEmail(valore);
				session.update(c);
			} else if(campo.equalsIgnoreCase("note")) {
				c.setNote(valore);
				session.update(c);
			}
		
			//session.save(c);
			//System.out.println("contact POST : " + c);
		transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return c;	
		//UPDATE
		//contact = contacts.get(contacts.size()-1);
		//System.out.println("contact PRE UPDATE: " + contact);
//		contact.setId(10);
//		contact.setName("Chiara");
//		contact.setSurname("Sala");
//		contact.setPhoneNumber("09876543");
//		contact.setEmail("lara.sala@beije.it");
//		contact.setNote("contatto modificato con Hibernate");
//		
//		System.out.println("contact PRE : " + contact);
//		session.save(contact);
//		System.out.println("contact POST : " + contact);
		
	}


	public List<Contact> groupBy() {
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml")
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = null;
				
		List<Contact> contacts=null;
		
		try {
			 
			session = factory.openSession();
			//NON LEGGE QUESTA SINTASSI "SELECT c FROM Contact c GROUP BY c.name, c.surname, c.phoneNumber"
			String hql = "SELECT new Contact (c.name, c.surname, c.phoneNumber) FROM Contact c GROUP BY c.name, c.surname, c.phoneNumber";
			Query<Contact>query=session.createQuery(hql);
			contacts=query.getResultList();
			/*CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Contact> criteriaQuery = builder.createQuery(Contact.class);
	        Root<Contact> root = criteriaQuery.from(Contact.class);

	        criteriaQuery.multiselect(root.get("name"), root.get("surname"), root.get("phoneNumber"));
	        criteriaQuery.groupBy(root.get("name"), root.get("surname"), root.get("phoneNumber"));
	        criteriaQuery.having(builder.gt(builder.count(root), 1));*/

			//Transaction transaction = session.beginTransaction();
			
			/*
			String hql="SELECT c FROM Contact c "
	                + "GROUP BY c.name, c.surname, c.phoneNumber "
	                + "HAVING COUNT(c) > 1";
			Query query=session.createQuery(hql);
			contacts=query.getResultList();
			*/
			
		} catch (Exception e) {
			//if(transaction!=null){
			//transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return contacts;
		
		
	}
	
	public Contact merge(String nome, String cognome, String telefono) {
		Contact c=null;
		return c;
	}

}

