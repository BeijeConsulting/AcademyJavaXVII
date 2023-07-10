package it.beije.xvii.exercises.ulloa;

import java.util.ArrayList;
import java.util.Objects;

public class ArrayListUtils {
	public static boolean equals(ArrayList<?> one, ArrayList<?> two) {
        if (one == two) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        int size = one.size();
        if (size != two.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            Object element1 = one.get(i);
            Object element2 = two.get(i);
            if (!Objects.equals(element1, element2)) {
                return false;
            }
        }
        return true;
    }
}