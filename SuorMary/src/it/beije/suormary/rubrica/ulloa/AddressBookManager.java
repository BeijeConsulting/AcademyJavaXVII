package it.beije.suormary.rubrica.ulloa;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class AddressBookManager {
	
	private Scanner scanner;
	private AddressBook rubrica;
	private RubricaUtils rUtils;
	private RubricaToolsDB rToolsDB;
	
	
	public AddressBookManager() {
		rUtils = new RubricaUtils();
		scanner = new Scanner(System.in);
        //rubrica = new AddressBook(rUtils.loadRubricaFromJDBC());
		//rubrica = new AddressBook(rUtils.loadRubricaFromHBM());
		rubrica = new AddressBook(rUtils.loadRubricaFromJPA());
		rToolsDB = new RubricaToolsDB();
    }
	
	public void runCLI () {
		
		boolean running = true;
		
	    while (running) {
	        System.out.println("----- Menu Rubrica -----");
	        System.out.println("1. Visualizza lista contatti");
	        System.out.println("2. Cerca contatto");
	        System.out.println("3. Inserisci nuovo contatto");
	        System.out.println("4. Modifica contatto");
	        System.out.println("5. Cancella contatto");
	        System.out.println("6. Trova contatti duplicati");
	        System.out.println("7. Unisci contatti duplicati");
	        System.out.println("0. Esci");
	        System.out.println();
	        
	        System.out.print("Scelta: ");
	        String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga
	        System.out.println("------------------------");
	        switch (choice) {
	            case "1":
	            	visualizzaListaContatti();
	                break;
	            case "2":
	                // Cerca contatto
	            	cercaContatto();
	                break;
	            case "3":
	                // Inserisci nuovo contatto
	            	inserisciNuovoContatto();
	                break;
	            case "4":
	                // Modifica contatto
	            	modificaContatto();
	                break;
	            case "5":
	                // Cancella contatto
	            	cancellaContatto();
	                break;
	            case "6":
	                // Trova contatti duplicati
	            	trovaContattiDuplicati();
	                break;
	            case "7":
	                // Unisci contatti duplicati
	            	unisciContattiDuplicati();
	                break;
	            case "0":
	                running = false;
	                System.out.println("Arrivederci!");
	                scanner.close();
	                //RubricaUtils.closeEntityManager();
	                break;
	            default:
	                System.out.println("Scelta non valida.");
	                break;
	        }
	    }
	
	    scanner.close();
	    }
	
	public void printContactList(List<Contact> contacts, boolean salvataggio) {	    
	    if (contacts.isEmpty()) {
	        System.out.println("La lista dei contatti è vuota.");
	    } else {
	    	System.out.println("=== Lista Contatti ===");
	        for (Contact contact : contacts) {
	        	System.out.println(contact.toString()); 
	        }
	        System.out.println();
	        
	        if(salvataggio) {
	        	salvataggio(contacts);
	        }
	    }
	}
		
	private void aggiornaRubrica() {
		//rubrica = new AddressBook(rUtils.loadRubricaFromJDBC());
		//rubrica = new AddressBook(rUtils.loadRubricaFromHBM());
		rubrica = new AddressBook(rUtils.loadRubricaFromJPA());
		if (rubrica.getContatti().isEmpty()) {
	        System.out.println("Nessun contatto presente nella rubrica.");
	        return;
	    }
	}
	
	private void salvataggio(List<Contact> contacts) {
		boolean running = true;
		String pathFile;
	    while (running) {
	    	System.out.println("=== Vuoi salvare questi dati? ===");
		    System.out.println("1. Sì, in un file csv");
	        System.out.println("2. Sì, in un file xml");
	        System.out.println("0. No");
	        System.out.println();
	        
	        System.out.print("Scelta: ");
	        String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga
	        System.out.println("------------------------");
	        switch (choice) {
	            case "1":
	            	System.out.print("Inserisci il percorso/nome del file csv dove vuoi salvare i dati: ");
			        pathFile = scanner.nextLine();
			        rToolsDB.exportToCSV(pathFile, "\";\"", contacts);
			        System.out.println("Dati salvati nel file");
			        System.out.println("------------------------");
	                break;
	            case "2":
	            	System.out.print("Inserisci il percorso/nome del file xml dove vuoi salvare i dati: ");
			        pathFile = scanner.nextLine();
			        rToolsDB.exportToXML(pathFile, contacts);
			        System.out.println("Dati salvati nel file");
			        System.out.println("------------------------");
	                break;
	            case "0":
	                running = false;
	                break;
	            default:
	                System.out.println("Scelta non valida.");
	                break;
	        }
	    }
	    
    }
	
	private void inserimentoDatiFile() {
		boolean running = true;
		boolean runningInterno=false;
		String pathFile;
		String separatore;
		List<Contact> contattiFile;
	    while (running) {
	    	System.out.println("=== Vuoi inserire contatti tramite un file? ===");
		    System.out.println("1. Sì, da un file csv");
	        System.out.println("2. Sì, da un file xml");
	        System.out.println("0. No");
	        System.out.println();
	        
	        System.out.print("Scelta: ");
	        String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga
	        System.out.println("------------------------");
	        switch (choice) {
	            case "1":
	            	System.out.print("Inserisci il percorso/nome del file csv: ");
			        pathFile = scanner.nextLine();
			        System.out.print("Inserisci il separatore: ");
			        separatore = scanner.nextLine();
			        if(new File(pathFile).exists()) {
			        	contattiFile = rUtils.loadRubricaFromCSV(pathFile, separatore);
			        	System.out.println("Questi sono i contatti presi dal file indicato");
			        	printContactList(contattiFile, false);
			        	runningInterno = true;
						while (runningInterno) {
							System.out.println("------------------------");
						    System.out.println("1. Conferma inserimento");
						    System.out.println("0. Annulla inserimento");

						    System.out.print("Seleziona un'opzione: ");
						    choice = scanner.next();
					        scanner.nextLine(); // Consuma la nuova riga
					        System.out.println("------------------------");
					        
						    switch (choice) {
						    case "1": rToolsDB.importFromCSV(pathFile, separatore);
				        			  System.out.println("Dati salvati nella rubrica");
						    		  System.out.println("------------------------");
						    		  runningInterno = false;
						    		  
						    		  break;
						    case "0": System.out.println("Hai annullato l'inserimento!");
						    		  runningInterno = false; // Torna al menu esterno
						    		  break;
						    default:
					            System.out.println("Opzione non valida.");
					            break;
						    }
						}
			        } else System.out.println("Il percorso file non esiste");
	                break;
	            case "2":
	            	System.out.print("Inserisci il percorso/nome del file xml: ");
			        pathFile = scanner.nextLine();
			        if(new File(pathFile).exists()) {
			        	contattiFile = rUtils.loadRubricaFromXML(pathFile);
			        	System.out.println("Questi sono i contatti presi dal file indicato");
			        	printContactList(contattiFile, false);
			        	runningInterno = true;
						while (runningInterno) {
							System.out.println("------------------------");
						    System.out.println("1. Conferma inserimento");
						    System.out.println("0. Annulla inserimento");

						    System.out.print("Seleziona un'opzione: ");
						    choice = scanner.next();
					        scanner.nextLine(); // Consuma la nuova riga
					        System.out.println("------------------------");
					        
						    switch (choice) {
						    case "1": rToolsDB.importFromXML(pathFile);
				        			  System.out.println("Dati salvati nella rubrica");
						    		  System.out.println("------------------------");
						    		  runningInterno = false;
						    		  
						    		  break;
						    case "0": System.out.println("Hai annullato l'inserimento!");
						    		  runningInterno = false; // Torna al menu esterno
						    		  break;
						    default:
					            System.out.println("Opzione non valida.");
					            break;
						    }
						}
			        } else System.out.println("Il percorso file non esiste");
	                break;
	            case "0":
	                running = false;
	                break;
	            default:
	                System.out.println("Scelta non valida.");
	                break;
	        }
	    }
	    
    }
	
	private void visualizzaListaContatti() {
        // Implementa la logica per visualizzare la lista contatti
        // con possibilità di ordinamento per nome e cognome a scelta
		aggiornaRubrica();
	    
	    boolean running = true;
		
	    while (running) {
	    	System.out.println("=== Lista Contatti ===");
		    System.out.println("1. Ordina per nome");
		    System.out.println("2. Ordina per cognome");
		    System.out.println("3. Nessun criterio di ordinamento");
		    System.out.println("0. Torna al menu principale");

		    System.out.print("Seleziona un'opzione: ");
		    String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga

		    List<Contact> contattiVisualizzazione;
		    switch (choice) {
		    case "1":
		    	contattiVisualizzazione = rubrica.getContattiOrdinatiPerNome();
	            printContactList(contattiVisualizzazione,true);
	            break;
	        case "2":
	        	contattiVisualizzazione = rubrica.getContattiOrdinatiPerCognome();
	        	printContactList(contattiVisualizzazione,true);
	            break;
	        case "3":
	        	contattiVisualizzazione = rubrica.getContatti();
	        	printContactList(contattiVisualizzazione,true);
	            break;
	        case "0":
	        	running = false; // Torna al menu principale
	        	break;
	        default:
	            System.out.println("Opzione non valida.");
	            break;
	            }
		    
	    }
    }

    private void cercaContatto() {
        // Implementa la logica per cercare un contatto
    	aggiornaRubrica();
    	
	    boolean running = true;
		
	    while (running) {
	    	System.out.println("=== Cerca Contatti ===");
		    System.out.println("1. Continua con la ricerca");
		    System.out.println("0. Torna al menu principale");

		    System.out.print("Seleziona un'opzione: ");
		    String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga

		    List<Contact> contattiVisualizzazione;
		    switch (choice) {
		    case "1":
		    	System.out.print("Inserisci il nome del contatto: ");
		        String nome = scanner.nextLine();

		        System.out.print("Inserisci il cognome del contatto: ");
		        String cognome = scanner.nextLine();
		        List<Contact> contattiTrovati = rubrica.cercaContatto(nome, cognome);
		        if (contattiTrovati.isEmpty()) {
		            System.out.println("Nessun contatto corrisponde alla ricerca.");
		        } else {
		            System.out.println("Contatti trovati:");
		            printContactList(contattiTrovati,true);
		        }
	            break;
	        case "0":
	        	running = false; // Torna al menu principale
	        	break;
	        default:
	            System.out.println("Opzione non valida.");
	            break;
	            }
		    
	    }
    }

    private void inserisciNuovoContatto() {
        // Implementa la logica per inserire un nuovo contatto
    	boolean running = true;
    	boolean runningInterno=false;
		
	    while (running) {
	    	System.out.println("=== Inserisci Contatti ===");
		    System.out.println("1. Continua con l'inserimento manuale");
		    System.out.println("2. Continua con l'inserimento tramite un file");
		    System.out.println("0. Torna al menu principale");

		    System.out.print("Seleziona un'opzione: ");
		    String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga
	        System.out.println("------------------------");
	        String value;
		    switch (choice) {
		    case "1":
		    	Contact contatto = new Contact();
		    	System.out.println("Metti spazio vuoto se non vuoi compilare un campo");
		    	
		    	System.out.print("Inserisci il nome del contatto: ");
		    	value = scanner.nextLine();
		        contatto.setName(value);

		        System.out.print("Inserisci il cognome del contatto: ");
		        value = scanner.nextLine();
		        contatto.setSurname(value);
		        
		        System.out.print("Inserisci il numero di telefono del contatto: ");
		        value = scanner.nextLine();
		        contatto.setPhoneNumber(value);

				System.out.print("Inserisci l'indirizzo email del contatto: ");
				value = scanner.nextLine();
				contatto.setEmail(value);
				
				System.out.print("Inserisci le note del contatto: ");
				value = scanner.nextLine();
				contatto.setNote(value);
				
				runningInterno = true;
				while (runningInterno) {
					System.out.println("------------------------");
				    System.out.println("1. Conferma inserimento");
				    System.out.println("0. Annulla inserimento");

				    System.out.print("Seleziona un'opzione: ");
				    choice = scanner.next();
			        scanner.nextLine(); // Consuma la nuova riga
			        System.out.println("------------------------");
			        
				    switch (choice) {
				    case "1": rubrica.aggiungiContatto(contatto);
				    		  //rUtils.addContactJDBC(contatto);
				    		  //rUtils.addContactHBM(contatto);
				    		  rUtils.addContactJPA(contatto);
				    		  System.out.println("Contatto inserito!");
				    		  System.out.println("------------------------");
				    		  runningInterno = false;
				    		  
				    		  break;
				    case "0": System.out.println("Hai annullato l'inserimento!");
				    		  runningInterno = false; // Torna al menu esterno
				    		  break;
				    default:
			            System.out.println("Opzione non valida.");
			            break;
				    }
				}
				
	            break;
		    case "2": 
		    	inserimentoDatiFile();
	            break;
	        case "0":
	        	running = false; // Torna al menu principale
	        	break;
	        default:
	            System.out.println("Opzione non valida.");
	            break;
	            }
		    
	    }
    }
    
    private void modificaContatto() {
        // Implementa la logica per modificare un contatto
	   
    	boolean running = true;
    	boolean runningInterno=false;
		
	    while (running) {
	    	System.out.println("=== Modifica Contatti ===");
		    System.out.println("1. Continua con la modifica");
		    System.out.println("0. Torna al menu principale");

		    System.out.print("Seleziona un'opzione: ");
		    String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga
	        System.out.println("------------------------");
	        String value;
		    switch (choice) {
		    case "1":
		    	aggiornaRubrica();
		    	printContactList(rubrica.getContatti(),false);
		    	
		    	System.out.print("Inserisci l'ID o l'identificatore del contatto da modificare: ");
		    	int id = scanner.nextInt();
		    	scanner.nextLine(); // Consuma la nuova riga
		    	Contact contattoDaModificare = rubrica.getContattoById(id);
		    	
		    	if (contattoDaModificare != null) {
		    		Contact contatto = new Contact();
			    	System.out.println("Modifica del contatto: \n" + contattoDaModificare);
			    	System.out.println();
			    	
			    	System.out.println("Metti spazio vuoto se non vuoi modificare un campo");
			    	System.out.println("Scrivi blank se vuoi sbiancare un campo");
			    	
			    	System.out.print("Inserisci il nuovo nome del contatto: ");
			    	value = scanner.nextLine();
			    	if(value.trim().equals("")) {
			    		contatto.setName(contattoDaModificare.getName());
			    	} else if(value.trim().equalsIgnoreCase("blank")) {
			    		contatto.setName("");
			    	} else {
			    		contatto.setName(value);
			    	} 

			        System.out.print("Inserisci il nuovo cognome del contatto: ");
			        value = scanner.nextLine();
			        if(value.trim().equals("")) {
			    		contatto.setSurname(contattoDaModificare.getSurname());
			    	} else if(value.trim().equalsIgnoreCase("blank")) {
			    		contatto.setSurname("");
			    	} else {
			    		contatto.setSurname(value);
			    	}
			        
			        System.out.print("Inserisci il nuovo numero di telefono del contatto: ");
			        value = scanner.nextLine();
			        if(value.trim().equals("")) {
			    		contatto.setPhoneNumber(contattoDaModificare.getPhoneNumber());
			    	} else if(value.trim().equalsIgnoreCase("blank")) {
			    		contatto.setPhoneNumber("");
			    	} else {
			    		contatto.setPhoneNumber(value);
			    	}

					System.out.print("Inserisci la nuova email del contatto: ");
					value = scanner.nextLine();
					if(value.trim().equals("")) {
			    		contatto.setEmail(contattoDaModificare.getEmail());
			    	} else if(value.trim().equalsIgnoreCase("blank")) {
			    		contatto.setEmail("");
			    	} else {
			    		contatto.setEmail(value);
			    	}
					
					System.out.print("Inserisci le nuove note del contatto: ");
					value = scanner.nextLine();
					contatto.setNote(value);
					
					if(value.trim().equals("")) {
			    		contatto.setNote(contattoDaModificare.getNote());
			    	} else if(value.trim().equalsIgnoreCase("blank")) {
			    		contatto.setNote("");
			    	} else {
			    		contatto.setNote(value);
			    	}
					
					runningInterno = true;
					while (runningInterno) {
						System.out.println("------------------------");
					    System.out.println("1. Conferma modifica");
					    System.out.println("0. Annulla modifica");

					    System.out.print("Seleziona un'opzione: ");
					    choice = scanner.next();
				        scanner.nextLine(); // Consuma la nuova riga
				        System.out.println("------------------------");
				        
					    switch (choice) {
					    case "1": rubrica.modificaContatto(contattoDaModificare, contatto);
					    		  //rUtils.updateContactJDBC(id, contatto);
					    		  //rUtils.updateContactHBM(id, contatto);
					    		  rUtils.updateContactJPA(id, contatto);
					    		  System.out.println("Contatto modificato!");
					    		  System.out.println("------------------------");
					    		  runningInterno = false;
					    		  
					    		  break;
					    case "0": System.out.println("Hai annullato la modifica!");
					    		  runningInterno = false; // Torna al menu esterno
					    		  break;
					    default:
				            System.out.println("Opzione non valida.");
				            break;
					    }
					}
		    	} else {
		            System.out.println("Nessun contatto trovato con l'ID specificato.");
		        }
		    	
				break;
	        case "0":
	        	running = false; // Torna al menu principale
	        	break;
	        default:
	            System.out.println("Opzione non valida.");
	            break;
	            }
		    
	    }
    }

    private void cancellaContatto() {
        // Implementa la logica per cancellare un contatto
    	boolean running = true;
    	boolean runningInterno=false;
		
	    while (running) {
	    	System.out.println("=== Cancella Contatti ===");
		    System.out.println("1. Continua con la cancellazione");
		    System.out.println("0. Torna al menu principale");

		    System.out.print("Seleziona un'opzione: ");
		    String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga
	        System.out.println("------------------------");
		    switch (choice) {
		    case "1":
		    	aggiornaRubrica();
		    	printContactList(rubrica.getContatti(),false);
		    	
		    	System.out.print("Inserisci l'ID o l'identificatore del contatto da cancellare: ");
		    	int id = scanner.nextInt();
		    	Contact contattoDaCancellare = rubrica.getContattoById(id);
		    	
		    	if (contattoDaCancellare != null) {
			    	System.out.println("Cancellazione del contatto: \n" + contattoDaCancellare);
			    	System.out.println();
			    	
					runningInterno = true;
					while (runningInterno) {
						System.out.println("------------------------");
					    System.out.println("1. Conferma cancellazione");
					    System.out.println("0. Annulla cancellazione");

					    System.out.print("Seleziona un'opzione: ");
					    choice = scanner.next();
				        scanner.nextLine(); // Consuma la nuova riga
				        System.out.println("------------------------");
				        
					    switch (choice) {
					    case "1": rubrica.cancellaContatto(contattoDaCancellare);
					    		  //rUtils.deleteContactJDBC(id);
					    		  //rUtils.deleteContactHBM(id);
					    		  rUtils.deleteContactJPA(id);
					    		  System.out.println("Contatto cancellato!");
					    		  System.out.println("------------------------");
					    		  runningInterno = false;
					    		  
					    		  break;
					    case "0": System.out.println("Hai annullato la cancellazione!");
					    		  runningInterno = false; // Torna al menu esterno
					    		  break;
					    default:
				            System.out.println("Opzione non valida.");
				            break;
					    }
					}
		    	} else {
		            System.out.println("Nessun contatto trovato con l'ID specificato.");
		        }
		    	
				break;
	        case "0":
	        	running = false; // Torna al menu principale
	        	break;
	        default:
	            System.out.println("Opzione non valida.");
	            break;
	            }
		    
	    }
    }

    private void trovaContattiDuplicati() {
        // Implementa la logica per trovare contatti duplicati
    	boolean running = true;
		
	    while (running) {
	    	System.out.println("=== Contatti Duplicati ===");
		    System.out.println("1. Continua con la visualizzazione");
		    System.out.println("0. Torna al menu principale");

		    System.out.print("Seleziona un'opzione: ");
		    String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga
	        System.out.println("------------------------");
		    switch (choice) {
		    case "1":
		    	aggiornaRubrica();
		    	List<Contact> duplicati = rubrica.trovaContattiDuplicati(false);
		    	//List<Contact> duplicati = rUtils.getDuplicateContacts();
		    	printContactList(duplicati,false);
		    	if (duplicati.isEmpty()) {
		    		running = false;
			    }
				break;
	        case "0":
	        	running = false; // Torna al menu principale
	        	break;
	        default:
	            System.out.println("Opzione non valida.");
	            break;
	            }
		    
	    }
    }

    private void unisciContattiDuplicati() {
        // Implementa la logica per unire contatti duplicati
    	boolean running = true;
    	boolean runningInterno=false;
		
	    while (running) {
	    	System.out.println("=== Unisci Contatti Duplicati===");
		    System.out.println("1. Continua con l'unione");
		    System.out.println("0. Torna al menu principale");

		    System.out.print("Seleziona un'opzione: ");
		    String choice = scanner.next();
	        scanner.nextLine(); // Consuma la nuova riga
	        System.out.println("------------------------");
		    switch (choice) {
		    case "1":
		    	aggiornaRubrica();
		    	List<Contact> duplicati = rubrica.trovaContattiDuplicati(false);
		    	System.out.println("=== Contatti Duplicati ===");
		    	printContactList(duplicati,false);
		    	if (duplicati.isEmpty()) {
		    		running = false;
			    } else {
			    	List<Contact> duplicatiEliminati = rubrica.unisciContattiDuplicati();
			    	System.out.println("=== Contatti Duplicati Da Eliminare===");
			    	printContactList(duplicatiEliminati,false);
			    	
			    	runningInterno = true;
					while (runningInterno) {
						System.out.println("------------------------");
					    System.out.println("1. Conferma unione");
					    System.out.println("0. Annulla unione");

					    System.out.print("Seleziona un'opzione: ");
					    choice = scanner.next();
				        scanner.nextLine(); // Consuma la nuova riga
				        System.out.println("------------------------");
				        
					    switch (choice) {
					    case "1": 
					    		  //rUtils.deleteContactJDBC(duplicatiEliminati);
					    		  //rUtils.deleteContactHBM(duplicatiEliminati);
					    		  rUtils.deleteContactJPA(duplicatiEliminati);
					    		  System.out.println("Unione completata!");
					    		  System.out.println("------------------------");
					    		  runningInterno = false;
					    		  running = false; //finita operazione esci al menu principale
					    		  break;
					    case "0": System.out.println("Hai annullato l'unione!");
					    		  runningInterno = false; // Torna al menu esterno
					    		  break;
					    default:
				            System.out.println("Opzione non valida.");
				            break;
					    }
					}
			    }		    	
				break;
	        case "0":
	        	running = false; // Torna al menu principale
	        	break;
	        default:
	            System.out.println("Opzione non valida.");
	            break;
	            }
		    
	    }
    }
    
    
	
	
	public static void main(String[] args) {
		AddressBookManager manager = new AddressBookManager();
        manager.runCLI();
    }
}
