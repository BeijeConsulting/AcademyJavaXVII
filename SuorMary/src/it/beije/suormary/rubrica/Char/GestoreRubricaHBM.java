package it.beije.suormary.rubrica.Char;

import java.util.Scanner;

import org.hibernate.Session;

import java.util.List;
public class GestoreRubricaHBM {

	public static void main(String[] args)  {

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
            Session session = null;
      try {
    	   session = HBMsessionFactory.openSession();
    	   switch(scelta) {
          case "lista contatti" :List<Contact> contacts =  RubricaHBM.loadRubricaHBM(session); for(Contact c : contacts) System.out.println(c); break;
          case "cerca contatto" : RubricaHBM.findContact(session); break;
          case "nuovo contatto" : RubricaHBM.createContact(session); break;        
          case "modifica contatto" : RubricaHBM.updateContact(session); break;
          case "cancella contatto" : RubricaHBM.deleteContact(session); break;
          case "trova contatti duplicati" : RubricaHBM.findDuplicatedContacts(session); break;
          case "unisci contatti duplicati" : RubricaHBM.mergeDuplicatedContacts(session); break;
          case "esporta contatti in file CSV" : RubricaHBM.exportDbToCSV(session); break;
          case "esporta contatti in file XML" : RubricaHBM.exportDbToXML(session); break;
          case "importa contatti da file CSV" : RubricaHBM.exportCSVToDb(session); break;
          case "importa contatti da file XML" : RubricaHBM.exportXMLToDb(session); break;
          case "esci" : System.out.println("Arrivederci"); break;
          default : System.out.println("Non hai inserito nessuna tra le opzioni disponibili"); break;
        }
      } catch(Exception e) {
    	  e.printStackTrace();
      } finally {
 		 session.close();
 	 }
        
	    }
        scanner.close();
	}

}
