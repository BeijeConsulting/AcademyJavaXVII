package it.beije.suormary.rubrica.trapani;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class ExportSuDB {
	
	public static void exportSuCSV() throws ClassNotFoundException, SQLException, IOException{
		Scanner in = new Scanner(System.in);
		System.out.println("Inserisci path DB in cui scrivere: ");
		String pathDB = in.nextLine();
		System.out.println("Inserisci user: ");
		String user = in.nextLine();
		System.out.println("Inserisci user: ");
		String pw = in.nextLine();
		List<Contact> c = MetodiListContact.loadContactListFromDB(pathDB,user,pw);
		
		System.out.println("Inserisci path file in cui scrivere: ");
		String pathFile = in.nextLine();
		System.out.println("Inserisci separatore: ");
		String separatore = in.nextLine();
		
		MetodiListContact.writeContactsInRubricaCSV(c, pathFile, separatore);
		
	}
	
	public static void exportSuXML() throws ClassNotFoundException, SQLException{
		Scanner in = new Scanner(System.in);
		System.out.println("Inserisci path DB in cui scrivere: ");
		String pathDB = in.nextLine();
		System.out.println("Inserisci user: ");
		String user = in.nextLine();
		System.out.println("Inserisci user: ");
		String pw = in.nextLine();
		List<Contact> c = MetodiListContact.loadContactListFromDB(pathDB,user,pw);
		
		System.out.println("Inserisci path file in cui scrivere: ");
		String pathFile = in.nextLine();
				
		MetodiListContact.writeContactsInRubricaXML(c, pathFile);
		
	}
	
	public static void main(String[] args) {
		
	}
	
}
