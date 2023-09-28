package Soluzioni;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Dato uno stream di stringhe, trasforma ogni stringa in maiuscolo.
 * Esempio: ["a", "b", "c"] -> ["A", "B", "C"]
 */

public class Ex_2_soluzione {
	
	public List<String> ex2(List<String> stringsList) {
        List<String> uppercaseStrings = stringsList.stream() // crea uno stream a partire dalla lista di input "stringsList"
                .map(x -> x.toUpperCase()) //  il metodo map serve per convertire ogni elemento della lista in maiuscolo utilizzando il metodo toUpperCase().
                .collect( // collect serve per raccogliere in una nuova lista gli elementi filtrati.
                		Collectors.toList() // Collectors.toList() è utilizzato per creare una lista contenente gli elementi filtrati.
                		);
        return uppercaseStrings;
        
    }

}
