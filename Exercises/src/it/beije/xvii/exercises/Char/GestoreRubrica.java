package it.beije.xvii.exercises.Char;
import java.util.Scanner;
import java.util.List;
public class GestoreRubrica {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String scelta = "";
        while(!scelta.equals("esci")) {  	
    		System.out.println("Scegli una tra le seguenti opzioni : ");
    		System.out.println("lista contatti");
    		System.out.println("nuovo contatto");
    		System.out.println("modifica contatto");
    		System.out.println("cancella contatto");
    		System.out.println("trova contatti duplicati");
    		System.out.println("unisci contatti duplicati");
    		System.out.println("esci");
            scelta = scanner.nextLine();
      
         switch(scelta) {
          case "lista contatti" : List<Contact> contacts = RubricaJDBC.loadRubricaJDBC(); 
        						for(Contact c : contacts) {
        							System.out.println(c);
        						}
        						break;
          case "nuovo contatto" : RubricaJDBC.createContactFromRubrica(); break;
          case "modifica contatto" : RubricaJDBC.updateContactFromRubrica(); break;
          case "cancella contatto" : RubricaJDBC.deleteContactFromRubrica(); break;
          case "trova contatti duplicati" : RubricaJDBC.findDuplicatedContacts(); break;
          case "unisci contatti duplicati" : RubricaJDBC.mergeDuplicatedContacts(); break;
          case "esci" : System.out.println("Arrivederci"); break;
          default : System.out.println("Non hai inserito nessuna tra le opzioni disponibili"); break;
        }

	    }
        scanner.close();
	}

}
