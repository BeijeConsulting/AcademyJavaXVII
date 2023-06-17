package it.beije.xvii.exercises.iannetta;


public class FibonacciRows {

	public static void main(String[] args) {
		String sequence = "";

		long[] fib = new long[] {0L, 0L, 0L};
		
		int n = 20;
		
		for (int i = 1; i <= n; i++) {
			if (i == 1) {
				fib[0] = 1L;
				sequence += fib[0];
			}
			else if (i == 2) {
				fib[1] = 1L;
				sequence += fib[0];
			}
			else {
				fib[2] = fib[0] + fib[1];
				fib[0] = fib[1];
				fib[1] = fib[2];
				sequence += fib[2];
			}
			System.out.println(sequence);
			if (i != n) sequence += ", ";
		}
	}

}
