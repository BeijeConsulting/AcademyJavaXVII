package it.beije.xvii.exercises.ceccarelli.DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.suormary.rubrica.Contact;

public class MetodiRubrica {
	
	private ExerciseswithDB db;
	public MetodiRubrica() {
		db = new ExerciseswithDB();
	}
	
	// list of contacts
	public void listContact() {
		List<Contact> names = new ArrayList<Contact>();
		try {
			List<Contact> contacts = db.loadRubricaFromDb();
			Contact tmp = contacts.get(0);
			for(int y=1;y<=contacts.size();y++) {
				if(y==contacts.size()) {
					names.add(tmp);
					break;
				}
				//System.out.println(tmp.getName() + contacts.get(y).getName());
				int comp = tmp.getName().compareToIgnoreCase(contacts.get(y).getName());
				//System.out.println(comp);
				if(comp<0) {
					names.add(tmp);
					tmp = contacts.get(y);
				}else if(comp>0){
					names.add(contacts.get(y)); 
				}else {
					names.add(contacts.get(y));
					tmp = contacts.get(y);
				}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Vuoi salvare il risultato su un file? ");
		boolean rispostaValida = false;
		while(!rispostaValida) {
			String r = scan.next().trim();
			if(r.equalsIgnoreCase("Si") || scan.next().trim().equalsIgnoreCase("Sì") ) {
				System.out.print("CSV o XML? ");
				String s = scan.next().trim();
				if(s.equalsIgnoreCase("csv")) {
					try {
						rispostaValida = true;
						db.writeRubricaFromDbToCSV(names);	
					}catch(Exception e) {
						System.out.println("errore");
					}
				}else if(s.equalsIgnoreCase("xml")){
					try {
						rispostaValida = true;
						db.writeRubricaFromDbToXML(names);
					
					}catch(Exception e) {
						System.out.println("errore");
					}
				}else {
					System.out.println("Valore inserito non valido");
				}
			}else if(r.equalsIgnoreCase("No")){
				System.out.println("Valori non salvati");
				rispostaValida = true;
			}else {
				System.out.println("Valore inserito non valido");
			};
		}
		System.out.println("FATTO!");
	} 
	
	// search contacts
	public void searchContact() {
		
		
	}
	
}
