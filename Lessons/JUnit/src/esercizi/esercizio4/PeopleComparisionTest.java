package esercizi.esercizio4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PeopleComparisionTest {
	
	PeopleComparison pc = new PeopleComparison();
	
	//1
	public void createPerson() {}
	
	//2
	@Test
	public void oldestPersonTest() {
		Person p1 = new Person("Anna", "Panna", 21, "Roma");
		Person p2 = new Person("Pio", "Oddio", 17, "Roma");
		
		assertEquals(p1, pc.oldestPerson(p1,p2));				
	}
	
	//2.1
	public void sameAgeTest() {}
	
	/* 3
	*  INSERISCI QUI IL/I TEST 
	*/
	
	
}
