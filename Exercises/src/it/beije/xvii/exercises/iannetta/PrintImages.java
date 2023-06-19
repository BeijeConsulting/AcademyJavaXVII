package it.beije.xvii.exercises.iannetta;

public class PrintImages {

	public static void main(String[] args) {
		int min = 1;
		int max = 6;
		
		for (int i = max; i>= min; i--) {
			for (int j = i; j > 0; j--) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		System.out.println("");
		for (int i = min; i<= max; i++) {
			for (int j = 1; j <= i; j++) System.out.print("#");
			System.out.println("");
		}
		
		System.out.println("");
		for (int i = min; i<= max; i++) {
			
			for (int j = min; j <= i; j++) {
				System.out.print(j);
			}
			System.out.print("    ");
			for (int j = max - (i-1); j >= 1; j--) {
				System.out.print(j);
			}
			System.out.println("");
		}

	}

}
