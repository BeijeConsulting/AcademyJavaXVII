package it.beije.xvii.exercises.mancuso;

/*
 * Scrivere un programma che stampi le n righe della successione di Fibonacci in questo modo:
1
1, 1
1, 1, 2
1, 1, 2, 3
1, 1, 2, 3, 5
1, 1, 2, 3, 5, 8
1, 1, 2, 3, 5, 8, 13
....
 */

public class Es8 {

	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			long n1 = 1;
			long n2 = 1;
			for(int j=0; j<=i; j++) {
				if(j<2) {
					System.out.print(1);
				}else {
					long n = n1 + n2;
					n2 = n1;
					n1 = n;
					System.out.print(n);	
				}
				if(j<i) {
					System.out.print(", ");
				}
				
			}
			System.out.print("\n");
			
		}

	}

}
