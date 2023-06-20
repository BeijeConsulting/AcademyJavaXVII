package cicli;

import java.util.InputMismatchException;
import java.util.Scanner;

//  Scrivere un programma che stampi le tabellina del numero dato come argomento
public class MultiplicationTable {
    public void multiplicationTable() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci un numero: ");
        int selectedNumber = scanner.nextInt();

        for (int i = 1; i <= 10; i++) {
            int table = selectedNumber * i;
            System.out.println(table);
        }
    }

    public void multiplicationTableWithControls() {

        Scanner scanner = new Scanner(System.in);
        try {

            int selectedNumber;

            do {
                System.out.print("Inserisci il numero maggiore o uguale a zero di cui vuoi calcolare la tabellina: ");
                selectedNumber = scanner.nextInt();

                if (selectedNumber < 0) {
                    System.out.println("Il numero inserito è minore di zero. Riprova.");
                }
            } while (selectedNumber < 0);

            for (int i = 1; i <= 10; i++) {
                int table = selectedNumber * i;
                System.out.println(table);
            }
        } catch (InputMismatchException ex) {
            System.out.println("Input non valido");
        }
    }


}
