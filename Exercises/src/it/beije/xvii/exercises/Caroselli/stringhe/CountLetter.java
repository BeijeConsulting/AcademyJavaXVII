package it.beije.xvii.exercises.Caroselli.stringhe;


import java.util.Scanner;

//  Scrivere il metodo
//
// public int contaLettera(char c, String str)
//
//  che conta le occorrenze della lettera c nella stringa str

public class CountLetter {
    public void countLetter() {
        System.out.println("Inserisci la parola");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        System.out.println("Inserisci la lettera di cui vuoi contare le occorrenze ");
        Scanner scanner1 = new Scanner(System.in);
        char c = scanner1.next().charAt(0);
        count(c, word);

    }

    public void count(char c, String str) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == c) {
                count = count + 1;
            }
        }
        System.out.println("La lettera " + c + "si trova nella parola " + count + " volte");
    }
}
