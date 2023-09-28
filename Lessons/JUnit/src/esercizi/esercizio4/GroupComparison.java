package esercizi.esercizio4;

import java.util.ArrayList;
import java.util.List;

public class GroupComparison {
	
	//4
	public Person[] relatives(Person[] people, Person p1) {
		
		if(people==null || p1==null) {
			return null;
		}
		
		
		List<Person> relatives = new ArrayList<>(); 
		
		for(Person person : people) {
			if(p1.getSurname().equals(person.getSurname())) {
				relatives.add(person);
			}
		}	
		return (Person[]) relatives.toArray();	
	}
	
	

}
