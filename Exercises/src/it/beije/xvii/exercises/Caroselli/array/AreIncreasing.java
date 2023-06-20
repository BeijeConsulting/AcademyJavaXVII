package array;

import java.util.Arrays;

import static utils.ArrayIsEmpty.arrayIsEmpty;

//Verificare la sequenza crescente di un array. Il metodo “boolean isCrescente(int [] array)”
// restituisce true se tutti gli elementi dell’array passato sono in ordine crescente, false altrimenti.

public class AreIncreasing {

    public void printIfAreIncreasing() {
        int[] array = {2, 5, 6, 8, 29, 99};
        System.out.println(areIncreasing(array));
    }

    public boolean areIncreasing(int[] array) {
        boolean areIncreasing = false;
        int[] sortedArray = Arrays.stream(array).sorted().toArray();
        if (!arrayIsEmpty(array)) {
            for (int k : array) {
                for (int i : sortedArray) {
                    if (k == i) {
                        areIncreasing = true;
                    }
                }
            }
        } else {
            System.out.println(arrayIsEmpty(array));
        }
        return areIncreasing;
    }
}
