package it.beije.xvii.exercises.iannetta;

public class SumRows {

	private static int rowSumOddNumbersLoop(int n) {
		int count = 1;
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum = 0;
			for (int j = 1; j <= i; j++) {
				System.out.print(count + " ");
				sum += count;
				count += 2;
			}
			System.out.println("");
		}
		return sum;
	}
	
	private static int rowSumOddNumbers(int n) {
		int sum = 1;
		for (int i = 0; i < n; i++) sum += n * (n - 1) + 1 + 2 * i; 
		return sum;
	}
	
	public static void main(String[] args) {
		int row = 0;
		System.out.println("Double loop: " + rowSumOddNumbersLoop(row));
		System.out.println("Single loop: " + rowSumOddNumbers(row));

	}

}
