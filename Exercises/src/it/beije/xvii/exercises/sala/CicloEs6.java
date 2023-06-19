package it.beije.xvii.exercises.sala;

/*
 * Stampare a video la seguente figura:
1      654321
12      54321
123      4321
1234      321
12345      21
123456      1
 */

public class CicloEs6 {

	public static void main(String[] args) {
		int n=6;
		for(int i=1; i<=6; i++) {
			for(int j=1; j<=i; j++) {
				System.out.print(j);
			}
			System.out.print("      ");
			for(int z=n; z>=1; z--) {
				System.out.print(z);
			}
			System.out.println();
			n--;
		}

	}

}
