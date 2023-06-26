package esercizi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class RubricaUtils {
	
	//questi indici sono da sostituire in sb.append(campi[0].trim()); al posto di campi[0] in modo da far diventare
	//il codice più leggibile e indipendente dall'ordine dei campi nel file. se dovesse cambiare d'ordine o inserire nuove
	//colonne, basterebbe ad esempio sostituire a COGNOME_INDEX l'indice 1 e quel campo
	/*private static final int COGNOME_INDEX = 0;
	private static final int NOME_INDEX = 1;
	private static final int TELEFONO_INDEX = 2;
	private static final int EMAIL_INDEX = 3;
	private static final int NOTE_INDEX = 4;
*/
	
	private static final String COGNOME_FIELD="COGNOME";
	private static final String NOME_FIELD="NOME";
	private static final String TELEFONO_FIELD="TELEFONO";
	private static final String EMAIL_FIELD="EMAIL";
	private static final String NOTE_FIELD="NOTE";
	
	public static void main(String[] args) throws Exception{
	
		
		String pathFile = "/Users/Padawan/Desktop/fileAcademy/rubrica.csv";
		
		String separator = ";";
		RubricaUtils ru = new RubricaUtils();
		
		List<Contact> contatti = ru.loadRubricaFromCSV(pathFile, separator);
		
		String pathFile1 = "/Users/Padawan/Desktop/fileAcademy/rubricaScritta.csv";
		
		ru.writeRubricaCSV(contatti, pathFile1, separator);
		
		List<MioContact> c1 = ru.loadRubricaFromXML(pathFile1);
		
		ru.stampaContatti(c1);

	}
	
	public List<Contact> loadRubricaFromCSV(String pathFile, String separator) throws Exception {
		File file = new File(pathFile);
		System.out.println("exists? " + file.exists());
		
		FileReader fr = new FileReader(file);
		
		BufferedReader br = new BufferedReader(fr);
		List<Contact> contatti = new ArrayList<Contact>();
		Contact c = null;
		String riga = null;
		//array dove si salvano i campi che si generano ad ogni linea dopo averli separati all'interno della linea 
		//stessa
		String [] campi =null;
		//lo stringbuilder è meglio inizializzarlo e poi svuotarlo con apposito metodo dentro al ciclo in cui lo si 
		//utilizza perchè è un oggetto di appoggio e quindi sarebbe uno spreco di risorse inizializzarlo da zero ogni
		//volta (non ha altre utilità come invece un oggetto ContactCSV deve avere, in quanto ho degli oggetti indipendenti)
		StringBuilder sb = new StringBuilder();
		
		//serve per salatare la prima linea del file cvs, in quanto contiene l'intestazione con i nomi dei campi e non
		//i valori dei contatti
		br.readLine();
		
		while (br.ready()) {
			riga = br.readLine();
			
			//non serve farlo ora perchè toglie spazi solo all'inizio e alla fine 
			//riga = riga.trim();
			campi=riga.split(separator);
			
			//controllo che dopo lo split l'array sia della
			//dimensione corretta, ovvero pari al numero di
			//campi che devono esserci (5 nel nostro caso)
			if(campi.length==5) {
				//in base al riferimento dell'oggetto che ho creato fuori dal while, vado a costruire di volta in volta
				//l'oggetto corrente
				c=new Contact();
				
				//in questo modo a ogni iterazione svuoto lo sb precendente
				//viene utilizzato come operazione di pulizia
				sb.setLength(0);
				//funzione per inserire valori all'interno dello stringbuilder
				sb.append(campi[0].trim());
				//ora setto il valore di c passandogli lo stringbuilder convertito in stringa tramire toString()
				c.setCognome(sb.toString());
				
				//di nuovo azzero lo stringbuilder, lo faccio prima di scriverci ogni nuova volta
				sb.setLength(0);
				sb.append(campi[1].trim());
				c.setNome(sb.toString());
				
				sb.setLength(0);
				sb.append(campi[2].trim());
				c.setTelefono(sb.toString());
				
				sb.setLength(0);
				sb.append(campi[3].trim());
				c.setEmail(sb.toString());
				
				sb.setLength(0);
				sb.append(campi[4].trim());
				c.setNote(sb.toString());
				
				//VERSIONE CON STRING
				/*c.setCognome(campi[0].trim());
				c.setNome(campi[1].trim());
				c.setTelefono(campi[2].trim());
				c.setEmail(campi[3].trim());
				c.setNote(campi[4].trim());
				*/
				
				//DEVO AGGIUNGERE l'oggetto dopo averlo settato per poterlo avere nella mia lista che cotiene contatti
				contatti.add(c);
			} else {
				//è buona norma tenere una traccia di tutte le linee che sono sbagliate
				System.out.println("La seguente linea non è corretta: "+riga);
			}
			
			
			
		}
		
		//prima si chiude il bufferreader
		br.close();
		//poi chiudo il filereader
		fr.close();
		return contatti;
		
	}
	
	public List<MioContact> loadRubricaFromXML(String pathFile) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		//questa linea di codice grazie al metodo parse, carica il file XML specificato, lo analizza e crea un oggetto 
		//Document che rappresenta il contenuto del file XML. Successivamente, questo oggetto Document viene utilizzato 
		//per l'elaborazione del documento XML.
		Document document = documentBuilder.parse("/Users/Padawan/Desktop/fileAcademy/rubrica.xml");
		
		/*
		 *  recupera l'elemento radice del documento XML, che è l'elemento principale che racchiude tutti gli altri 
		 *  elementi nel documento. Questo elemento radice può essere utilizzato per avviare la navigazione e l'elaborazione 
		 *  del documento XML. L'elemento radice viene utilizzato successivamente per ottenere gli elementi 
		 *  figlio e accedere ai loro contenuti.
		 *  
		 *  <?xml version="1.0" encoding="UTF-8"?>
			<rubrica> ----> questo è l'elemento radice
			...
			</rubrica>
		 */
		Element docEl = document.getDocumentElement();
		
		//il metodo getChildElement viene creato al fine di realizzare una lista di elementi figli dell'elemento radice
		//ovvero docE1
		List<Element> elements = getChildElements(docEl);
		
		//Viene inizializzata una lista di oggetti MioContact per memorizzare i contatti estratti dal documento XML.
		List<MioContact> contacts = new ArrayList<MioContact>();
		MioContact c = null;
		
		//dichiaro una lista che contiene elementi che servirà a contenere i figli dell'elemento contatti
		List<Element> els = null;
		
		//viene eseguito un ciclo for each sulla lista dei figli della radice (quindi gli elementi che sono contatti)
		for (Element el : elements) {
			
			//per ogni elemento posso accedere al valore dell'attributo tramite getAttribute, passando il tag come stringa
			//(per tag intendo il nome dell'attributo)
			//System.out.println("et� contatto = " + el.getAttribute("eta"));
			
			//mi fa vedere tutti i nodi di testo presenti all'interno di un elemento.
			//non è un metodo molto utile qui, meglio estrarre gli elementi figli da quello corrente
			//e da li' fare il getTextContent(), ovvero quando sono nel livello più interno
			//System.out.println("contenuto contatto = " + el.getTextContent());
			
			//Viene chiamato il metodo getChildElements() passando l'elemento corrente come argomento, che 
			//restituisce una lista di elementi figlio diretti dell'elemento corrente.
			els = getChildElements(el);
			
			//Viene creato un nuovo oggetto Contact per memorizzare i dettagli del contatto.
			c = new MioContact();
			
			/*
			 * Viene eseguito un ciclo for-each sulla lista di elementi figlio dell'elemento corrente 
			 * per elaborare ciascun elemento.
			 */
			for (Element e : els) {
				
				/*
				 * Per ogni elemento, viene stampato il nome dell'elemento utilizzando il metodo getTagName() 
				 * sull'oggetto Element, e viene stampato il contenuto del nodo utilizzando il metodo 
				 * getTextContent() sull'oggetto Element.
				 */
				//System.out.println(e.getTagName() + " = " + e.getTextContent());
				
				/*
				 * Viene utilizzato un costrutto switch-case per assegnare il valore appropriato al 
				 * campo corrispondente nell'oggetto Contact, in base al nome dell'elemento.
				 */
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
			
			//dopo aver settato tutti gli attributi del riferimento c, lo aggiunge alla lista dei contatti
			contacts.add(c);
		
			
		}
		return contacts;
	}
	
	/*
	 * prende in input l'elemento (in questo caso la radice) passato in ingresso in questo caso
	 */
	public static List<Element> getChildElements(Element el) {
		
		//il metodo restituisce la lista dei nodi figli di un elemento specifico (el nel nostro caso)
		//la lista di nodi conterrà nodi elementi e nodi spazi bianchi (NO altre tipologie di nodo come quelli attributi)
		//per questo motivo servirà usare una nuova lista del tipo di interesse e salvare gli elementi di nodeList che
		//sono del tipo di nostro interesse (tramite un cast esplicito) all'interno di questa nuova lista
		NodeList nodeList = el.getChildNodes();
		
		//tramite questo metodo otteniamo la lunghezza, saranno presenti vari tipi di nodi
		//System.out.println("nodeList size: " + nodeList.getLength());
		
		//creo una lista che andrà a contenere elementi figli dell'elemento fornito come parametro del metedo (el)
		List<Element> elements = new ArrayList<Element>();
		//viene eseguito per iterare su tutti i nodi presenti nella nodeList
		for (int i = 0; i < nodeList.getLength(); i++) {
			//creo un riferimento all'elemento corrente della lista, ottenendolo tramite la funzione item a cui passo l'indice
			Node n = nodeList.item(i);
			
			/*
			 * Per ogni nodo nell'iterazione corrente, viene verificato se è un'istanza di Element utilizzando 
			 * l'operatore instanceof. In pratica, questo controlla se il nodo è un elemento XML. Se è un elemento, 
			 * viene aggiunto alla lista elements dopo essere stato castato esplicitamente a Elementi
			 */
			if (n instanceof Element) elements.add((Element) n);
		}
		
		return elements;
	}
	
	
	//SCRITTURA CSV
	public void writeRubricaCSV(List<Contact> contatti, String pathFile, String separator) throws Exception{
		//creo un nuovo oggetto della classe File che punti al path
		File f = new File(pathFile);
		
		//controllo se il file esiste o meno
		boolean append =f.exists();
		
		
		//Se ho solo bisogno di scrivere caratteri o stringhe di testo semplici nel file, FileWriter può essere sufficiente
		//PrintWrinter serve invece a fare formattazioni
		//a FileWriter passo il valore di append, se risulta true, il file esisteva già e continua a scrivere in append,
		//se invece il file non esisteva, append sarà false e quindi non scriverà in append
		FileWriter fw = new FileWriter(pathFile, append);
		
		
		
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
				fw.write(c.getCognome());
				fw.write(separator);	
				fw.write(c.getNome());
				fw.write(separator);
				fw.write(c.getTelefono());
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
		
		fw.close();
	}
	
	public void stampaContatti(List<MioContact> contatti) {
		for(MioContact c1 : contatti) {
			if(c1!=null) {
				if(c1.getSurname()==null || c1.getSurname().isEmpty()) {
					System.out.println("cognome mancante!");
				} else {
					System.out.println(c1.getSurname());
				}
				
				if(c1.getName()==null || c1.getName().isEmpty()) {
					System.out.println("nome mancante!");
				} else {
					System.out.println(c1.getName());
				}
				
				if(c1.getPhoneNumber()==null || c1.getPhoneNumber().isEmpty()) {
					System.out.println("telefono mancante!");
				} else {
					System.out.println(c1.getPhoneNumber());
				}
				
				if(c1.getEmail()==null || c1.getEmail().isEmpty()) {
					System.out.println("email mancante!");
				} else {
					System.out.println(c1.getEmail());
				}
				if(c1.getNote()==null || c1.getNote().isEmpty()) {
					System.out.println("nota mancante");
				} else {
					System.out.println(c1.getNote());
				}
				
				System.out.println("*********");
			}
			
		}
	}
}
