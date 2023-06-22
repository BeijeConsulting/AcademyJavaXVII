package it.beije.xvii.exercises.trapani;

public class Asterischi {

	public static void main(String[] args) {
		int rows=6;
		
		for(int i=0; i<rows; i++) {
			for (int j=rows-1; j>=i; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
		

}
