package it.beije.xvii.exercises.Caroselli.exercises;

import java.util.HashMap;
import java.util.Map;

public class Ex {

//    Lo scopo di questo esercizio è convertire una stringa in una nuova stringa,
//    dove ogni carattere della nuova stringa è ‘(‘ se il carattere corrispondente nella stringa
//    originale è unico, altrimenti vale ‘)’. Ignorare le maiuscole/minuscole per decidere se un carattere è
//    duplicato o meno.Per esempio:”din” => “(((“”recede” => “()()()”
//            “Success” => “)())())”
//
//            “(( @” => “))((“

    public static String conversion(String s) {

        Map<Character, Integer> frequencyMap = new HashMap<>();
        StringBuilder convStr = new StringBuilder();
        ;
        for (char c : s.toLowerCase().toCharArray()) {
            Integer frequency = frequencyMap.get(c);
            if (frequency == null) {
                frequencyMap.put(c, 1);
            } else {
                frequencyMap.put(c, frequency + 1);
            }
        }

        for (char c : s.toCharArray()) {
            Integer frequency = frequencyMap.get(c);

            if (frequency != 1) {
                convStr.append(')');
            } else {
                convStr.append('(');
            }
        }

        return convStr.toString();


    }

    public static void main(String[] args) {
        String s = "ciaooo";
        System.out.println(conversion(s));
    }
}
