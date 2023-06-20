package array;

import static utils.ArrayIsEmpty.arrayIsEmpty;
import static utils.ArrayIsEmpty.objIsEmpty;

public class BooleanContains {

    //deve restituire true se l'elemento e' presente nell'array, altrimenti false
    public void isPresent() {
        int[] array = {30, 30, 50, 60};
        Integer[] integerArray = {20, 10, 40};
        Integer[] integerArray2 = {};
        Integer integer = 3;
        int num = 30;
        booleanContains(num, array);
        booleanContainsObj(integer, integerArray2);

    }


    //scrivere un metodo “boolean contains(int e, int[] array)” che restituisca true se l’elemento e è presente nell’array, false altrimenti
    public void booleanContains(int e, int[] array) {
        boolean isPresent = false;
        if (!arrayIsEmpty(array)) {
            for (int j : array) {
                if (j == e) {
                    isPresent = true;
                    break;
                }
            }
            System.out.println(isPresent);
        } else {
            System.out.println(arrayIsEmpty(array));
        }
    }

    //scrivere un metodo “boolean contains(Object e, Object[] array)” che restituisca true se l’elemento e è presente nell'oggetto , false altrimenti
    public void booleanContainsObj(Object e, Object[] objects) {
        boolean isPresent = false;
        if (!objIsEmpty(objects)) {
            for (Object o : objects) {
                if (o.equals(e)) {
                    isPresent = true;
                    break;
                }
            }
            System.out.println(isPresent);
        } else {
            System.out.println(objIsEmpty(objects));
        }
    }


}
