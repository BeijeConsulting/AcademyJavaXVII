package it.beije.xvii.exercises.mancuso;

/*
 * Stampare a video la seguente figura:
	******
	*****
	****
	***
	**
	*
 * 
 */

public class Es4 {

	public static void main(String[] args) {
		
		for(int i=6; i>0; i--) {
			for(int j=i; j>0; j--) {
				System.out.print("*");
			}
			System.out.print("\n");
		}

	}

}
