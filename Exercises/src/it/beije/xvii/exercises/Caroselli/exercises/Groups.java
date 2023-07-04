package it.beije.xvii.exercises.Caroselli.exercises;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Groups {

//    Scrivere una funzione che verifichi la correttezza di una stringa in cui
//    sono inserite delle parentesi. In programmazione, ma anche nel linguaggio naturale,
//    le istruzioni o le frasi possono essere inserite in gruppi di parentesi: (), [] o {}.
//    All’apertura di una parentesi ci si aspetta una chiusura coerente, tipo: [], ([]), {[]({})}.
//    I gruppi che non vengono chiusi o non vengono chiusi in ordine corretto vengono considerati sbagliati.

    public static boolean groupCheck(String s) {
        char c1 = '(';
        char c2 = '[';
        char c3 = '{';
        char c4 = ')';
        char c5 = ']';
        char c6 = '}';
        boolean isStringOk = true;

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == c1 || c == c2 || c == c3) {
                stack.push(c);
            } else if (c == c4 || c == c5 || c == c6) {
                if (stack.isEmpty()) {
                    isStringOk = false;
                    break;
                }

                char firstElement = stack.pop();

                if ((c == c4 && firstElement != c1) || (c == c5 && firstElement != c2) || (c == c6 && firstElement != c3)) {
                    isStringOk = false;
                    break;
                }
            }
        }

        return isStringOk;
    }

    public static void main(String[] args) {
        String str = "()[ddd]";
        System.out.println(groupCheck(str));

    }
}
