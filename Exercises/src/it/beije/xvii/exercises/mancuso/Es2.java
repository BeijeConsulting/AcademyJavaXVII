package it.beije.xvii.exercises.mancuso;

// Scrivere un programma che stampi a video i primi dieci interi pari compresi fra 20 e 0, partendo da 20.

public class Es2 {
	public static void main(String[] args) {
		
		int i = 0;
		int n = 20;
		
		while(i<10) {
			if(n%2 == 0) {
				System.out.print(n + " ");
				i++;
			}
			n--;
		}
		
	}
}
