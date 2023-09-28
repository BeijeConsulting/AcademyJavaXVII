package soluzioni.esercizio4;

import java.util.ArrayList;
import java.util.List;

public class GroupComparison {
	
	//4
	public Person[] relatives(Person[] group, Person p1) {
		
		if(group==null || p1==null) {
			return null;
		}	
		List<Person> relatives = new ArrayList<>(); 
		
		for(Person person : group) {
			if(p1.getSurname().equals(person.getSurname())) {
				relatives.add(person);
			}
		}	
		return (Person[]) relatives.toArray();	
	}
	
	//5
	public Person[] sortByName(Person[] group) {
		return null;}
	
	//6
	public int citizen(Person[] group, String city) {
		return 0;		
	}

}
