package it.beije.xvii.exercises.Caroselli.exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RowSumOddNumbers {

//    Dato il seguente triangolo di numeri dispari:                    1
//            3       5
//            7       9     11
//            13     15     17       19
//            21     23     25       27     29
//            ….
//    si scriva la funzione rowSumOddNumbers(int n) che calcola la somma dei numeri nella riga n-esima. Per esempio:
//    rowSumOddNumbers(1) = 1
//    rowSumOddNumbers(3) = 7 + 9 + 11 = 27
//    ecc.
//

    public static void main(String[] args) {

        System.out.println("Inserisci il numero della riga di cui vuoi sommarne gli elementi");
        Scanner scanner = new Scanner(System.in);

        int numberOfRows = scanner.nextInt();

        System.out.println(rowSumOddNumbers(numberOfRows));

    }

    //calcola la somma dei numeri della riga n-esima
    public static int rowSumOddNumbers(int n) {
        int rows = 4;
        int sum = 0;
        Integer[] row1 = {3, 5};
        Integer[] row2 = {7, 9, 11};
        Integer[] row3 = {13, 15, 17, 19};
        Integer[] row4 = {21, 23, 25, 27, 29};

        Map<Integer[], Integer> mapOfNumberOfRowAndNumbersInRow = new HashMap<>();
        mapOfNumberOfRowAndNumbersInRow.put(row1, 1);
        mapOfNumberOfRowAndNumbersInRow.put(row2, 2);
        mapOfNumberOfRowAndNumbersInRow.put(row3, 3);
        mapOfNumberOfRowAndNumbersInRow.put(row4, 4);

        if (n > rows || n < 0) {
            System.out.println("la riga del triangolo che vuoi sommare non e' valida");
        }

        Integer[] rowN = null;
        for (Map.Entry<Integer[], Integer> entry : mapOfNumberOfRowAndNumbersInRow.entrySet()) {
            if (entry.getValue() == n) {
                rowN = entry.getKey();
                break;
            }
        }

        if (rowN != null) {


            for (int number : rowN) {
                sum += number;
            }

            System.out.println("La somma dei numeri nella riga " + n + " è: " + sum);

        }
        return sum;
    }
}
