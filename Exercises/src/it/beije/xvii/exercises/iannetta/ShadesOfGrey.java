package it.beije.xvii.exercises.iannetta;

public class ShadesOfGrey {
	
	private static String decToHex(int n) {
		int maxPower = 0;
		int number = n;
		while (number < (int)Math.pow(16, maxPower)) maxPower++;
		
	}

	private static String[] shades(int n) throws Exception {
		if (n < 0) return new String[] {};
		if (n == 0) throw new Exception("That's white");
		if (n > 254) n = 254;
		
		String [] result = new String[n];
		for (int i = 0; i < n; i++) {
			result [i+1] = "#" + decToHex(i+1);
		}
		
		return result;
 	}
	
	public static void main(String[] args) {
		

	}

}
