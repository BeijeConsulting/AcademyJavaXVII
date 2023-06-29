package it.beije.xvii.exercises.iannetta;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContactsManagerTest {

	private static void menu() {
		System.out.println("1: View contacts" + 
						 "\n2: Search contact" +
						 "\n3: Add new contact" + 
						 "\n4: Edit contact" + 
						 "\n5: Delete contact" + 
						 "\n6: Find duplicates" + 
						 "\n7: Merge duplicates" + 
						 "\n0: Exit");
	}
	
	private static boolean choice(int answer) throws ClassNotFoundException, SQLException {
		ContactsManager contactsManager = new ContactsManager();
		switch(answer) {
		//case 1: contactsManager.showContacts("id");
		case 1: contactsManager.sorting(); break; 
		case 2: contactsManager.searchContact(); break;
		case 3: contactsManager.insertContact(); break;
		case 4: int id = contactsManager.searchByID();
				  contactsManager.editContact(id); 
				 break;
		default: break;
		}
		if (answer != 0) return true;
		return false;
	}
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to Contacts Manager. What do you want to do?");
		
//		ContactsManager contactsManager = new ContactsManager();
//		 contactsManager.sorting(); 
		menu();
		
		int answer=0;
			answer = in.nextInt();
		boolean keepGoing = choice(answer);
		
		
		
//		while (keepGoing){
//			keepGoing = choice(answer);
//			menu();
//			answer = in.nextInt();
//		}			
		in.close();

		
	}

}
