package it.beije.suormary.rubrica.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    
    public Contact getContattoById(String id) {
        for (Contact contact : contatti) {
            if (contact.getId().equals(id)) {
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
    
    public List<Contact> trovaContattiDuplicati() {
        List<Contact> duplicati = new ArrayList<>();
        for (int i = 0; i < contatti.size(); i++) {
            Contact contattoCorrente = contatti.get(i);
            for (int j = i + 1; j < contatti.size()-1; j++) {
                Contact contattoSuccessivo = contatti.get(j);
                if (contattoCorrente.equals(contattoSuccessivo)) {
                    duplicati.add(contattoCorrente);
                    duplicati.add(contattoSuccessivo);
                }
            }
        }
        return duplicati;
    }
    
    
    public List<Contact> unisciContattiDuplicati(List<Contact> duplicati) {
        List<Contact> duplicatiEliminati = new ArrayList<Contact>();
        for (int i = 0; i < duplicati.size(); i++) {
            Contact contatto = duplicati.get(i);
            if (i > 0) {
                duplicatiEliminati.add(contatto);
                cancellaContatto(contatto);
            }
        }
        return duplicatiEliminati;
    }

	public void setContatti(List<Contact> contatti) {
		this.contatti = contatti;
	}
    
}
