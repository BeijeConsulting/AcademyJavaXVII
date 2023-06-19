package it.beije.xvii.exercises.mancuso;

//scrivere un metodo “boolean contains(int e, int[] array)” che restituisca true 
//se l’elemento e è presente nell’array, false altrimenti. Ripetere l’esercizio 
//con “boolean contains(Object e, Object[] array)”, quali differenze ci sono?

public class Es18 {

	public boolean contains(int e, int[] array) {
		for(int i=0; i<array.length; i++) {
			if(e == array[i]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(Object e, Object[] array) {
		for(int i=0; i<array.length; i++) {
			/*if(e == array[i]) {
				return true;
			}*/
			if(e.equals(array[i])){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] numeri = {1,2,3,4,5,6,7,8,9,10};
		int num = 4;
		
		Es18 support = new Es18();
		
		boolean found = support.contains(num, numeri);
		
		if(found) {
			System.out.println("Trovato");
		}else {
			System.out.println("Non trovato");
		}
		
		found = false;
		
		Double[] objects = {1.0,2.0,3.0,4.0,5.0};
		Double obj = 2.0;
		
		found = support.contains(obj, objects);
		
		if(found) {
			System.out.println("Trovato");
		}else {
			System.out.println("Non trovato");
		}
	}

}
