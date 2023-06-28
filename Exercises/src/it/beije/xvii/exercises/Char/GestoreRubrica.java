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
        							System.out.println(c);
        						}
        						break;
        case "nuovo contatto" : RubricaJDBC.createContactFromRubrica();
        						break;
        case "modifica contatto" : RubricaJDBC.updateContactFromRubrica();
        						   break;
        case "cancella contatto" : System.out.println("a"); break;
        case "trova contatti duplicati" : System.out.println("a"); break;
        case "unisci contatti duplicati" : System.out.println("a"); break;
        case "esci" :  break;
        }
        scanner.close();
	}

}
