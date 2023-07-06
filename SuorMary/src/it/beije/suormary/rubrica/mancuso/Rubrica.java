package it.beije.suormary.rubrica.mancuso;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

public class Rubrica {

	public static void main(String[] args) {
		Scanner input = null;
			
		try {
			JPAManagerFactory.getEntityManager();
			AddressBook ab = new AddressBook();
			
			input = new Scanner(System.in);
			
			String command = "";
			
			boolean exit = false;
			
			while(!exit) {
				
				Menu.printOptions();
				
				command = input.nextLine();
				
				exit = Menu.executeCommand(command, input, ab);
				
			}
			System.out.println("Programma terminato.");
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			input.close();
			JPAManagerFactory.getEntityManager().close();
		}
		
	}

}
