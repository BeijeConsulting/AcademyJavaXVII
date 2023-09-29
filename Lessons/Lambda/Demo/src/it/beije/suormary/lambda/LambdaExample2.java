package it.beije.suormary.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExample2 {
	
	public static void main(String[] args) {
		
		// Print all elements of a list
		
		 List<String> fruitList = new ArrayList<String>();  
	     
		 fruitList.add("Banana");  
		 fruitList.add("Apple");  
		 fruitList.add("Kiwi");  
		 fruitList.add("Pear");
		 
		 //Without lambda expression
		 
		 for(String s : fruitList) {
			 System.out.println(s);
		 }
		 
		 System.out.println("-----");
		 
		 //With lambda expression
		 
		 fruitList.forEach( (n)->System.out.println(n) );  
		 
		 System.out.println("-----");
		 
		 // Use the Consumer interface to store the method
		 
		 Consumer<String> method = (n) -> { System.out.println(n); };
		 fruitList.forEach( method );
		 
	}
	
}
