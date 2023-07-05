package it.beije.suormary.rubrica.iannetta;
import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class ContactsManagerTest {

	private static void menu() {
		System.out.println("1: View contacts" + 
						 "\n2: Search contact" +
						 "\n3: Add new contact" + 
						 "\n4: Edit contact" + 
						 "\n5: Delete contact" + 
						 "\n6: Find duplicates" + 
						 "\n7: Merge duplicates" + 
						 "\n8: Import contacts" + 
						 "\n9: Export contacts" +
						 "\n0: Exit");
	}
	
	private static boolean choice(int answer) throws ClassNotFoundException, SQLException {
		ContactsManagerJDBC contactsManagerJDBC = new ContactsManagerJDBC();
		int id;
		switch(answer) {
		case 1: contactsManagerJDBC.sorting(); break; 
		case 2: contactsManagerJDBC.searchContact(); break;
		case 3: contactsManagerJDBC.insertContact(); break;
		case 4: id = contactsManagerJDBC.searchByID();
				contactsManagerJDBC.editContact(id); 
				break;
		case 5: id = contactsManagerJDBC.searchByID();
				contactsManagerJDBC.deleteContact(id);
				break;
		case 6: contactsManagerJDBC.findDuplicates(); break;
		case 7: contactsManagerJDBC.mergeDuplicates(); break;
		case 8: contactsManagerJDBC.importFrom(); break;
		case 9: contactsManagerJDBC.exportTo(); break;
		case 0:
		default: contactsManagerJDBC.end(); return false;
		}
		return true;
	}
		
	private static boolean choiceHBM(int answer) throws ClassNotFoundException, SQLException, ParserConfigurationException, SAXException, IOException, TransformerException {
		ContactsManagerHBM contactsManagerHBM = new ContactsManagerHBM();
		int id;
		switch(answer) {
		case 1: contactsManagerHBM.sorting(); break; 
		case 2: contactsManagerHBM.searchContact(); break;
		case 3: contactsManagerHBM.insertContact(); break;
		case 4: id = contactsManagerHBM.selectID();
				contactsManagerHBM.editContact(id); 
				break;
		case 5: id = contactsManagerHBM.selectID();
				contactsManagerHBM.deleteContact(id);
				break;
		case 6: contactsManagerHBM.findDuplicates(); break;
		case 7: contactsManagerHBM.mergeDuplicates(); break;
		case 8: contactsManagerHBM.importFrom(); break;
		case 9: contactsManagerHBM.exportTo(); break;
		case 0:
		default: contactsManagerHBM.end(); return false;
		}
		return true;
	}
	
	private static boolean choiceJPA(int answer) throws ClassNotFoundException, SQLException {
		ContactsManagerJPA contactsManagerJPA = new ContactsManagerJPA();
		int id;
		switch(answer) {
		case 1: contactsManagerJPA.sorting(); break; 
		case 2: contactsManagerJPA.searchContact(); break;
		case 3: contactsManagerJPA.insertContact(); break;
		case 4: id = contactsManagerJPA.selectID();
				contactsManagerJPA.editContact(id); 
				break;
		case 5: id = contactsManagerJPA.selectID();
				contactsManagerJPA.deleteContact(id);
				break;
//		case 6: contactsManagerJPA.findDuplicates(); break;
//		case 7: contactsManagerJPA.mergeDuplicates(); break;
//		case 8: contactsManagerJPA.importFrom(); break;
//		case 9: contactsManagerJPA.exportTo(); break;
		case 0:
		default: contactsManagerJPA.end(); return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException, SAXException, IOException, TransformerException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to Contacts Manager. What do you want to do?");
		
		int answer;
		boolean keepGoing;
		
//		//NORMALE
//		do {
//			System.out.println("");
//			menu();
//			answer = in.nextInt();
//			keepGoing = choice(answer);						
//		} while (keepGoing);
//		
//		
//		//HBM
//		do {
//			System.out.println("");
//			menu();
//			answer = in.nextInt();
//			keepGoing = choiceHBM(answer);						
//		} while (keepGoing);
		
		
		//JPA
		do {
			System.out.println("");
			menu();
			answer = in.nextInt();
			keepGoing = choiceJPA(answer);						
		} while (keepGoing);
		
		
		in.close();
	}
}
