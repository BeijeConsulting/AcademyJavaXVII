package Soluzioni;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Unione di due stream:
 *  Unisci due stream di stringhe in un unico stream e raccogli in una nuova lista gli elementi filtrati.
 * Esempio: ["a", "b", "c"], ["d", "e", "f"] -> ["a", "b", "c", "d", "e", "f"]
 * */

public class Ex_4_soluzione {
	
	public static List<String> ex4(List<String> list1, List<String> list2) {
        return Stream.concat( // il metodo concat serve ad unire i due stream risultanti in un unico stream.
        		list1.stream(), list2.stream() //viene convertita ciascuna delle due liste in uno stream utilizzando il metodo stream()
        		)
        		.collect( // collect serve per raccogliere in una nuova lista gli elementi filtrati. 
                		Collectors.toList() // Collectors.toList() è utilizzato per creare una lista contenente gli elementi filtrati.
                		);
    }

}
