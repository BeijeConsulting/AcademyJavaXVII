package it.beije.xvii.exercises.Caroselli.stringBuilderUtils;

import it.beije.xvii.exercises.Caroselli.stringhe.ReverseString;


public class StringBuilderUtils {

    public static StringBuilder append(StringBuilder sb, String str) {
        if (str == null) {
            System.out.println("La stringa che cerchi di aggiungere e' vuota");
        }

        return new StringBuilder(sb + str);

    }

    public static StringBuilder insert(StringBuilder sb, int offset, String str) {
        if (sb == null || str == null || offset < 0 || offset > sb.length()) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        //uso l'append creato precedentemente
        return append(new StringBuilder(sb.substring(0, offset)), str);

    }

    public static StringBuilder delete(StringBuilder sb, int start, int end) {
        if (sb == null || start < 0 || start > sb.length() || end > sb.length() || end < 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }


        for (int i = start; i < sb.length() - end - start; i++) {
            sb.setCharAt(i, sb.charAt(i + end - start));
        }

        sb.setLength(sb.length() - end - start);

        return sb;
    }

    public static StringBuilder deleteCharAt(StringBuilder sb, int index) {
        if (sb == null || index < 0 || index >= sb.length()) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        for (int i = index; i < sb.length() - 1; i++) {
            sb.setCharAt(i, sb.charAt(i + 1));
        }

        sb.setLength(sb.length() - 1);

        return sb;


    }

    public static StringBuilder reverse(StringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        String sb1 = sb.toString();
        ReverseString reverse = new ReverseString();

        return new StringBuilder(reverse.reverseString(sb1));


    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("ciao come stai");
        String str = " come stai?";
        int offset = 2;
        int start = 0;
        int end = 3;
//        System.out.println(append(sb, str));
//        System.out.println(insert(sb, offset, str));
//        System.out.println(reverse(sb));
        System.out.println(deleteCharAt(sb, offset));
    }
}
