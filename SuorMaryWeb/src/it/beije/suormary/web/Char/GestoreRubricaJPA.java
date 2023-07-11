package it.beije.suormary.web.Char;

import java.util.List;



import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

public class GestoreRubricaJPA {

	public static void main(String[] args)  {
		  EntityManager entityManager = null;
		Scanner scanner = new Scanner(System.in);
		String scelta = "";
        while(!scelta.equals("esci")) {
        	
    		System.out.println("Scegli una tra le seguenti opzioni : ");
    		System.out.println("lista contatti");
    		System.out.println("cerca contatto");
    		System.out.println("nuovo contatto");
    		System.out.println("modifica contatto");
    		System.out.println("cancella contatto");
    		System.out.println("trova contatti duplicati");
    		System.out.println("unisci contatti duplicati");
    		System.out.println("esporta contatti in file CSV");
    		System.out.println("esporta contatti in file XML");
    		System.out.println("importa contatti da file CSV");
    		System.out.println("importa contatti da file XML");
    		System.out.println("esci");
    		System.out.println("--------");
            scelta = scanner.nextLine();
           
      try {
   	      entityManager = JPAmanagerFactory.createEntityManager();
    	   switch(scelta) {
          case "lista contatti" :List<Contact> contacts =  RubricaJPA.loadRubricaJPA(entityManager); for(Contact c : contacts) System.out.println(c); break;
          case "cerca contatto" : RubricaJPA.findContact(entityManager); break;
          case "nuovo contatto" : RubricaJPA.findContactById(entityManager,"13"); break;        
//          case "modifica contatto" : RubricaJPA.updateContact(entityManager); break;
//          case "cancella contatto" : RubricaJPA.deleteContact(entityManager); break;
//          case "trova contatti duplicati" : RubricaJPA.findDuplicatedContacts(entityManager); break;
//          case "unisci contatti duplicati" : RubricaJPA.mergeDuplicatedContacts(entityManager); break;
//          case "esporta contatti in file CSV" : RubricaJPA.exportDbToCSV(entityManager); break;
//          case "esporta contatti in file XML" : RubricaJPA.exportDbToXML(entityManager); break;
//          case "importa contatti da file CSV" : RubricaJPA.exportCSVToDb(entityManager); break;
//          case "importa contatti da file XML" : RubricaJPA.exportXMLToDb(entityManager); break;
          case "esci" : System.out.println("Arrivederci"); break;
          default : System.out.println("Non hai inserito nessuna tra le opzioni disponibili"); break;
        }
      } catch(Exception e) {
    	  e.printStackTrace();
      } 
        
	    }
        entityManager.close();
        scanner.close();
	}

}
