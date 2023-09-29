package Soluzioni;

import java.util.List;

/*
 * Somma degli elementi: 
 * Calcola la somma di tutti i numeri in uno stream di numeri interi.
 * Esempio: [1, 2, 3, 4, 5, 6] -> 21
 * */

public class Ex_3_soluzione {
	
	public static int ex3(List<Integer> integersList) {
        int sum = integersList.stream() // // crea uno stream a partire dalla lista di input "integersList"
        		
                .reduce( // il metodo reduce serve per calcolare la somma degli elementi nella lista e accetta due argomenti: 
                		//il valore iniziale della somma (in questo caso, 0) e una funzione (nel formato di una lambda expression) 
                		//applicata iterativamente agli elementi dello stream
                		
                		0, (x, y) -> x + y // la funzione somma i valori x e y per ogni elemento dello stream.
                		);
        return sum;
    }

}
