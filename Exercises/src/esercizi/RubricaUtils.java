package esercizi;

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
	
	//english version
	private static final String CONTACT_FIELD="contacts";
	private static final String SURNAME_FIELD="surname";
	private static final String NAME_FIELD="name";
	private static final String PHONE_FIELD="phone";
	private static final String MAIL_FIELD="email";
	private static final String NOTE_EN_FIELD="note";
	
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
		}catch(FileNotFoundException fne) {
			fne.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally {
			try {
				//prima si chiude il bufferreader
				br.close();
				//poi chiudo il filereader
				fr.close();
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		
	return contatti;
		
	}
	
	
	
	//LETTURA DINAMICA DA CSV
		public List<Contact> loadRubricaDynamicFromCSV(String pathFile, String separator){
			
			//li metto fuori altrimenti il ramo finally non vedrebbe l'inizializzazione
			FileReader fr=null;
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
				
				//leggo l'intestazione e la salvo in una stringa
				String headerLine = br.readLine();
				
				//salvo i nomi delle colonne in un array di stringhe
				String [] headers = headerLine.split(separator);
				
				//trasformo headers in arraylist perchè è una struttura dinamica
				List<String> headerList = Arrays.asList(headers);
				
				//creo una lista per contenere gli elementi derivati dall'array degli elementi di ogni riga
				List<String> fieldsList = null;
				
				//setto un indice 
		        int index = -1;
		        
		        while (br.ready()) {
	                //legge una riga di testo dal file di input
	                riga = br.readLine();
	                campi = riga.split(separator);
	                
	                //controllo che effettivamente il numero di colonne dell'indice corrisponda a 
	                //quelle della riga corrente
	                if (campi.length == headers.length) {
	                    c = new Contact();
	                    
	                    //trasformo l'array di stringhe contenenti i campi della riga in una arraylisty
	                    fieldsList = Arrays.asList(campi);
	                    
	                    //controllo se nella lista di campi dell'intestazione sia presente "name"
	                    if (headerList.contains(NOME_FIELD)) {
	                    //salvo l'indice in cui si trova nella lista dell'intestazione	
	                        index = headerList.indexOf(NOME_FIELD);
	                        //svuoto lo stringbuilder
	                        sb.setLength(0);
	                        //cerco nella lista dei campi della riga il valore alla posizione indicata
	                        //da index (essendo salvati nello stesso ordine sui due arraylist, il valore nella posizione
	                        //in quello di intestazione corrisponderà a quello nella riga
	                        sb.append(fieldsList.get(index).trim());
	                        //uso il valore ottenuto per settare il valore 
	                        c.setNome(sb.toString());        
                    }
				 
				//DA CONTINUARE
	                    /*if(headerList.contains(COGNOME_FIELD)) {
	                    	index=
	                    }*/
					
					
						
						
						
					
						
						//DEVO AGGIUNGERE l'oggetto dopo averlo settato per poterlo avere nella mia lista che cotiene contatti
						contatti.add(c);
					} else {
						//è buona norma tenere una traccia di tutte le linee che sono sbagliate
						System.out.println("La seguente linea non è corretta: "+riga);
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
	
	
	
	
	public List<MioContact> loadRubricaFromXML(String pathFile) throws ParserConfigurationException {
		List<MioContact> contacts =null;
		try {
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
			contacts = new ArrayList<MioContact>();
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
	
	public void writeRubricaXML(List<MioContact> contatti, String pathFile) {
		
			try {
				/*
				 * crea un'istanza di DocumentBuilderFactory, che rappresenta 
				 * una factory per oggetti DocumentBuilder. La factory è 
				 * utilizzata per configurare e creare istanze di DocumentBuilder, 
				 * che a sua volta verrà utilizzato per creare documenti XML.
				 */
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				
				/*
				 * crea un oggetto DocumentBuilder utilizzando la DocumentBuilderFactory 
				 * precedentemente creata. L'oggetto DocumentBuilder è responsabile della 
				 * creazione e della manipolazione del documento XML.
				 */
				DocumentBuilder db = dbf.newDocumentBuilder();
				
				/*
				 * crea un nuovo oggetto Document, che rappresenta il documento XML vuoto. 
				 * L'oggetto Document sarà utilizzato come punto di partenza per costruire 
				 * la struttura del documento XML.
				 * Document document = db.newDocument();
				 */
				
				
				//VALUTAZIONE PER SCRITTURA IN APPEND con if-else
				Document document;
				
				//mi serve per verificare l'esistenza del file
	            File file = new File(pathFile);
	            Element root;

	            if (file.exists()) {
	                // Il file esiste già, carico il contenuto esistente
	                document = db.parse(file);
	                
	                //recupero l'elemento radice
	                root = document.getDocumentElement();
	            } else {
	            	
	                // Il file non esiste, creo un nuovo documento
	                document = db.newDocument();  
	                
	                /*
					 * BUONA PRATICA
					 * creare un elemento radice separato e aggiungerlo al documento può essere una pratica 
					 * comune e utile in molti casi. Questo ti consente di avere un punto di partenza chiaro 
					 * e organizzato per costruire la struttura del tuo documento XML. Puoi aggiungere elementi 
					 * figlio all'elemento radice e costruire la struttura gerarchica del documento in modo più ordinato.
					 */
	              //viene creato un elemento radice chiamato "contacts" utilizzando il metodo createElement()
	                root = document.createElement("contacts");
	                
	              //l'elemento radice chiamato "contacts" e viene impostato come elemento radice del documento XML   
	                document.appendChild(root);
	            }
				
				
				
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
				Element nuovoContatto = null;
				
				for(MioContact c : contatti) {
					if(c!=null) {
						//Per ogni contatto, viene creato un nuovo elemento "contact" 
						nuovoContatto = document.createElement(CONTACT_FIELD);
						
						/*
						 *  impostato un attributo "age" con il valore "50" per ogni elemento 
						 *  "contact" utilizzando il metodo setAttribute()
						 *  nuovoContatto.setAttribute("age", "50");
						 */
						
						
						/*
						 * i dati del contatto (nome, cognome, numero di telefono, email e note) 
						 * vengono controllati e, se non sono nulli, vengono creati gli elementi 
						 * corrispondenti utilizzando document.createElement() e vengono impostati 
						 * i loro contenuti utilizzando setTextContent().
						 * ogni elemento figlio viene aggiunto all'elemento "contact" (ovvero
						 * nuovoContatto) usando appendChild()
						 */
						if(c.getName()!=null) {
							Element name=document.createElement(NAME_FIELD);
							//crea un nodo di testo per l'elemento name
							name.setTextContent(c.getName());
							nuovoContatto.appendChild(name);
						}
						if(c.getSurname()!=null) {
							Element surname=document.createElement(SURNAME_FIELD);
							surname.setTextContent(c.getSurname());
							nuovoContatto.appendChild(surname);
						}
						if(c.getPhoneNumber()!=null) {
							Element phone=document.createElement(PHONE_FIELD);
							phone.setTextContent(c.getPhoneNumber());
							nuovoContatto.appendChild(phone);
						}
						if(c.getEmail()!=null) {
							Element email=document.createElement(EMAIL_FIELD);
							email.setTextContent(c.getEmail());
							nuovoContatto.appendChild(email);
						}
						if(c.getNote()!=null) {
							Element note=document.createElement(NOTE_EN_FIELD);
							note.setTextContent(c.getNote());
							nuovoContatto.appendChild(note);
						}
						
						/*
						 * dopo aver aggiunto i vari elementi all'elemento madre
						 * nuovoContatto, questo viene aggiunto alla radice 
						 * (ovvero contacts)
						 */
						root.appendChild(nuovoContatto);
					} else {
						System.out.println("Missing contact");
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
				TransformerFactory tf = TransformerFactory.newInstance();
				
				/*
				 * crea un oggetto Transformer utilizzando la TransformerFactory 
				 * precedentemente creata. L'oggetto Transformer è responsabile di 
				 * trasformare il documento XML in una forma leggibile.
				 */
				Transformer transformer = tf.newTransformer();
				
				/*
				 * crea un oggetto DOMSource utilizzando il documento XML (document) come 
				 * sorgente dei dati per la trasformazione. L'oggetto DOMSource rappresenta 
				 * il contenuto del documento XML da trasformare.
				 */
				DOMSource source = new DOMSource(document);
				
				//QUI PENSO DOVER AGIRE PER LA SCRITTURA IN APPEND
				
				/*
				 * OUTPUT PER FILE XML
				 * crea un oggetto StreamResult utilizzando un oggetto File per specificare 
				 * il percorso e il nome del file XML in cui verrà scritto il contenuto 
				 * trasformato
				 */
				StreamResult result = new StreamResult(new File("/temp/contacts.xml"));
		
				// OUTPUT PER CONSOLE
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
}
