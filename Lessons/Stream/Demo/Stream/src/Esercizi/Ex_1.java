package Esercizi;

/*
 * Filtraggio semplice: 
 * Crea uno stream di numeri interi e filtra solo i numeri pari.
 * Esempio: [1, 2, 3, 4, 5, 6] -> [2, 4, 6]
*/

public class Ex_1 {

	public static List<Integer> ex1(List<Integer> integersList) {
        List<Integer> evenNumbers = integersList.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        return evenNumbers;
    }
}
