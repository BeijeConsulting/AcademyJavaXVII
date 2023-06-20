package array;

import utils.TestCase;

import java.util.Arrays;
import java.util.List;

import static utils.ArrayIsEmpty.arrayIsEmpty;

public class PrintZigZag {

    //Scrivere un programma StampaZigZag che, dato un array di 10 numeri interi
    // contenente valori a piacere, ne stampa gli elementi secondo il seguente ordine:
    // il primo, l’ultimo, il secondo, il penultimo, il terzo, il terz’ultimo, ecc…

    public static void printZigZag(int[] array) {

        if (!arrayIsEmpty(array)) {
            for (int i = 0; i < array.length / 2; i++) {
                System.out.println(array[i] + " : " + array[(array.length - 1) - i]);

            }
            if (array.length % 2 != 0) {
                System.out.println(array[array.length / 2]);
            }

        }
    }

    private final static List<TestCase<int[], List<Integer>>> testCases = Arrays.asList(
            new TestCase<>(new int[]{2, 4, 6, 8}, Arrays.asList(2, 8, 4, 6)),
            new TestCase<>(new int[]{1, 2, 4, 4, 2}, Arrays.asList(1, 2, 2, 4, 4)),
            new TestCase<>(new int[]{}, null),
            new TestCase<>(new int[]{-1, 0, -1}, Arrays.asList(-1, -1, 0)));


    public static void tests() {
        for (TestCase<int[], List<Integer>> test : testCases) {
            printZigZag(test.getValue());

        }
    }

}
