package Esercizi;

/*
 *Unisci e ordina: 
 *Unisci due stream di numeri, elimina i duplicati e ordina il risultato in ordine decrescente.
 *Esempio: [1, 2, 3], [3, 4, 5] -> [5, 4, 3, 2, 1]
 * */

public class Ex_7 {
	public static List<Integer> filteredList(List<Integer> list1, List<Integer> list2) {
		List<Integer> mergedAndSorted = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
		return mergedAndSorted;
	}

}
