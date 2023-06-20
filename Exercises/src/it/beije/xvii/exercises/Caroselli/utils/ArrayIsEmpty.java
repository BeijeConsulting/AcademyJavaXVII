package utils;

public class ArrayIsEmpty {
    public static boolean arrayIsEmpty(int[] array) {
        boolean isEmpty = false;
        if (array.length == 0) {
            System.out.println("L'array e' vuoto");
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean objIsEmpty(Object[] objects) {
        boolean isEmpty = false;
        if (objects == null) {
            System.out.println("L'oggetto e' vuoto");
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean arrayOfStringIsEmpty(String[] array) {
        boolean isEmpty = false;
        if (array == null) {
            System.out.println("La stringa e' vuota");
            isEmpty = true;
        }
        return isEmpty;
    }


}
