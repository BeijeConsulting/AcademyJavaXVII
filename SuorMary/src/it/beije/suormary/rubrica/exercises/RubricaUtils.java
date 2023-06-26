package it.beije.suormary.rubrica.exercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

import it.beije.suormary.rubrica.Contact;

public class RubricaUtils {
	
	/*Si definiscono le seguenti costanti che rappresentano gli indici dei rispettivi campi 
	 *array fields.
	 *Utilizzando queste costanti, il codice diventa più leggibile e indipendente 
	 *dall'ordine dei campi nel file CSV. 
	 *In caso di modifiche all'ordine dei campi o l'aggiunta di nuovi campi, 
	 *si dovrà aggiornare le costanti anziché modificare direttamente gli indici nel codice.
	 *infatti ora: fields[SURNAME_INDEX]
	 *prima:	   fields[0]
	 */
	private static final int SURNAME_INDEX = 0;
	private static final int NAME_INDEX = 1;
	private static final int PHONE_NUMBER_INDEX = 2;
	private static final int EMAIL_INDEX = 3;
	private static final int NOTE_INDEX = 4;
	private static final String SURNAME_FIELD = "COGNOME";
	private static final String NAME_FIELD = "NOME";
	private static final String PHONE_NUMBER_FIELD = "TELEFONO";
	private static final String EMAIL_FIELD = "EMAIL";
	private static final String NOTE_FIELD = "NOTE";
	
	public List<Contact> loadRubricaFromCSV(String pathFile, String separator) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		List<Contact> contatti = null;
		try {
			File file = new File(pathFile);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			contatti = new ArrayList<Contact>();
			Contact contatto = null;
			String line = null;
			String[] fields = null;
			StringBuilder stringBuilder = new StringBuilder();
			
			// Ignora la prima riga (intestazione)
	        bufferedReader.readLine();
	        
			while (bufferedReader.ready()) {
				//La funzione readLine() della classe BufferedReader legge una riga di testo dal file di input, 
				//ma rimuove il carattere di ritorno a capo (\n) alla fine della riga letta.
				line = bufferedReader.readLine();
				fields = line.split(separator);
				if (fields.length == 5) {
	                contatto = new Contact();
	                
	                stringBuilder.setLength(0);
	                /*Nel contesto di Java 8, la chiamata al metodo trim() della classe String 
	                 * rimane la soluzione più efficiente per rimuovere gli spazi bianchi iniziali 
	                 * e finali da una stringa. 
	                 * La funzione trim() crea una nuova istanza di stringa solo se necessario, 
	                 * altrimenti restituisce la stessa istanza di stringa se non sono presenti 
	                 * spazi bianchi iniziali o finali.*/
	                stringBuilder.append(fields[SURNAME_INDEX].trim());
	                contatto.setSurname(stringBuilder.toString());
	                
	                stringBuilder.setLength(0);
	                stringBuilder.append(fields[NAME_INDEX].trim());
	                contatto.setName(stringBuilder.toString());
	                
	                stringBuilder.setLength(0);
	                stringBuilder.append(fields[PHONE_NUMBER_INDEX].trim());
	                contatto.setPhoneNumber(stringBuilder.toString());
	                
	                stringBuilder.setLength(0);
	                stringBuilder.append(fields[EMAIL_INDEX].trim());
	                contatto.setEmail(stringBuilder.toString());
	                
	                stringBuilder.setLength(0);
	                stringBuilder.append(fields[NOTE_INDEX].trim());
	                contatto.setNote(stringBuilder.toString());
	                
	                contatti.add(contatto);
	            } else {
	                System.out.println("Riga non valida: " + line);
	            }
				//System.out.println(r);
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
	
	public List<Contact> loadRubricaFromCSVDynamic(String pathFile, String separator) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		List<Contact> contatti = null;
		try {
			File file = new File(pathFile);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			contatti = new ArrayList<Contact>();
			Contact contatto = null;
			String line = null;
			String[] fields = null;
			StringBuilder stringBuilder = new StringBuilder();
			
			// Legge la prima riga (intestazione)
	        String headerLine = bufferedReader.readLine();
	        String[] headers = headerLine.split(separator);
	        
	        //Il motivo della trasformazione da Array a Lista di String
	        //perché è una struttura dinamica 
	        List<String> headerList = Arrays.asList(headers);
	        
	        List<String> fieldsList = null;
	        int index = -1;
	        
			while (bufferedReader.ready()) {
				//legge una riga di testo dal file di input
				line = bufferedReader.readLine();
				fields = line.split(separator);
				
				if (fields.length == headers.length) {
	                contatto = new Contact();
	                
	                fieldsList = Arrays.asList(fields);
	                
	                if(headerList.contains(SURNAME_FIELD)) {
	                	index = headerList.indexOf(SURNAME_FIELD);
	                	stringBuilder.setLength(0);
		                stringBuilder.append(fieldsList.get(index).trim());
		                contatto.setSurname(stringBuilder.toString());
	                }
	                
                    if (headerList.contains(NAME_FIELD)) {
                        index = headerList.indexOf(NAME_FIELD);
                        stringBuilder.setLength(0);
                        stringBuilder.append(fieldsList.get(index).trim());
                        contatto.setName(stringBuilder.toString());
                    }
                    
                    if (headerList.contains(PHONE_NUMBER_FIELD)) {
                    	index = headerList.indexOf(PHONE_NUMBER_FIELD);
                    	stringBuilder.setLength(0);
    	                stringBuilder.append(fieldsList.get(index).trim());
    	                contatto.setPhoneNumber(stringBuilder.toString());
                    }
	                
	                if (headerList.contains(EMAIL_FIELD)) {
	                	index = headerList.indexOf(EMAIL_FIELD);
	                	stringBuilder.setLength(0);
	                	stringBuilder.append(fieldsList.get(index).trim());
		                contatto.setEmail(stringBuilder.toString());
	                }
	                
	                if (headerList.contains(NOTE_FIELD)) {
	                	index = headerList.indexOf(NOTE_FIELD);
	                	stringBuilder.setLength(0);
	                	stringBuilder.append(fieldsList.get(index).trim());
		                contatto.setNote(stringBuilder.toString());
	                }
	                
	                
	                contatti.add(contatto);
	            } else {
	                System.out.println("Riga non valida: " + line);
	            }
				//System.out.println(r);
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
                rootElement = document.createElement("contacts");
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
					contact = document.createElement("contact");
					
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
	
	public static void printContactList(List<Contact> contacts) {	    
	    if (contacts.isEmpty()) {
	        System.out.println("La lista dei contatti è vuota.");
	    } else {
	        System.out.println("Lista dei contatti:");
	        for (Contact contact : contacts) {
	            System.out.println("Nome: " + contact.getName()); 
	            System.out.println("Cognome: " + contact.getSurname()); 
	            System.out.println("Telefono: " + contact.getPhoneNumber());
	            System.out.println("Email: " + contact.getEmail());
	            System.out.println("Note: " + contact.getNote());
	            System.out.println();
	        }
	    }
	}
	
	//testing
	public static void main(String[] args)  {
		RubricaUtils ru = new RubricaUtils();
		String pathFile = "/Users/Padawan/git/file/rubrica.csv";
		String pathFileW = "/Users/Padawan/git/file/rubrica_scrittura.csv";
		
		String pathFileXML = "/Users/Padawan/git/file/rubrica.xml";
		String pathFileXMLW = "/Users/Padawan/git/file/rubrica_scrittura.xml";
		
		String pathFileDynamic = "/Users/Padawan/git/file/rubrica_dinamico_v1.csv";
		
		String separator = ";";
		
		List<Contact> contatti = ru.loadRubricaFromCSV(pathFile, separator);
		ru.writeRubricaCSV(contatti, pathFileW, separator);
		
		List<Contact> contattiXML = ru.loadRubricaFromXML(pathFileXML);
		ru.writeRubricaXML(contattiXML, pathFileXMLW);
		
		System.out.println();
		
		List<Contact> contattiDynamic = ru.loadRubricaFromCSVDynamic(pathFileDynamic, separator);
		RubricaUtils.printContactList(contattiDynamic);

	}

}
