package exercises;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GestoreRubrica {

	private String nomeDB = "jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET";
	private String account = "root";
	private String password = "Arlabunakti";
	private Scanner ts = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		GestoreRubrica gr = new GestoreRubrica();
		gr.menu(gr);
		
		RubricaUtils ru = new RubricaUtils();
		ToolImportExport tie = new ToolImportExport();
		
		

	}
	
	public void menu(GestoreRubrica gr) {
		ts = new Scanner(System.in);
		System.out.println("benvenuto nel gestore rubrica");
		System.out.println("sono presenti le funzionalità indicate, cliccare il numero corrispondente all'azione desiderata");
		System.out.println("per vedere la lista contatti (con possibilità di ordinare per nome e cognome a scelta) 1\r\n"
				+ "per cercare un nuovo contatto digita 2\r\n"
				+ "per inserire un nuovo contatto digita 3\r\n"
				+ "per modificare un contatto digita 4\r\n"
				+ "per cancellare un contatto digita 5\r\n"
				+ "per trovare contatti duplicati digita 6\r\n"
				+ "per unire contatti duplicati digita 7");
		
		System.out.println();
		System.out.println("inserisci il valore:");
		int scelta=-1;
		
		do{
			scelta=ts.nextInt();
			System.out.println();
			
			if(scelta == 1) { 
				System.out.println("hai scelto di vedere la lista contatti!");
				System.out.println("Vuoi ordinare con qualche criterio la visualizzazione? Scrivi si o no");
				String siNo= ts.next();
				
				String ordine=null;
				if(siNo.equalsIgnoreCase("no")) {
					System.out.println("hai scelto di visualizzare senza ordinamento specifico!");
					ordine="default";
					System.out.println(ordine);
				} else if(siNo.equalsIgnoreCase("si")){
					System.out.println("Se preferisci visualizzare in ordine di nome premi N, se preferisci in ordine di cognome premi C");
					
					do {
						ordine = ts.next();
						if(ordine.equalsIgnoreCase("C")) {
							System.out.println("hai scelto di ordinare per cognome!");
							System.out.println(ordine);
							System.out.println(!ordine.equalsIgnoreCase("C") || !ordine.equalsIgnoreCase("N"));
						}
						else if(ordine.equalsIgnoreCase("N")) {
							System.out.println("hai scelto di ordinare per nome!");
						}else if(!ordine.equalsIgnoreCase("C") && !ordine.equalsIgnoreCase("N")) {
							System.out.println("OPS! Carattere non valido, inserisci C per cognome o N per nome!");
							ordine="default";
						}
					} while(!ordine.equalsIgnoreCase("C") && !ordine.equalsIgnoreCase("N"));
				
				} else if(!siNo.equalsIgnoreCase("no") && !siNo.equalsIgnoreCase("si")) {
					System.out.println("la sequenza non è riconosciuta, i contatti verranno mostrati senza un ordinamento specifico");
					ordine = "defaul";
				}
						
				System.out.println(".....");
				//AZIONI
				//MOSTRO CONTATTI QUINDI USO LOAD
				gr.mostraContatti(ordine);
				
			}
			
				
			if(scelta == 2) {
				System.out.println("hai scelto di cercare un contatto!");
				
				//AZIONI
				gr.cercaContatto();
				
			}
			if(scelta == 3) {
				System.out.println("hai scelto di inserire un nuovo contatto!");
				
				gr.inserisciContatto();
			}
			if(scelta == 4) {
				System.out.println("hai scelto di modificare un contatto!");
				
				gr.modificaContatto();
			}
			if(scelta == 5) {
				System.out.println("hai scelto di cancellare un contatto!");
				
				gr.cancellaContatto();
			}
			if(scelta == 6) {
				System.out.println("hai scelto di trovare contatti duplicati!");
				cercaDuplicati();
			}
			if(scelta == 7) {
				System.out.println("hai scelto di unire contatti duplicati!");
			}
			if(scelta<1 || scelta >7) {
				System.out.println("OPS! Hai inserito un valore non corretto! Prova di nuovo");
			}
		}while(scelta<1 || scelta >7);
		
		
		
		
	}
	
	public void  mostraContatti(String ordine) {
		RubricaUtils ru = new RubricaUtils();
		List<Contact> contatti=ru.loadRubricaFromDBOrdinata(ordine);
		
		//MOSTRO QUELLI CHE HO NEL DB
		 
		
		for(Contact c : contatti) {
			System.out.println(c.toString());
		}
		
				
		
		
	}

	public void cercaContatto() {
		
		//verifica se il contatto dato per nome e cognome sia presente e ottengo nome e cognome da cercare nel db
		//POTREI CHIEDERE DI RIFARE LA RICERCA SE IL CONTATTO NON ESISTE
		String valori = search();
		String [] val = valori.split(" ");
		String nome=val[0];
		String cognome=val[1];
		//System.out.println(nome);
		
		//salva tutte le info relative al contatto
		List<Contact> contatti=info(nome, cognome);
		
		for(Contact c : contatti) {
			System.out.println(c.toString());
		}
	}
	

	
	public List<Contact> info(String name, String surname) {
		
		RubricaUtils ru = new RubricaUtils();
		List<Contact> contatti=ru.loadRubricaFromDBCerca(name, surname);
		
		return contatti;
		
	}
	
	public Map<Integer, String> searchMap(){
		ts = new Scanner(System.in);
		RubricaUtils ru = new RubricaUtils();
		
		Map<Integer, String> map = ru.searchID();
		
		Set<Integer> id = map.keySet();
		
		/*for(Integer i : id) {
			System.out.println(i);
		}*/
		
		System.out.println("inserisci nome e cognome del contatto");
		String nomeCognome = ts.nextLine();
		while(!map.containsValue(nomeCognome)) {
			System.out.println("OPS! contatto inesistente, inseriscine uno nuovo");
			nomeCognome=ts.nextLine();
			//searchMap();	
		}
		return map;
	}
	
	public String search(){
		ts = new Scanner(System.in);
		RubricaUtils ru = new RubricaUtils();
		
		Map<Integer, String> map = ru.searchID();
		
		Set<Integer> id = map.keySet();
		
		/*for(Integer i : id) {
			System.out.println(i);
		}*/
		
		System.out.println("inserisci nome e cognome del contatto");
		String nomeCognome = ts.nextLine();
		while(!map.containsValue(nomeCognome)) {
			System.out.println("OPS! contatto inesistente, inseriscine uno nuovo");
			nomeCognome=ts.nextLine();
			//searchMap();
		}
		return nomeCognome;
	}
	
	//VOLENDO POSSO CHIEDERE SE VUOLE INSERIRE ULTERIORI CONTATTI, HO GIà UNA LIST PRONTA DA RIEMPIRE
	public void inserisciContatto() {
		ts = new Scanner(System.in);
		System.out.println("inserisci nome");
		String name = ts.nextLine();
		System.out.println("inserisci cognome");
		String surname = ts.nextLine();
		System.out.println("inserisci numero telefono");
		String phoneNumber = ts.nextLine();
		System.out.println("vuoi inserire una mail? rispondi si per inserire mail, qualsiasi altra combinazione di caratteri sarà"
				+ "valutata come no");
		String risposta = ts.nextLine();
		String email;
		if(risposta.equalsIgnoreCase("si")) {
			System.out.println("inserisci la mail!");
			email=ts.nextLine();
		} else {
			email=null;
		}
		System.out.println("vuoi inserire una nota? rispondi si per inserire mail, qualsiasi altra combinazione di caratteri sarà"
				+ "valutata come no");
		risposta = ts.nextLine();
		String note;
		if(risposta.equalsIgnoreCase("si")) {
			System.out.println("inserisci la nota!");
			note=ts.nextLine();
		} else {
			note=null;
		}
		
		//creo il contatto
		Contact c = new Contact();
		c.setName(name);
		c.setSurname(surname);
		c.setPhoneNumber(phoneNumber);
		c.setEmail(email);
		c.setNote(note);
		
		List<Contact> contatti = new ArrayList<>();
		contatti.add(c);
		
		RubricaUtils ru = new RubricaUtils();
		ru.writeRubricaDBInsert(contatti);
	}
	
	
	
	public void modificaContatto() {
		//mostro a console il contatto cercato tramite nome e cognome
		cercaContatto();
		
		//map.values()
		
		System.out.println("inserisci l'id del contatto che vuoi modificare tra quelli mostrati");
		String id = ts.next();
		
		//devo ottenere
		System.out.println("come desideri modificare? inserisci nome o cognome o telefono o email o note per modificare il"
				+ "campo desiderato. Altre parole saranno valutate come la volontà di non effettuare alcuna modifica.");
		String campo = ts.next();
		if(!campo.equalsIgnoreCase("nome") && !campo.equalsIgnoreCase("cognome") && !campo.equalsIgnoreCase("telefono")
				&& !campo.equalsIgnoreCase("email") && !campo.equalsIgnoreCase("note")) {
			System.out.println("hai scelto di non apportare modifiche");
		} else {
		
			
		System.out.println("inserisci il nuovo valore");
		String valore = ts.next();
		RubricaUtils ru = new RubricaUtils();
		Contact c = ru.writeFromDBSet(campo, valore, id);
		
		if(c!=null) {
			System.out.println("ecco le modifiche effettuate");
			System.out.println(c.toString());
		}
		
		}
		
		
		
	}
	
	//TO DO: ho modificato cercaContatto in modo che mi mostra anche l'id (l'ho aggiunto tra gli attributi di Contact
	//così posso far scegliere di cancellare un contatto sulla base dell'id
	public void cancellaContatto() {
		cercaContatto();

		System.out.println("Quelli appena mostrati sono il/i contatti sulla base dei tuoi parametri di ricerca");
		System.out.println("Scrivi il numero di id del contatto che vuoi cancellare");
		
		//per ora non considero l'opzione per cui l'utente non selezioni uno tra gli id mostrati
		String id = ts.next();
		RubricaUtils ru = new RubricaUtils();
		ru.writeFromDBDelete(id);
		
	}
	
	//decidere condizione groupby
	public void cercaDuplicati() {
		RubricaUtils ru=new RubricaUtils();
		List<Contact>contatti=ru.groupBy();
		
		for(Contact c : contatti) {
			System.out.println(c.toString());
		}
	}
	
	
}
