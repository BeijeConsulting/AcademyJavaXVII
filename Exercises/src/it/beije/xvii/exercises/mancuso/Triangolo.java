package it.beije.xvii.exercises.mancuso;

public class Triangolo {
	
	/*
	 * Triangolo:
	 * 1
	 * 3 5
	 * 7 9 11
	 * 13 15 17 19
	 * 21 23 25 27 29
	 * ....
	 */
	
	/*
	 * si scriva la funzione rowSumOddNumbers(int n) che calcola la somma dei numeri nella riga n-esima. Per esempio:
	 * rowSumOddNumbers(1) = 1
     * rowSumOddNumbers(3) = 7 + 9 + 11 = 27
	 */
	
	public int rowSumOddNumbers(int n) {
		int sum = 0;
		int number = 1;
		
		for(int row = 0; row<n; row++) {
			for(int i=0, j=0; i<n && j<n; number++) {
				if(row == n-1) {
					if(number%2 != 0) {
						System.out.println("Operation: " + sum + " + " + number);
						sum += number;
						i++;
					}
				}else {
					j++;
					continue;
				}
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		
		Triangolo t = new Triangolo();
		
		System.out.println(t.rowSumOddNumbers(2));

	}

}
