package it.beije.xvii.exercises.ulloa;

/*
 * Stampare a video la seguente figura:
1      654321
12      54321
123      4321
1234      321
12345      21
123456      1
 */

public class Esercizio6 {
	public static void main(String[] args) {
		int v=6;
		for (int i=1; i<=6; i++) {
			for (int j=1; j<=i; j++) {
				System.out.print(j);
			}
			
			System.out.print("      ");
			
			for (int k=v; k>=1; k--) {
				System.out.print(k);
			}
			v--;  
			System.out.println();
		}
		
	}

}
