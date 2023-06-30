package it.beije.suormary.rubrica.exercises;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				contatto.setId(rs.getString(ID_FIELD));
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
	
	public List<Contact> loadRubricaFromXML(String pathFile) {
		List<Contact> contacts = null;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			/*Questa linea di codice grazie al metodo parse carica il file XML specificato, 
			 *lo analizza e crea un oggetto Document che rappresenta il contenuto del file XML.
			 *Successivamente, questo oggetto Document viene utilizzato per l'elaborazione del documento XML
			 */
			Document document = documentBuilder.parse(pathFile);
			
			/*
			 * Questa linea di codice recupera l'elemento radice del documento XML, 
			 * che è l'elemento principale che racchiude tutti gli altri elementi nel documento. 
			 * Questo elemento radice può essere utilizzato per avviare la navigazione e
			 * l'elaborazione del documento XML. Infatti in questo codice, 
			 * l'elemento radice viene utilizzato successivamente per ottenere gli elementi figlio
			 * e accedere ai loro contenuti.
			 * <?xml version="1.0" encoding="UTF-8"?>
			 * <rubrica> <---- l'elemento radice!
			 * ...
			 * </rubrica> 
			 *  */
			Element docEl = document.getDocumentElement();
			
			//Stampa il nome del tag in questo caso l'elemento radice che è stato memorizzato nell'istanza di oggetto docEl
			//System.out.println(docEl.getTagName());
			
			/*
			 * Utilizza il metodo getChildElements per ottenere una lista di elementi 
			 * figlio dell'elemento radice ovvero docEl
			 * */
			List<Element> elements = getChildElements(docEl);
			
			//Viene inizializzata una lista di oggetti Contact per memorizzare 
			//i contatti estratti dal documento XML.
			contacts = new ArrayList<Contact>();
			
			Contact c = null;
			
			List<Element> els = null;
			
			//Viene eseguito un ciclo for-each sulla lista di elementi figlio della radice
			//per elaborare ciascun elemento (elementi contatto)
			for (Element el : elements) {
				
				//Posso accedere al valore dell'attributo tramite il metodo getAttribute 
				//passando il tag come stringa: per tag si intende il nome dell'attributo
				//System.out.println("eta' contatto = " + el.getAttribute("eta"));
				
				//mi fa vedere tutti i nodi di testo presenti all'interno di un elemento.
				//non è un metodo molto utile qui, meglio estrarre gli elementi figli da quello corrente
				//e da li' fare il getTextContent(), ovvero quando sono nel livello più interno
				//System.out.println("contenuto contatto = " + el.getTextContent());
				
				//Viene chiamato il metodo getChildElements() passando l'elemento corrente 
				//come argomento, che restituisce una lista di elementi figlio diretti dell'elemento corrente.
				els = getChildElements(el);
				
				//Viene creato un nuovo oggetto Contact per memorizzare i dettagli del contatto.
				c = new Contact();
				
				//Viene eseguito un ciclo for-each sulla lista di elementi figlio dell'elemento 
				//corrente per elaborare ciascun elemento.
				for (Element e : els) {
					
					//Per ogni elemento, viene stampato il nome dell'elemento utilizzando il
					//metodo getTagName() sull'oggetto Element, e viene stampato il contenuto 
					//del nodo utilizzando il metodo getTextContent() sull'oggetto Element.
					//System.out.println(e.getTagName() + " = " + e.getTextContent());
					
					//Viene utilizzato un costrutto switch-case per assegnare il valore appropriato 
					//al campo corrispondente nell'oggetto Contact, in base al nome dell'elemento.
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
				//L'oggetto Contact (riferimento oggetto c) viene aggiunto alla lista dei contatti.
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
	
	//Prende in input l'elemento che riceve in parametro d'ingresso e 
	//restituisce una lista di elementi figlio di quell'elemento
	public static List<Element> getChildElements(Element el) {
		//Restituisce la lista dei nodi figlio di un elemento specifico
		//che in questo caso è el (parametro in ingresso)
		//La lista di nodi conterrà: nodi elementi e nodi spazi bianchi, 
		//le altre tipologie di nodi non vengono contate 
		NodeList nodeList = el.getChildNodes();
		
		//System.out.println("nodeList size: " + nodeList.getLength());
		
		//E' una lista vuota che andrà a contenere gli elementi figli dell'
		//elemento fornito come parametro in ingresso "Element el"
		List<Element> elements = new ArrayList<Element>();
		
		//Viene eseguito un ciclo for per iterare attraverso tutti i nodi 
		//presenti nella nodeList.
		for (int i = 0; i < nodeList.getLength(); i++) {
			//Per ogni nodo nell'iterazione corrente...
			
			//dalla lista tramite questo metodo specifico item restuisce il nodo
			//a posizione i.
			Node n = nodeList.item(i);
			
			//viene verificato se è un'istanza di Element utilizzando l'operatore 
			//instanceof. 
			//In pratica, questo controlla se il nodo è un elemento XML. 
			//Se è un elemento, viene aggiunto alla lista elements dopo essere
			//stato castato esplicitamente --> perche la classe NodeList contiene
			//nodi e noi vogliamo in specifico solo gli elementi che si trattano di una
			//tipologia di nodi.
			if (n instanceof Element) elements.add((Element) n);
		}
		
		return elements;
	}
	
	public void writeRubricaCSV(List<Contact> contatti, String pathFile, String separator) {
		FileWriter fileWriter = null;
		try {
			File file = new File(pathFile);
			boolean append = file.exists(); // Controlla se il file esiste
			
			//PrintWriter serve solo a fare formatazzioni.
			//Se hai solo bisogno di scrivere caratteri o stringhe di testo semplici nel file, 
			//FileWriter può essere sufficiente
			//Se il file esiste, il valore di append è true e quindi i nuovi contatti verranno aggiunti
			//in coda al file.
			//Altrimenti, append viene impostato su false e i nuovi contatti sovrascriveranno il file.
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
	
	public void writeRubricaDB(List<Contact> contatti) {
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
	
	public void writeRubricaXML(List<Contact> contatti, String pathFile) {
		try {
			/*
			 * crea un'istanza di DocumentBuilderFactory, che rappresenta 
			 * una factory per oggetti DocumentBuilder. La factory è 
			 * utilizzata per configurare e creare istanze di DocumentBuilder, 
			 * che a sua volta verrà utilizzato per creare documenti XML.
			 */
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			
			/*
			 * crea un oggetto DocumentBuilder utilizzando la DocumentBuilderFactory 
			 * precedentemente creata. L'oggetto DocumentBuilder è responsabile della 
			 * creazione e della manipolazione del documento XML.
			 */
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			/*
			 * crea un nuovo oggetto Document, che rappresenta il documento XML vuoto. 
			 * L'oggetto Document sarà utilizzato come punto di partenza per costruire 
			 * la struttura del documento XML.
			 * Document document = documentBuilder.newDocument();
			 */
			
			Document document;
			File file = new File(pathFile);
			
			//viene creato un elemento radice chiamato "contacts"
			//utilizzando il metodo createElement
			//Element contacts = document.createElement("contacts");
			
			//questo sarebbe l'elemento contacts ma ho deciso di cambiare il nome 
			//per una migliore lettura
			Element rootElement;
			
			if(file.exists()){
				// Il file esiste già, carica il contenuto esistente
                document = documentBuilder.parse(file);
                rootElement = document.getDocumentElement();
			} else {
				// Il file non esiste, crea un nuovo documento
                document = documentBuilder.newDocument();
                rootElement = document.createElement("rubrica");
                document.appendChild(rootElement);
			}
			
			//l'elemento radice chiamato "contacts" e viene impostato come 
			//elemento radice del documento XML
			//document.appendChild(rootElement);
			
			//creo dei nuovi contatti da aggiungere
			/*
			 * MioContact contact1 = new MioContact();
			contact1.setName("Pippo");
			contact1.setSurname("Rossi");
			contact1.setPhoneNumber("09876543");
			contact1.setEmail("Pippo@beije.it");
			
			MioContact contact2 = new MioContact();
			contact2.setName("Pluto");
			contact2.setSurname("Bianchi");
			contact2.setPhoneNumber("098767564");
			contact2.setEmail("pluto@beije.it");
			 */
			
			//il metodo mi passa una lista quindi non mi serve
			//creare nuovi contatti, ma li estrapolo da questa lista
			
			//mi serve creare un riferimento a un oggetto di tipo
			//Element settato a null per ottimizzare l'uso della memoria
			//tale riferimento verrà usato nel for each loop
			Element contact = null;
			
			for (Contact c : contatti) {
				
				if (c != null) {
					//Per ogni contatto, viene creato un nuovo elemento "contact"
					contact = document.createElement("contatto");
					
					/*
					 *  impostato un attributo "age" con il valore "50" per ogni elemento 
					 *  "contact" utilizzando il metodo setAttribute()
					 *  contact.setAttribute("age", "50");
					 */
					
					/*
					 * i dati del contatto (nome, cognome, numero di telefono, email e note) 
					 * vengono controllati e, se non sono nulli, vengono creati gli elementi 
					 * corrispondenti utilizzando document.createElement() e vengono impostati 
					 * i loro contenuti utilizzando setTextContent().
					 * ogni elemento figlio viene aggiunto all'elemento "contact" usando appendChild()
					 */
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
					
					/*
					 * dopo aver aggiunto i vari elementi all'elemento madre
					 * nuovoContatto, questo viene aggiunto alla radice 
					 * (ovvero contacts)
					 */
					rootElement.appendChild(contact);
				}else {
                    System.out.println("Contatto mancante");
                }
			}
			
			/*
			 * SINTESI
			 * Le righe di codice che seguono si occupano di trasformare il 
			 * documento XML in una forma leggibile utilizzando un oggetto 
			 * Transformer e quindi scrivere il contenuto del documento su un file XML 
			 * e sulla console.
			 */
			
			/*
			 * crea un'istanza di TransformerFactory, che rappresenta una factory per 
			 * oggetti Transformer. La factory viene utilizzata per ottenere un oggetto 
			 * Transformer che può essere utilizzato per trasformare il documento XML.
			 */
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			/*
			 * crea un oggetto Transformer utilizzando la TransformerFactory 
			 * precedentemente creata. L'oggetto Transformer è responsabile di 
			 * trasformare il documento XML in una forma leggibile.
			 */
			Transformer transformer = transformerFactory.newTransformer();
			
			/*
			 * crea un oggetto DOMSource utilizzando il documento XML (document) come 
			 * sorgente dei dati per la trasformazione. L'oggetto DOMSource rappresenta 
			 * il contenuto del documento XML da trasformare.
			 */
			DOMSource source = new DOMSource(document);
			
			/*
			 * OUTPUT PER FILE XML
			 * crea un oggetto StreamResult utilizzando un oggetto File per specificare 
			 * il percorso e il nome del file XML in cui verrà scritto il contenuto 
			 * trasformato
			 */
			StreamResult result = new StreamResult(new File(pathFile));

			// Output to console for testing
			/*
			 * crea un oggetto StreamResult utilizzando System.out come argomento. 
			 * In questo modo, il risultato della trasformazione del documento XML 
			 * verrà scritto sulla console.
			 */
			StreamResult syso = new StreamResult(System.out);
			
			/*
			 * esegue la trasformazione del documento XML utilizzando l'oggetto 
			 * Transformer. Il risultato della trasformazione viene scritto nel 
			 * file specificato dall'oggetto StreamResult result.
			 */
			transformer.transform(source, result);
			
			/*
			 * esegue un'altra trasformazione del documento XML utilizzando lo stesso 
			 * oggetto Transformer. Tuttavia, il risultato della trasformazione viene 
			 * scritto sulla console utilizzando l'oggetto StreamResult syso
			 */
			transformer.transform(source, syso);

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
	
	public void addContactDB(Contact contatto) {
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
	
	public void updateContactDB(String id, Contact contatto) {
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
	
	public void deleteContactDB(String id) {
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
	
	public void deleteContactDB(List<Contact> contatti) {
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
							System.out.println("Query non inserita: " + query);
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
