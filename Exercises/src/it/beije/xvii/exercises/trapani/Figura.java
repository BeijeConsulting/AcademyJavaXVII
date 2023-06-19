package it.beije.xvii.exercises.trapani;

public class Figura {

	public static void main(String[] args) {
		int num=6;
		
		for(int i=0;i<6;i++) {
			int y;
			for(y=0;y<i+1;y++) {
				
				System.out.print(y+1);
				
			}
		    	System.out.print(" ");
		        for (int k=0;k<num-i ;k++)
		        System.out.print((num - k - y + 1));
		        System.out.println();
		        
		    }
		    
		   }
	
}
