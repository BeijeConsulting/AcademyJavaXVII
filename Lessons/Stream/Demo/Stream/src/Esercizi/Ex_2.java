package Esercizi;

/*
 * Dato uno stream di stringhe, trasforma ogni stringa in maiuscolo.
 * Esempio: ["a", "b", "c"] -> ["A", "B", "C"]
 */

public class Ex_2 {
	
	public List<String> ex2(List<String> stringsList) {
        List<String> uppercaseStrings = stringsList.stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.toList());
        return uppercaseStrings;
        
    }

}
