package array;

import utils.TestCase;

import java.util.Arrays;
import java.util.List;

import static utils.ArrayIsEmpty.arrayIsEmpty;

//Trovare l’indice del massimo elemento in un array (o il minimo)
public class FindMaxOrMinIndex {

    public static int findMaxIndex(int[] array) {

        int index = 0;
        int max = 0;

        if (!arrayIsEmpty(array)) {

            for (int i = 0; i < array.length; i++) {
                if (max < array[i]) {
                    max = array[i];
                    index = i;
                }
            }

        }
        return index;
    }

    public static int findMinIndex(int[] list) {

        int index = 0;

        if (!arrayIsEmpty(list)) {
            int min = list[0];
            for (int i = 0; i < list.length; i++) {
                if (min > list[i]) {
                    min = list[i];
                    index = i;
                }

            }
        }
        return index;
    }

    public static void test() {
        for (TestCase<int[], Integer> test : testCasesMaxIndex) {
            int index = findMaxIndex(test.getValue());
            System.out.println("Il valore restituito e' : " + index + ", il valore atteso e' : " + test.getExpectedValue());

        }

        for (TestCase<int[], Integer> test : testCasesMinIndex) {
            int index = findMinIndex(test.getValue());
            System.out.println("Il valore restituito e' : " + index + ", il valore atteso e' : " + test.getExpectedValue());
        }
    }

    private static final List<TestCase<int[], Integer>> testCasesMaxIndex = Arrays.asList(
            new TestCase<>(new int[]{2, 50, 100, 500}, 3),
            new TestCase<>(new int[]{}, null)
    );

    private static final List<TestCase<int[], Integer>> testCasesMinIndex = Arrays.asList(
            new TestCase<>(new int[]{2, 50, 100, 500}, 0),
            new TestCase<>(new int[]{}, null)
    );


}
