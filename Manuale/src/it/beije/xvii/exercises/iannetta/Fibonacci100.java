package it.beije.xvii.exercises.iannetta;

public class Fibonacci100 {

	public static void main(String[] args) {
		//int[] makes sense up to 40-45
		//long[] up to 90?
		long[] fib = new long[] {0L, 0L, 0L};

		//makes sense up to 40-45
		int n = 100;
		
		for (int i = 1; i <= n; i++) {
			if (i == 1) {
				fib[0] = 1L;
				System.out.println(i + ". " + fib[0]);
			}
			else if (i == 2) {
				fib[1] = 1L;
				System.out.println(i + ". " + fib[1]);
			}
			else {
				fib[2] = fib[0] + fib[1];
				fib[0] = fib[1];
				fib[1] = fib[2];
				System.out.println(i + ". " + fib[2]);
			}
		}
	}
}
