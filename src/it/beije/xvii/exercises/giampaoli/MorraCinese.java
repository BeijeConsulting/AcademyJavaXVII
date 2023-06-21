package it.beije.xvii.exercises.giampaoli;
import java.util.Scanner;

public class MorraCinese {
	
	public static void main(String args[])   {
		String p1 = "";
		String p2 = "";
		
		Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
		System.out.print("Giocatore uno: Carta, sasso o forbici?: ");  
		p1 = sc.nextLine().trim();
		
	     if (p1.equals("Carta") || p1.equals("Forbici") || p1.equals("Sasso")) {
	    	System.out.print("Giocatore due: Carta, sasso o forbici?: ");  
	   		p2 = sc.nextLine().trim();
	   			if (p1.equals("Carta") || p1.equals("Forbici") || p1.equals("Sasso")) {
	   				if (p1.equals(p2)) {
	   					System.out.println("Parità");
	   				} else if ((p1.equals("Carta") && p2.equals("Sasso")) || (p1.equals("Sasso") && p2.equals("Forbici")) || (p1.equals("Forbici") && p2.equals("Carta"))) {
	   					System.out.println("Giocatore 1 vince, " + p1 + " batte " + p2 );
	   				} else if ((p2.equals("Carta") && p1.equals("Sasso")) || (p2.equals("Sasso") && p1.equals("Forbici")) || (p2.equals("Forbici") && p1.equals("Carta"))) {
	   					System.out.println("Giocatore 2 vince, " + p2 + " batte " + p1 );
	   				}  				
	   				
	   			} else {
	   				System.out.println("Ho detto carta, sasso o forbici.");
		        	 System.exit(0);
	   			}
	   		
	     } 	else { 
	        	 System.out.println("Ho detto carta, sasso o forbici.");
	        	 System.exit(0);
         }
	      
	                 	
    }
}
