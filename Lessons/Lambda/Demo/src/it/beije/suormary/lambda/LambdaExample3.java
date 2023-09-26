package it.beije.suormary.lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaExample3 {
	
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
		
	}
	
}
