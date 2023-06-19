package it.beije.xvii.exercises.ulloa;

/*
 * Stampare a video la seguente figura:
#
##
###
####
#####
######
 */

public class Esercizio5 {

	public static void main(String[] args) {
		for (int i=1; i<=6; i++) {
			for (int j=1; j<=i; j++) {
				System.out.print("#");
			}
			System.out.println();
		}
	}

}
