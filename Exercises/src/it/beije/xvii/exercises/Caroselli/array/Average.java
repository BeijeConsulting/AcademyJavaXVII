package it.beije.xvii.exercises.Caroselli.array;

import it.beije.xvii.exercises.Caroselli.utils.ArrayIsEmpty;

// Scrivere un programma MediaMultipliDiTre che calcoli la media di un array
// di numeri interi, considerando i soli numeri divisibili per tre.

// Scrivere un programma Media che calcoli la media di un array di numeri interi

public class Average {
    public void returnAverage() {
        int[] array = {3, 5, 6, 8, 9, 30};
        System.out.println(averageMultiplesOfThree(array));
        System.out.println(average(array));

    }

    public int averageMultiplesOfThree(int[] array) {
        int sum = 0;
        int count = 0;

        if (!ArrayIsEmpty.arrayIsEmpty(array)) {
            for (int i = 0; i < array.length; i++) {
                if ((array[i] % 3) == 0) {
                    sum = sum + array[i];
                    count = count + 1;

                }
            }
        } else {
            System.out.println(ArrayIsEmpty.arrayIsEmpty(array));
        }
        return sum / count;
    }


    public int average(int[] array) {
        int sum = 0;
        int count = 0;

        if (!ArrayIsEmpty.arrayIsEmpty(array)) {
            for (int i = 0; i < array.length; i++) {
                sum = sum + array[i];
                count = count + 1;
            }
        } else {
            System.out.println(ArrayIsEmpty.arrayIsEmpty(array));
        }
        return sum / count;
    }


}
