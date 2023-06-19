package it.beije.xvii.exercises.trapani;

public class Figura {

	public static void main(String[] args) {
		    for(int x=6;x>=1;x--){

		    	for(int space=7-x;space>0;space--) 
		        	System.out.print(space);
		    	System.out.print(" ");
		        for (int y=x;y>0;y--)
		        System.out.print(y);
		        System.out.println();
		        
		    }
		    
		   }
	
}