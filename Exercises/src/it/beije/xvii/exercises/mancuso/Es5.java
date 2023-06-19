package it.beije.xvii.exercises.mancuso;

/*
 * Stampare a video la seguente figura:
#
##
###
####
#####
######
 */

public class Es5 {
	public static void main(String[] args) {
		
		for(int i=1; i<=6; i++) {
			for(int j=1; j<=i; j++) {
				System.out.print("#");
			}
			System.out.print("\n");
		}

	}
}
