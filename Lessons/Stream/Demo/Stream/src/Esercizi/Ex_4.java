package Esercizi;

/*
 * Unione di due stream:
 *  Unisci due stream di stringhe in un unico stream.
 * Esempio: ["a", "b", "c"], ["d", "e", "f"] -> ["a", "b", "c", "d", "e", "f"]
 * */

public class Ex_4 {

	public static List<String> ex4(List<String> list1, List<String> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
    }
}
