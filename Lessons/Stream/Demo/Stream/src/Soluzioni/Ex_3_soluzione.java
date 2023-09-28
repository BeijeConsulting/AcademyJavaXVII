package Soluzioni;

import java.util.List;

public class Ex_3_soluzione {
	
	public static int ex3(List<Integer> integersList) {
        int sum = integersList.stream()
                .reduce(0, (x, y) -> x + y);
        return sum;
    }

}
