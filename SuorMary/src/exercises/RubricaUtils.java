package exercises;

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
	private static final String COGNOME_FIELD="COGNOME";
	private static final String NOME_FIELD="NOME";
	private static final String TELEFONO_FIELD="TELEFONO";
	private static final String EMAIL_FIELD="EMAIL";
	private static final String NOTE_FIELD="NOTE";
	
	
	public static void main(String[] args) {
		
		
		String pathFile = "/Users/Padawan/Desktop/fileAcademy/rubrica_dinamico_v2.csv";
		
		
		String separator = ";";
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
		Connection connection = null;
		Statement statement = null;
		List<Contact> contact = null;
		
		try {
			//buona norma delegare a un solo metodo la creazione della connessione
			connection = RubricaUtils.getConnection();
			
			statement = connection.createStatement();
			
			System.out.println("connection open? " + !connection.isClosed());
			
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			
			contact = new ArrayList<>();
			Contact c = null;
			
			while (rs.next()) {
				c = new Contact();
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setPhoneNumber(rs.getString("telefono"));
				c.setNote(rs.getString("note"));
				
				contact.add(c);
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
		return contact;
	}
	
	public List<Contact> loadRubricaFromDBOrdinata(String ordine){
		Connection connection = null;
		//Statement statement = null;
		PreparedStatement preparedStatement=null;
		List<Contact> contact = null;
		
		try {
			connection = RubricaUtils.getConnection();
			
			
			//VECCHIA SINTASSI CON STATEMENT
			/*statement = connection.createStatement();
			
			System.out.println("connection open? " + !connection.isClosed());
			
			//MODIFICHE PER CAPIRE QUALE QUERY FARE
			ResultSet rs=null;
			
			if(ordine.equalsIgnoreCase("c")) {
				rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY cognome");
			} else if(ordine.equalsIgnoreCase("n")) {
				rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY nome");
			} else {
				rs = statement.executeQuery("SELECT * FROM rubrica");
			}
			*/
			
			StringBuilder query = new StringBuilder("SELECT * FROM rubrica");
			
			if(ordine.equalsIgnoreCase("c")) {
				query.append(" ORDER BY cognome");
			} else if(ordine.equalsIgnoreCase("n")) {
				query.append(" ORDER BY nome");
			}
			
			// crea un oggetto PreparedStatement
			preparedStatement = connection.prepareStatement(query.toString());
			
			//esegue la query
			ResultSet rs = preparedStatement.executeQuery();	
			
			contact = new ArrayList<>();
			Contact c = null;
			
			
			while (rs.next()) {
				c = new Contact();
				
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setPhoneNumber(rs.getString("telefono"));
				c.setNote(rs.getString("note"));
				
				contact.add(c);
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
		return contact;
	}
	
	public List<Contact> loadRubricaFromDBCerca(String name, String surname){
		Connection connection = null;
		//Statement statement = null;
		
		//PreparedStatement preparedStatement=null;: Inizializza una variabile preparedStatement di tipo PreparedStatement 
		//e la inizializza con il valore null. Questa variabile verrà utilizzata per eseguire la query preparata.
		PreparedStatement preparedStatement = null;
		List<Contact> contact = null;
		
		try {
			connection = RubricaUtils.getConnection();
			
			/* VERSIONE FUNZIONANTE CON STATEMENT
			statement = connection.createStatement();
			
			System.out.println("connection open? " + !connection.isClosed());
			
			//MODIFICHE PER CAPIRE QUALE QUERY FARE
			ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM rubrica WHERE nome = '" + name + "' AND cognome = '" + surname + "'");
			*/
			
			
			StringBuilder query=new StringBuilder("SELECT DISTINCT * FROM rubrica WHERE nome = ? AND cognome = ?");
			preparedStatement=connection.prepareStatement(query.toString());
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			contact = new ArrayList<>();
			Contact c = null;
			
			while (rs.next()) {
				c = new Contact();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setPhoneNumber(rs.getString("telefono"));
				c.setNote(rs.getString("note"));
				
				contact.add(c);
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
		return contact;
	}
	
	public Map<Integer, String> searchID(){
		Connection connection = null;
		//Statement statement = null;
		PreparedStatement preparedStatement = null;
		List<Integer> indici = null;
		Map<Integer, String> map = null;
		
		try {
			connection = RubricaUtils.getConnection();
			
			
			/*QUERY FUNZIONANTE CON STATEMENT
			statement = connection.createStatement();
			
			System.out.println("connection open? " + !connection.isClosed());
			
			//MODIFICHE PER CAPIRE QUALE QUERY FARE
			ResultSet rs = statement.executeQuery("SELECT id, nome, cognome FROM rubrica");
			*/
			
			StringBuilder query = new StringBuilder("SELECT id, nome, cognome FROM rubrica");
			preparedStatement=connection.prepareStatement(query.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			map = new HashMap<>();
			StringBuilder sb=new StringBuilder();
			
			while (rs.next()) {
				int index = rs.getInt("id");
				sb.setLength(0);
				sb.append(rs.getString("nome")).append(" ").append(rs.getString("cognome"));
				map.put(index, sb.toString());
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
						fw.write(c.getSurname());
						fw.write(separator);	
						fw.write(c.getName());
						fw.write(separator);
						fw.write(c.getPhoneNumber());
						fw.write(separator);
						fw.write(c.getEmail());
						fw.write(separator);
						fw.write(c.getNote());
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
		Connection connection = null;
		//Statement statement = null;
		PreparedStatement preparedStatement=null;
		try {

			connection = RubricaUtils.getConnection();
			
			/* 	QUERY FUNZIONANTE CON STATEMENT
			statement = connection.createStatement();
			System.out.println("connection open? " + !connection.isClosed());
			
			
			//mi serve uno stringbuilder in cui salvare i campi dei contatti
			StringBuilder query = new StringBuilder();
			
			//voglio tenere traccia di quanti inseriementi sono andati a buon fine
			int numInserimenti=0;
			

			
			//CODICE FUNZIONANTE CHE NON CONSIDERA GLI ESCAPE
		
			for(Contact c : contatti) {
				query.setLength(0);
				query.append(("INSERT INTO rubrica (`nome`, `cognome`, `telefono`, `email`, `note`) VALUES ('"));
				if(c!=null) {
					query.append(c.getName()).append("', '");
					query.append(c.getSurname()).append("', '");
					query.append(c.getPhoneNumber()).append("', '");
					query.append(c.getEmail()).append("', '");
					query.append(c.getNote()).append("')");
				
					
					//inserisco la query
					numInserimenti=numInserimenti+statement.executeUpdate(query.toString());
				}
				
			}*/
			
			StringBuilder query = new StringBuilder();
			
			
			int numeroInseriti=0;
			
			for(Contact c : contatti) {
				query.setLength(0);
				query.append("INSERT INTO rubrica(nome, cognome, telefono, email, note)"
					+ "VALUES (?, ?, ?, ?, ?)");
				preparedStatement = connection.prepareStatement(query.toString());
				if(c!=null) {
					preparedStatement.setString(1, c.getName());
					preparedStatement.setString(2, c.getSurname());
					preparedStatement.setString(3, c.getPhoneNumber());
					preparedStatement.setString(4, c.getEmail());
					preparedStatement.setString(5, c.getNote());
				}
				preparedStatement.execute();
				numeroInseriti++;
				
			}
			
			System.out.println("sono stati inseriti: "+ numeroInseriti+" contatti");
			
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
	
	public void writeFromDBDelete(String id) {
		Connection connection = null;
		//Statement statement = null;
		PreparedStatement ps=null;
		try {
			
			connection = RubricaUtils.getConnection();
			
			/*	QUERY FUNZIONANTE
			statement = connection.createStatement();
			System.out.println("connection open? " + !connection.isClosed());

			//voglio tenere traccia di quante cancellazioni sono andate a buon fine
			int numCancellazioni = statement.executeUpdate("DELETE FROM rubrica WHERE nome = '" + name + "' AND cognome = '" + surname + "'" );
			*/	
			int numCancellazioni=0;
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM rubrica WHERE id = ?");
			ps=connection.prepareStatement(query.toString());
			ps.setString(1, id);
			ps.execute();
			
			System.out.println("il tuo contatto è stato cancellato con successo!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Contact writeFromDBSet(String campo, String valore, String id) {
		Connection connection = null;
		//Statement statement = null;
		PreparedStatement ps = null;
		Contact c=null;
		try {

			connection = RubricaUtils.getConnection();
			
			/*CODICE FUNZIONANTE PER LE MODIFICHE CON STATEMENT
			statement = connection.createStatement();
			System.out.println("connection open? " + !connection.isClosed());
			
			//voglio tenere traccia di quante modifche sono andate a buon fine
			int numModifiche = statement.executeUpdate("UPDATE rubrica set telefono = '987654' WHERE id < 4");
			System.out.println(numModifiche + " record modificati");
			*/
			//campo=campo.toLowerCase();
			
			
			/*switch (campo) {
		    case "nome":
		        query.append("nome = ? ");
		        break;
		    case "cognome":
		    	query.append("cognome = ? ");
		        break;
		    case "telefono ":
		    	query.append("telefono = ? ");
		        break;
		    case "email ":
		    	query.append("email = ? ");
		        break;
		    case "note ":
		    	query.append("note = ? ");
		        break;    
		    default:
		        System.out.println("valore non riconosciuto");
		        break;
		}*/
			
			StringBuilder query = new StringBuilder();
			query.append("UPDATE rubrica SET nome = ? WHERE id=?");
			/*if(campo.equalsIgnoreCase("nome")) {
				query.append(" nome = ?");
			} else if(campo.equalsIgnoreCase("cognome")) {
				query.append(" cognome = ?");
			} else if(campo.equalsIgnoreCase("telefono")) {
				query.append(" telefono = ?");
			} else if(campo.equalsIgnoreCase("email")) {
				query.append(" email = ?");
			} else if(campo.equalsIgnoreCase("note")) {
				query.append(" note = ?");
			}
			query.append(" WHERE id = ?");*/
			ps=connection.prepareStatement(query.toString());
			ps.setString(1, valore);
			ps.setString(2, id);
			
			ps.executeUpdate();
			c=new Contact();
			c.setName(valore);
			
			/*
			ResultSet rs = ps.executeQuery();
			rs.next();
			c=new Contact();
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("nome"));
			c.setSurname(rs.getString("cognome"));
			c.setPhoneNumber(rs.getString("telefono"));
			c.setEmail(rs.getString("email"));
			c.setNote(rs.getString("note"));*/
		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}


	public List<Contact> groupBy() {
		Connection connection = null;
		Statement statement = null;
		List<Contact>contact = null;
		try {

			connection = RubricaUtils.getConnection();
			
			statement = connection.createStatement();
			System.out.println("connection open? " + !connection.isClosed());
			

			String query = "SELECT nome, cognome, telefono "
		            + "FROM rubrica "
		            + "GROUP BY nome, cognome, telefono "
		            + "HAVING COUNT(*) > 1";
			
			//voglio tenere traccia di quante cancellazioni sono andate a buon fine
			ResultSet rs = statement.executeQuery(query);
			
			contact = new ArrayList<>();
			Contact c = null;
			
			while (rs.next()) {
				c = new Contact();
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				//c.setEmail(rs.getString("email"));
				c.setPhoneNumber(rs.getString("telefono"));
				//c.setNote(rs.getString("note"));
				
				contact.add(c);
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
		return contact;
	}

}

