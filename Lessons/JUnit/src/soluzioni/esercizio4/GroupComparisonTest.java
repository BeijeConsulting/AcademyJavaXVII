package soluzioni.esercizio4;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GroupComparisonTest {
	
	GroupComparison gc = new GroupComparison();

	//4
	@Test
	public void relativesTest() {
		  
		  Person p1 = new Person("Andrea","Ferrari",4,"Ferrara");
		  Person p2 = new Person("Luca","Rossi",32,"Bari");
		  Person p3 = new Person("Sofia","Moretti",78,"Bari");
		  Person p4 = new Person("Laura","Marini",56,"Genova");
		  Person p5 = new Person("Massimo","Romani",33,"Roma");
		  Person p6 = new Person("Elena","Rossi",28,"Bari");
		  Person p7 = new Person("Gigliola","Rossi",21,"Bari");
		  
		  Person[] group ={p1,p2,p3,p4,p5,p6,p7};
		  
		  Person testPerson = new Person("Lillo","Rossi", 57, "Bari");

		  Person[] relatives ={p2,p6,p7};
		  
		  
		  assertArrayEquals(relatives, gc.relatives(group, testPerson));
		
	}

	//5
	@Test
	public void sortByNameTest() {
		  
		  Person p1 = new Person("Andrea","Ferrari",4,"Ferrara");
		  Person p2 = new Person("Luca","Russo",32,"Mosca");
		  Person p3 = new Person("Sofia","Moretti",78,"Bari");
		  Person p4 = new Person("Laura","Marini",56,"Genova");
		  Person p5 = new Person("Massimo","Romani",33,"Roma");
		  Person p6 = new Person("Elena","Rossi",28,"Matera");
		  
		  Person[] group ={p1,p2,p3,p4,p5,p6};
		  
		  Person[] sortedGroup = {p1,p6,p4,p2,p5,p3};
		  
		  assertArrayEquals(sortedGroup, gc.sortByName(group));
		
	}
	
	//6
	@Test
	public void citizensTest() {

	  Person p1 = new Person("Andrea","Ferrari",4,"Ferrara");
	  Person p2 = new Person("Luca","Russo",32,"Bari");
	  Person p3 = new Person("Sofia","Moretti",78,"Bari");
	  Person p4 = new Person("Laura","Marini",56,"Genova");
	  Person p5 = new Person("Massimo","Romani",33,"Roma");
	  Person p6 = new Person("Elena","Rossi",28,"Bari");
	  Person p7 = new Person("Gigliola","Rossi",21,"Bari");
	  
	  Person[] group ={p1,p2,p3,p4,p5,p6,p7};
	  
	  assertEquals(4, gc.citizen(group,"Bari"));

	}
}
