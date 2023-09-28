package Soluzioni;

import java.util.List;

/*
 * Filtraggio semplice: 
 * Crea uno stream di numeri interi, filtra solo i numeri pari e raccogli in una nuova lista gli elementi filtrati.
 * Esempio: [1, 2, 3, 4, 5, 6] -> [2, 4, 6]
*/


import java.util.stream.Collectors;

public class Ex_1_soluzione {
	
	public static List<Integer> ex1(List<Integer> integersList) {
	
	List<Integer> evenNumbers = integersList.stream() // crea uno stream a partire dalla lista di input "integersList"
            .filter(x -> x % 2 == 0) // il metodo filter viene usato per selezionare solo gli elementi che soddisfano la condizione specificata
            .collect( // collect serve per raccogliere in una nuova lista gli elementi filtrati. 
            		Collectors.toList() // Collectors.toList() è utilizzato per creare una lista contenente gli elementi filtrati.
            		); 
    return evenNumbers;
	}

}
