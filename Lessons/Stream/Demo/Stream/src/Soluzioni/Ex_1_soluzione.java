package Soluzioni;

import java.util.List;
import java.util.stream.Collectors;

public class Ex_1_soluzione {
	
	public static List<Integer> ex1(List<Integer> integersList) {
	
	List<Integer> evenNumbers = integersList.stream()
            .filter(x -> x % 2 == 0)
            .collect(Collectors.toList());
    return evenNumbers;
	}

}
