package it.beije.xvii.exercises.Caroselli.stringhe;

import java.util.Scanner;

//  Scrivere un programma Contrario che, data una stringa, la stampa al contrario. Per esempio, la stringa “Viva Java!” verrà “!avaJ aviV”
public class ReverseString {
    public void reverseString() {
        System.out.println("Inserisci la parola che si vuole stampare al contrario");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        StringBuilder reversed = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }

        System.out.println(reversed);
    }

    public String reverseString(String str) {

        StringBuilder reversed = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }

        return reversed.toString();
    }
}
