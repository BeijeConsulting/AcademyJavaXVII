package it.beije.xvii.exercises.sala;

/*
 * Stampare a video la seguente figura:
******
*****
****
***
**
*
 */

public class CicloEs4 {

	public static void main(String[] args) {
		for(int i=6; i>=1; i--) {
			for(int j=1; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

}