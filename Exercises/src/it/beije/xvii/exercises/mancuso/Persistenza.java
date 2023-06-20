package it.beije.xvii.exercises.mancuso;

/*
 * 
 * Scrivere la funzione “persistenza”, che prende un parametro intero positivo e 
 * ritorna la sua “persistenza moltiplicativa”, che è il numero di volte per cui 
 * bisogna moltiplicare le cifre fra loro fino ad avere un unico carattere.
 * Per esempio:persistence(39) == 3 // perché 3*9 = 27, 2*7 = 14, 1*4=4
// e 4 è diuna cifra solapersistence(999) == 4 // perché 9*9*9 = 729, 7*2*9 = 126,
// 1*2*6 = 12, e 1*2 = 2

	persistence(4) == 0 // perché 4 è già una cifra singola
 * 
 */

public class Persistenza {
	
	public static int persistence(int n) {
		if(n>=0) {
			String cifre = String.valueOf(n);
			int count = 0;
			while(cifre.length()>1) {
				int product = 1;
				for(int i=0; i<cifre.length(); i++) {
					int c = Character.getNumericValue(cifre.charAt(i));
					product *= c;
				}
				count ++;
				cifre = "" + product;
			}
			return count;
		}else {
			return -1;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(persistence(999));

	}

}
