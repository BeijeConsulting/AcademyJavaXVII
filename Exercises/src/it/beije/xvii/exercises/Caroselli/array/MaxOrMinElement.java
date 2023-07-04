package it.beije.xvii.exercises.Caroselli.array;



import java.util.Arrays;
import java.util.List;

import it.beije.xvii.exercises.Caroselli.utils.ArrayIsEmpty;
import it.beije.xvii.exercises.Caroselli.utils.TestCase;



//Trovare il massimo elemento in un array (o il minimo)

public class MaxOrMinElement {
    public static void maxElement(int[] array) {

        int max = 0;


        if (!ArrayIsEmpty.arrayIsEmpty(array)) {
            for (Integer integer : array) {
                if (max < integer) {
                    max = integer;
                }

            }
            System.out.println(max);
        }
    }


    public static void minElement(int[] array) {

        int min = 0;

        if (!ArrayIsEmpty.arrayIsEmpty(array)) {


            for (Integer integer : array) {
                if (min < integer) {
                    min = integer;
                }
            }
            System.out.println(min);
        }
    }

//    public void checkMaxOrMin() {
//        int[] list1 = {2,50,100};
//        int[] list2 = {};
//
//        maxElement(list1);
//        minElement(list2);
//
//    }


    //prove dei test con la classe TestCase
    private static final List<TestCase<int[], List<Integer>>> testCasesMax = Arrays.asList(
            new TestCase<>(new int[]{2, 50, 100, 500}, Arrays.asList(500)),
            new TestCase<>(new int[]{1, 2, 3, 4, 4, 2}, Arrays.asList(4)),
            new TestCase<>(new int[]{}, null),
            new TestCase<>(new int[]{-1, 0, -1}, Arrays.asList(0))
    );

    private static final List<TestCase<int[], List<Integer>>> testCasesMin = Arrays.asList(
            new TestCase<>(new int[]{2, 50, 100, 500}, Arrays.asList(2)),
            new TestCase<>(new int[]{1, 2, 3, 4, 4, 2}, Arrays.asList(1)),
            new TestCase<>(new int[]{}, null),
            new TestCase<>(new int[]{-1, 0, -1}, Arrays.asList(-1))
    );

    public static void test() {
        for (TestCase<int[], List<Integer>> test : testCasesMax) {
            maxElement(test.getValue());

        }

        for (TestCase<int[], List<Integer>> test : testCasesMin) {
            minElement(test.getValue());

        }
    }
}
