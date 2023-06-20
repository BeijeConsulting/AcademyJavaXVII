package it.beije.xvii.exercises.ulloa;

/*
 * scrivere un metodo “boolean contains(int e, int[] array)” che 
 * restituisca true se l’elemento e è presente nell’array, 
 * false altrimenti. 
 * 
 * Ripetere l’esercizio con “boolean contains(Object e, Object[] array)”,
 * quali differenze ci sono? 
 * 
 * Verificare la sequenza crescente di un array. 
 * Il metodo “boolean isCrescente(int [] array)” 
 * restituisce true se tutti gli elementi dell’array passato sono 
 * in ordine crescente, false altrimenti.
 * 
 * Scrivere il metodo: “public int mostRecurrent(int [] array)” , 
 * che trova l’elemento più ricorrente in un array. 
 * Il metodo restituisce l’elemento trovato.
 */

public class ArrayUtils {
	public static void main(String[] args) {
		ArrayUtils au = new ArrayUtils();
		int [] numbers = {2,2,-1,0,0,0,2,2};
		Integer[] objects = {0,0,1,1,2,3,4,-1,-7,10};
		int e = 2;
		Integer obj = new Integer(e);
		
		System.out.println("E' presente il valore " + e + "? " + au.contains(2, numbers));
		System.out.println("E' presente il valore " + obj + "? " + au.contains(obj, objects));
		System.out.println("E' crescente? " + au.isCrescente(numbers));
		System.out.println("Qual e' l'elemento più ricorrente nell'array? " + au.mostRecurrent(numbers));
	}
	
	public boolean contains(int e, int[] array) {
		boolean presente = false;
		for (int i=0; !presente && i<array.length; i++) {
			if(array[i]==e) {
				presente = true;
			}
		}
		
		return presente;
	}
	
	public boolean contains(Object e, Object[] array) {
		boolean presente = false;
		for (int i=0; !presente && i<array.length; i++) {
			if(array[i].equals(e)) { //qui la differenza 
				presente = true;
			}
		}

		return presente;
	}
	
	public boolean isCrescente(int[] array) {
		boolean crescente = true; 
		for (int i=0; crescente && i<array.length-1; i++) {
			if(array[i]>array[i+1]) {
				crescente = false;
			}
		}
		
		return crescente;
	}
	
	public int mostRecurrent(int[] array) {
		Integer[][] mappa = new Integer[array.length][2];
		boolean trovato;
		
		int k; //contatore del ciclo while
		
		//Mappa[posKey][0]
		//Si tratta dell'indice del valore ricorrente che stiamo cercando.
		//Si inizializza a -1 perché non ci sara mai nel nostro array bidimensionale un indice a -1
		int posKey=-1; 
		//Mappa[posKey][1] si inizializza a 0 perché nella mappa non ci sarà mai un valore uguale a 0
		//il valore minimo presente nella mappa sarà 1
		int value=0;
		
		for(int i=0;i<array.length;i++) {
			//System.out.println(array[i]);
			trovato = false;
			for(int j=0; !trovato && j<mappa.length;j++) {
				if(mappa[j][0]!=null) {
					if(mappa[j][0]==array[i]) {
						mappa[j][1]= mappa[j][1]+1;
						trovato = true;
						//System.out.println("confronto oggetto");
					}
				} else {
					mappa[j][0]=array[i];
					mappa[j][1]=1;
					trovato = true;
					//System.out.println("riempio array");
				}
			}
			//System.out.println();
		}
		
		k=0;
		while(k<mappa.length && mappa[k][0]!=null) {
			if(mappa[k][1]>value) {
				posKey = k;
				value = mappa[k][1];
				//System.out.println("if while");
			}
			k++;
			//System.out.println("ciclo while");
		}
	
		return mappa[posKey][0];
	}

}
