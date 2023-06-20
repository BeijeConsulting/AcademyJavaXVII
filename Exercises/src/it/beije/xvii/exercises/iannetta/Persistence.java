package it.beije.xvii.exercises.iannetta;

public class Persistence {
	
	public static int multiplyDigits(int n, int count) {
		if (n < 10) return count;
		String s = "" + n;
		int product = 1;
		for (int i  = 0; i < s.length(); i++) {
			product *= Integer.parseInt("" + s.charAt(i));
		}
		return multiplyDigits(product, count + 1);
	}

	public static int multiplyDigits(int n) {
		return multiplyDigits(n, 0);
	}
	
	public static void main(String[] args) {
		int numbers[] = {39, 42, 999, 4, 5, 12, 82, 123};

		for (int n: numbers) {
			System.out.println("Number: " + n);
			System.out.println("Persistence: " + multiplyDigits(n) + "\n");
		}
	}

}
