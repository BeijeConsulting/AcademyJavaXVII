package it.beije.xvii.exercises.mancuso;

// Scrivere un programma che stampi le tabellina del numero dato come argomento

public class Es3 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		
		for(int i=1; i<=10; i++) {
			System.out.print((num*i) + " ");
		}
	}

}
