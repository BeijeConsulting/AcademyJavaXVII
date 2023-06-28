package it.beije.xvii.exercises.ceccarelli.DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.beije.suormary.rubrica.Contact;

public class MetodiRubrica {
	
	private ExerciseswithDB db;
	public MetodiRubrica() {
		db = new ExerciseswithDB();
	}
	
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
				System.out.println(tmp.getName() + contacts.get(y).getName());
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
		System.out.println(names);
		System.out.println(names.size());
		//from db to csv
		// from db to xml
	}
	
	
	public void searchContact() {
		
		
	}
	
}
