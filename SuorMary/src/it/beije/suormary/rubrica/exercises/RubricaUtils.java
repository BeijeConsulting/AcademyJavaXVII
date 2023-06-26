package it.beije.suormary.rubrica.exercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	
	public List<Contact> loadRubricaFromCSV(String pathFile, String separator) throws Exception {
		File file = new File(pathFile);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<Contact> contatti = new ArrayList<Contact>();
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
		
		bufferedReader.close();
		fileReader.close();
		//System.out.println("rows number: " + contatti.size());
		return contatti;
	}
	
	public List<Contact> loadRubricaFromXML(String pathFile) throws Exception {
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
		List<Contact> contacts = new ArrayList<Contact>();
		
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
	
	public void writeRubricaCSV(List<Contact> contatti, String pathFile, String separator) throws Exception {
		File file = new File(pathFile);
		boolean append = file.exists(); // Controlla se il file esiste
		
		//PrintWriter serve solo a fare formatazzioni.
		//Se hai solo bisogno di scrivere caratteri o stringhe di testo semplici nel file, 
		//FileWriter può essere sufficiente
		//Se il file esiste, il valore di append è true e quindi i nuovi contatti verranno aggiunti
		//in coda al file.
		//Altrimenti, append viene impostato su false e i nuovi contatti sovrascriveranno il file.
		FileWriter fileWriter = new FileWriter(pathFile,append);
		
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
		fileWriter.close();
	}
	
	public void writeRubricaXML(List<Contact> contatti, String pathFile) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
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
	public static void main(String[] args) throws Exception {
		RubricaUtils ru = new RubricaUtils();
		String pathFile = "/Users/Padawan/git/file/rubrica.csv";
		String pathFileW = "/Users/Padawan/git/file/rubrica_scrittura.csv";
		String pathFileXML = "/Users/Padawan/git/file/rubrica.xml";
		String pathFileXMLW = "/Users/Padawan/git/file/rubrica_scrittura.xml";
		String separator = ";";
		
		List<Contact> contatti = ru.loadRubricaFromCSV(pathFile, separator);
		ru.writeRubricaCSV(contatti, pathFileW, separator);
		
		List<Contact> contattiXML = ru.loadRubricaFromXML(pathFileXML);
		ru.writeRubricaXML(contattiXML, pathFileW);
		
		RubricaUtils.printContactList(contattiXML);

	}

}
