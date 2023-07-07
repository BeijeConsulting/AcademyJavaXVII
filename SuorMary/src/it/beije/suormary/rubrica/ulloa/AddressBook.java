package it.beije.suormary.rubrica.ulloa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressBook {
	private List<Contact> contatti;

    public AddressBook() {
        // Inizializzazione della lista di contatti
    	this.contatti = new ArrayList<Contact>();
    }
    
    public AddressBook(List<Contact> contatti) {
        this.contatti = contatti;
    }
    
    public void aggiungiContatto(Contact contatto) {
    	contatti.add(contatto);
    }

    public void modificaContatto(Contact contattoDaModificare, Contact contatto) {
    	// Cerca il contatto da modificare nella lista
        int index = contatti.indexOf(contattoDaModificare);
        if (index != -1) {
            // Se il contatto è presente, sostituisci l'elemento con quello aggiornato
            contatti.set(index, contatto);
        }
    }

    public void cancellaContatto(Contact contatto) {
        // Implementazione
    	contatti.remove(contatto);
    }
    
    public List<Contact> getContattiOrdinatiPerNome() {
        // Crea una copia della lista di contatti
        List<Contact> contattiCopia = new ArrayList<Contact>(contatti);
        // Ordina la lista copia in base al nome dei contatti
        Collections.sort(contattiCopia, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return contattiCopia;
    }
    
    public List<Contact> getContattiOrdinatiPerCognome() {
        // Crea una copia della lista di contatti
        List<Contact> contattiCopia = new ArrayList<Contact>(contatti);
        // Ordina la lista copia in base al cognome dei contatti
        Collections.sort(contattiCopia, (c1, c2) -> c1.getSurname().compareToIgnoreCase(c2.getSurname()));
        return contattiCopia;
    }

    public List<Contact> getContatti() {
        return contatti;
    }
    
    public Contact getContattoById(int id) {
        for (Contact contact : contatti) {
            if (contact.getId() == id) {
                return contact; // Restituisce il contatto corrispondente all'ID
            }
        }
        return null; // Se non viene trovato alcun contatto con l'ID specificato
    }
    
    public List<Contact> cercaContatto(String nome, String cognome) {
    	List<Contact> contattiTrovati = new ArrayList<>();
        for (Contact contatto : contatti) {
            if (contatto.getName().equalsIgnoreCase(nome) && contatto.getSurname().equalsIgnoreCase(cognome)) {
            	contattiTrovati.add(contatto);
            }
        }
        return contattiTrovati; // Contatto non trovato
    }
    
    public List<Contact> trovaContattiDuplicati(boolean cancellare) {
    	List<Contact> duplicati = new ArrayList<Contact>();
    	//List<Contact> contattiRidotti = new ArrayList<Contact>();
        Set<String> contattiUnici = new HashSet<>();
        
        
        for (Contact contatto : contatti) {
            // Genera una stringa rappresentante il contatto senza l'ID
            String contattoSenzaId = contatto.toStringExcludingId();

            // Se la stringa esiste già nell'insieme, il contatto è un duplicato
            if (contattiUnici.contains(contattoSenzaId)) {
            	duplicati.add(contatto);
            } else {
                contattiUnici.add(contattoSenzaId);
            }
        }
        
        if(!cancellare) {
        	/*for (Contact contatto : contattiRidotti) {
                if (duplicati.stream().anyMatch(dup -> dup.contains(contatto))) {
                    duplicati.add(contatto);
                }
            }*/
        	List<Contact> contattiUnivoci = new ArrayList<Contact>(contatti);
        	contattiUnivoci.removeAll(duplicati);
        	List<Contact> contattiDuplicati = new ArrayList<Contact>(contatti);
        	contattiDuplicati.removeAll(contattiUnivoci);
        	duplicati = contattiDuplicati;
           
        }
        return duplicati;

    }
    
    public List<Contact> unisciContattiDuplicati() {
        List<Contact> duplicatiEliminati = trovaContattiDuplicati(true);
        for (Contact contatto : duplicatiEliminati) {
        	cancellaContatto(contatto);
        }
        return duplicatiEliminati;
    }

	public void setContatti(List<Contact> contatti) {
		this.contatti = contatti;
	}
    
}
