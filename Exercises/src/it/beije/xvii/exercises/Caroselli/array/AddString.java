package array;

import java.util.Arrays;

import static utils.ArrayIsEmpty.arrayOfStringIsEmpty;

//  Scrivere il metodo: “public String [] addString(String s, String[] a)”,
//  che accetta come parametri una stringa ed un array di stringhe. Restituisce un nuovo array,
//  identico ad array, aggiungendo però, come ultimo elemento, la stringa s.

public class AddString {
    public static String[] addString(String s, String[] a) {

        String[] newArray = new String[a.length + 1];
        if (!arrayOfStringIsEmpty(a)) {
            System.arraycopy(a, 0, newArray, 0, a.length);
            newArray[newArray.length - 1] = s;
        } else {
            System.out.println(arrayOfStringIsEmpty(a));
        }
        return newArray;
    }

    public void add() {
        String[] array = {"Pippo", "Pluto", "Paperino"};
        String word = "Minnie";
        System.out.println(Arrays.toString(addString(word, array)));
    }


}
