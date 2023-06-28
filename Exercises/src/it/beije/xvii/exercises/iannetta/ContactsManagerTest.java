package it.beije.xvii.exercises.iannetta;

import java.sql.SQLException;
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
	
	private static boolean choice(String answer) throws ClassNotFoundException, SQLException {
		switch(answer) {
		case "1": ContactsManager.sorting(); return true;
		case "2": ContactsManager.searchContact(); return true;
		case "3": ContactsManager.insertContact(); return true;
		case "4": int id = ContactsManager.searchByID();
				  ContactsManager.editContact(id); 
					
				//System.out.println(id);
				  return true;
		default: return false;
		}
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to Contacts Manager. What do you want to do?");
		menu();
		String choice = in.nextLine();
		boolean keepGoing = choice(choice);

		while (keepGoing);{
			keepGoing = choice(choice);
			menu();
			choice = in.nextLine();
		}			
		in.close();

		
	}

}
