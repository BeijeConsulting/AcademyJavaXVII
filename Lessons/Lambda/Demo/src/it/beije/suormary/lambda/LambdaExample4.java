package it.beije.suormary.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.beije.suormary.bin.Person;

public class LambdaExample4 {
	public static void main(String[] args) {  
		List<Person> list=new ArrayList<Person>();  
	          
	    //Adding people  
	    list.add(new Person("Mario", 25));  
	    list.add(new Person("Gianni", 54));  
	    list.add(new Person("Sara", 33));  
	    
	          
	    System.out.println("Sorting on the basis of name...");  
	  
	    // Notice how the lambda expression is used to pass a function as a parameter
	    
	    Collections.sort(list,(p1,p2)->{  
	    	return p1.getName().compareTo(p2.getName());  
	    });  
	    
	    list.forEach((p) -> System.out.println(p.getName() + " - " + p.getAge()));
	    
	    
	    System.out.println("----------------");
	    System.out.println("Sorting on the basis of age...");  
		
	    
	    // One only needs to change from getName() to getAge()
	   
	    Collections.sort(list,(p1,p2)->{  
	    	return p1.getAge().compareTo(p2.getAge());  
	    });  
	    
	    
	    list.forEach((p) -> System.out.println(p.getName() + " - " + p.getAge()));
	    
	}  
}
