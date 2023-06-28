package it.beije.xvii.exercises.Char;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class GestoreRubrica {

	public static void main(String[] args) {
		System.out.println("Scegli una tra le seguenti opzioni : ");
		System.out.println("lista contatti");
		System.out.println("nuovo contatto");
		System.out.println("modifica contatto");
		System.out.println("cancella contatto");
		System.out.println("trova contatti duplicati");
		System.out.println("unisci contatti duplicati");
		System.out.println("esci");
		Scanner scanner = new Scanner(System.in);
        String scelta = scanner.nextLine();
        switch(scelta) {
        case "lista contatti" : System.out.println("Lista contatti : ");
        						List<Contact> contacts = RubricaJDBC.loadRubricaJDBC(); 
        						for(Contact c : contacts) {
        							System.out.println("Nome : " + c.getName() + " - Cognome : " + c.getSurname());
        						}
        						break;
        case "nuovo contatto" : System.out.println("Nome : ");
        						String name = scanner.nextLine(); 
        						System.out.println("Cognome : ");
        						String surname = scanner.nextLine(); 
        						System.out.println("Email : ");
        						String email = scanner.nextLine(); 
        						System.out.println("Telefono : ");
        						String phone = scanner.nextLine(); 
        						System.out.println("Note : ");
        						String note = scanner.nextLine(); 
        						Contact c = new Contact();
        						c.setName(name); c.setSurname(surname); c.setEmail(email); c.setPhoneNumber(phone); c.setNote(note);
        						RubricaJDBC.createContactFromRubrica(c);
        						break;
        case "modifica contatto" : System.out.println("a"); break;
        case "cancella contatto" : System.out.println("a"); break;
        case "trova contatti duplicati" : System.out.println("a"); break;
        case "unisci contatti duplicati" : System.out.println("a"); break;
        case "esci" :  break;
        }
        scanner.close();
	}

}
