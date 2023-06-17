package it.beije.xvii.exercises.iannetta;

public class PrintEvenNumbers {

	public static void main(String[] args) {
		
		for (int i = 20, count = 0; i >= 0 && count < 10; i--) {
			if (i % 2 == 0) {
				count++;
				System.out.println(count + ". " + i);
			}
		}
	}
}
