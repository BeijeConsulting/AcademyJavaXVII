package it.beije.xvii.exercises.sala;
import java.util.Scanner;

/*
 * Scrivere un programma che stampi le tabellina del numero dato come argomento
 */

public class CicloEs3 {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		System.out.println("inserisci un valore di cui calcolare ala tabellina");
		int n = ts.nextInt();
		int moltiplicatore = 0;
		
		while(moltiplicatore<=10) {
			System.out.println(n+"*"+moltiplicatore+"="+(n*moltiplicatore));
			moltiplicatore++;
		}
		ts.close();
	}

}
