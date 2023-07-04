package it.beije.xvii.exercises.Caroselli.stringhe;



import java.util.Scanner;

public class Concat {
    public void concat() {

        StringBuilder result = new StringBuilder();

        for (int i = 1; i < 4; i++) {
            System.out.println("Scrivi la parola " + i + " che vuoi concatenare");
            Scanner scanner = new Scanner(System.in);
            String word = scanner.nextLine();
            result.append(word).append("*");
        }
        System.out.println("Le parole concatenate sono: " + result);


    }
}
