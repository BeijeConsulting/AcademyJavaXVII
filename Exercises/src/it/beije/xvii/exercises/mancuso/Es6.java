package it.beije.xvii.exercises.mancuso;

/*
 * Stampare a video la seguente figura:
1      654321
12      54321
123      4321
1234      321
12345      21
123456      1
 * 
 */

public class Es6 {
	public static void main(String[] args) {
		for(int i=1; i<=6; i++) {
			
			for(int j=1; j<=i; j++) {
				System.out.print(j);
			}
			
			System.out.print(" ");
			
			for(int j=6-i+1; j>=1; j--) {
				System.out.print(j);
			}
			
			System.out.print("\n");
		}
	}
}
