package it.beije.xvii.exercises.mancuso;

/*
 * Verificare la sequenza crescente di un array. Il metodo “boolean isCrescente(int [] array)” 
 * restituisce true se tutti gli elementi dell’array passato sono in ordine crescente, false altrimenti.
 */

public class Es19 {
	
	public static boolean isCrescente(int[] array) {
		int previousNumber = array[0];
		for(int i=1; i<array.length; i++) {
			if(array[i]>previousNumber) {
				previousNumber = array[i];
				continue;
			}else {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] numeri = {1,2,3,4,5,6,7,8,9,10};
		
		if(Es19.isCrescente(numeri)) {
			System.out.println("Crescente");
		}else {
			System.out.println("Non crescente");
		}
		
		int[] numeri2 = {1,3,2,4,5,6,7,8,9,10};
		
		if(Es19.isCrescente(numeri2)) {
			System.out.println("Crescente");
		}else {
			System.out.println("Non crescente");
		}
	}

}
