package it.beije.xvii.exercises.giampaoli;
import java.util.*;  

public class Concatena {
	
	public static void main(String args[])   {
		String p1 = "";
		String p2 = "";
		String p3 = "";
		
		
		Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
		System.out.print("Inserisci una parola: ");  
		p1 = sc.nextLine().trim();
		
		System.out.print("Inserisci una seconda parola: ");  
		p2 = sc.nextLine().trim();
		
		System.out.print("Inserisci una terza parola: ");  
		p3 = sc.nextLine().trim();
	        
		StringBuilder concat = new StringBuilder().append(p1).append("*").append(p2).append("*").append(p3);
		
		System.out.println(concat);
	}
}