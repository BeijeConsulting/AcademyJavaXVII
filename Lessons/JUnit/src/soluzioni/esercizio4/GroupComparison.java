package soluzioni.esercizio4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
		return relatives.toArray(new Person[0]);	
	}
	
	//5
	public Person[] sortByName(Person[] group) {
		
		Person temp = null;
		Person p1 = null;
		Person p2 = null;
		
		for(int i=0; i < group.length; i++) {
			for (int j= 0; j < group.length -1; j++) {
				p1=group[j];
				p2=group[j+1];
				
				if(p1.getName().compareTo(p2.getName()) > 0) {
					temp = p1;
					group[j]=p2;
					group[j+1]=temp;				
				}
			}	
		}
	return group;
	}
	
	//6
	public int citizen(Person[] group, String city) {
		
		int count = 0;
		
		for(Person person : group) {
			if(person.getCity().equalsIgnoreCase(city)) {
				count++;
			}
		}
		
		return count;		
	}

}
