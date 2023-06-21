package it.beije.xvii.exercises.giampaoli;

public class RowSumOddNumbers {

	static int rowSumOddNumbers(int n) {		
	
		int n_Risultato = 0;
		int n_NumeriPrima = 0;
		int n_NumeriTotali = 0;
		
		//Calcolo riga tabella
		for(int i = 0; i <= n; i++) {
			n_NumeriTotali = n_NumeriTotali + i;
		}
		
		for(int i = 0; i < n; i++) {
			n_NumeriPrima = n_NumeriPrima + i;
		}
		
		
		
		for(int i = n_NumeriPrima + 1; i <= n_NumeriTotali; i++) {
			if (((i * 2) - 1) % 2 == 1) {
				n_Risultato = n_Risultato + ((i * 2) - 1);
			}
		}
		
	
	
		return n_Risultato;
	}
	
	public static void main(String args[])   {
		System.out.print(rowSumOddNumbers(4));
	}
	
}
