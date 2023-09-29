package soluzioni.esercizio4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PeopleComparisionTest {
	
	PeopleComparison pc = new PeopleComparison();
	
//	//1
//	@Before
//	public void createPerson() {
//		Person p = new Person("Pio", "Oddio", 17, "Roma");
//		
//		assertNotNull(p);	
//	}
	
	//2
	@Test
	public void oldestPersonTest() {
		Person p1 = new Person("Anna", "Panna", 21, "Roma");
		Person p2 = new Person("Pio", "Oddio", 17, "Roma");
		
		assertEquals(p1, pc.oldestPerson(p1,p2));				
	}
	
	//2.1
	@Test
	public void sameAgeTest() {
		Person p1 = new Person("Anna", "Panna", 21, "Roma");
		Person p2 = new Person("Pio", "Oddio", 21, "Roma");
		
		assertNull(pc.oldestPerson(p1, p2));
	}
	
	/* 3
	*  INSERISCI QUI IL/I TEST
	*/
	@Test
	public void sameCityTest() {
		Person p1 = new Person("Anna", "Panna", 21, "Roma");
		Person p2 = new Person("Pio", "Oddio", 81, "Roma");
		
		assertTrue(pc.sameCity(p1, p2));
	}
	
	@Test
	public void differentCityTest() {
		Person p1 = new Person("Anna", "Panna", 21, "Roma");
		Person p2 = new Person("Pio", "Oddio", 81, "Pietrelcina");
		
		assertFalse(pc.sameCity(p1, p2));
	}
	
}
