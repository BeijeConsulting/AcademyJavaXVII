package it.beije.xvii.exercises.ulloa;

public class StringBuilderUtils {

    public static StringBuilder append(StringBuilder sb, String str) {
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) {
            sb = appendChar(sb, str.charAt(i));
        }
        return sb;
    }

    public static StringBuilder insert(StringBuilder sb, int offset, String str) {
        int strLength = str.length();
        for (int i = 0; i < strLength; i++) {
            sb = insertChar(sb, offset + i, str.charAt(i));
        }
        return sb;
    }

    public static StringBuilder delete(StringBuilder sb, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            sb = deleteCharAt(sb, i);
        }
        return sb;
    }

    public static StringBuilder deleteCharAt(StringBuilder sb, int index) {
        int sbLength = sb.length();
        for (int i = index; i < sbLength - 1; i++) {
            sb.setCharAt(i, sb.charAt(i + 1));
        }
        sb.setLength(sbLength - 1);
        return sb;
    }

    public static StringBuilder reverse(StringBuilder sb) {
        int sbLength = sb.length();
        for (int i = 0; i < sbLength / 2; i++) {
            int j = sbLength - i - 1;
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
        return sb;
    }

    private static StringBuilder appendChar(StringBuilder sb, char c) {
        sb.ensureCapacity(sb.length() + 1);
        sb.setLength(sb.length() + 1);
        sb.setCharAt(sb.length() - 1, c);
        return sb;
    }

    private static StringBuilder insertChar(StringBuilder sb, int offset, char c) {
        sb.ensureCapacity(sb.length() + 1);
        sb.setLength(sb.length() + 1);
        for (int i = sb.length() - 1; i > offset; i--) {
            sb.setCharAt(i, sb.charAt(i - 1));
        }
        sb.setCharAt(offset, c);
        return sb;
    }
}
