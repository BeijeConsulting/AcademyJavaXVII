package soluzioni.esercizio4;

public class PeopleComparison {
	

	//2 - 2.1
	public Person oldestPerson(Person p1, Person p2) {
		
		if(p1.getAge() > p2.getAge()) {
			return p1;
		} // 2.1
		  else if (p1.getAge() == p2.getAge()){
			   return null;
		  }// FINE 2.1
		else {
			return p2;
		}		
	}
	
	//3
	public boolean sameCity(Person p1, Person p2) {	
		return p1.getCity().equals(p2.getCity());
	}
		

}
