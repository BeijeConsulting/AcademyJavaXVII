package Soluzioni;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 *Unisci e ordina: 
 *Unisci due stream di numeri, elimina i duplicati e ordina il risultato in ordine decrescente.
 *Esempio: [1, 2, 3], [3, 4, 5] -> [5, 4, 3, 2, 1]
 * */

public class Ex_7_soluzione {
	public static List<Integer> filteredList(List<Integer> list1, List<Integer> list2) {
		List<Integer> mergedAndSorted = Stream.concat(list1.stream(), list2.stream()) // viene utilizzato Stream.concat per unire i due stream risultanti in un unico stream.
                .distinct() // il metodo "distinct" rimuove i duplicati dallo stream risultante, in modo che ogni numero appaia solo una volta
                .sorted( // il metodo sorted ordina gli elementi dello stream in ordine decrescente
                		Comparator.reverseOrder() // Comparator.reverseOrder() fornisce un comparatore che inverte l'ordine naturale
                		)
                .collect( // collect serve per raccogliere in una nuova lista gli elementi filtrati. 
                		Collectors.toList() // Collectors.toList() è utilizzato per creare una lista contenente gli elementi filtrati.
                		);
		return mergedAndSorted;
	}

}
