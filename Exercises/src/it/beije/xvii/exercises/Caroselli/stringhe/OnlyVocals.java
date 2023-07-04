package it.beije.xvii.exercises.Caroselli.stringhe;


import java.util.Scanner;

//Scrivere un programma SoloVocali che, data una stringa, ne stampa le sole vocali

public class OnlyVocals {
    public void printVocals() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci una parola: ");
        String word = scanner.nextLine().toLowerCase();
        char[] vocals = {'a', 'e', 'i', 'o', 'u'};

        System.out.print("Le sole vocali sono: ");

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (isVowel(c, vocals)) {
                System.out.print(c);
            }
        }

        System.out.println();
    }


    public boolean isVowel(char c, char[] vocals) {
        for (char vowel : vocals) {
            if (c == vowel) {
                return true;
            }
        }
        return false;
    }
}
