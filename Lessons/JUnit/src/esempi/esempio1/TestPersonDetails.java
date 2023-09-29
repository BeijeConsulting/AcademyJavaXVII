package esempi.esempio1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Ignore;
import org.junit.Test;

public class TestPersonDetails {

	@Test
	public void testEqualsDifferent() {

		PersonDetails person1 = new PersonDetails();
		person1.setName("Bob");
		person1.setSurname("Barker");
		person1.setAge(46);
		
		PersonDetails person2 = new PersonDetails();
		person2.setName("Frank");
		person2.setSurname("Loyd");
		person2.setAge(46);
		
		assertEquals(person1, person2);
	}	
	
	//NO OVERRIDE EQUALS
	//@Ignore
	@Test
	public void testNoOverrideEquals() {
		
		String name = "Bob";
		String surname = "Barker";
		int age = 46;
		

		PersonDetails person1 = new PersonDetails();
		person1.setName(name);
		person1.setSurname(surname);
		person1.setAge(age);
	
		PersonDetails person3 = new PersonDetails();
		person3.setName(name);
		person3.setSurname(surname);
		person3.setAge(age);
		
		assertEquals(person1, person3);
		
		//assertEquals(person1.getName(), person3.getName());
	
	}
	
	//CON OVERRIDE EQUALS
	@Ignore
	@Test
	public void testOverrideEquals() {

		PersonDetails person1 = new PersonDetails();
		person1.setName("Bob");
		person1.setSurname("Barker");
		person1.setAge(46);
	
		PersonDetails person3 = new PersonDetails();
		person3.setName("Bob");
		person3.setSurname("Barker");
		person3.setAge(46);
		
		assertEquals(person1, person3);
	
	}
	
	
	@Test
	public void testEqualsWithSameRef() {
	
		PersonDetails person1 = new PersonDetails();
		person1.setName("Bob");
		person1.setSurname("Barker");
		person1.setAge(46);
	
		PersonDetails person3 = person1;
		//person3.setName("jack");
		
		assertEquals(person1, person3);
	
	}
	
	@Test
	public void testAssertSame() {
	
		PersonDetails person1 = new PersonDetails();
		person1.setName("Bob");
		person1.setSurname("Barker");
		person1.setAge(46);
	
		PersonDetails person3 = person1;

		assertSame(person1, person3);
	}
	
	@Test
	public void testSameDifferent() {

		PersonDetails person1 = new PersonDetails();
		person1.setName("Bob");
		person1.setSurname("Barker");
		person1.setAge(46);
		
		PersonDetails person2 = new PersonDetails();
		person2.setName("Bob");
		person2.setSurname("Barker");
		person2.setAge(46);
		
		assertSame(person1, person2);
	}	

}
