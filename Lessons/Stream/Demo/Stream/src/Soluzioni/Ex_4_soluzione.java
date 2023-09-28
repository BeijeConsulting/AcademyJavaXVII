package Soluzioni;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex_4_soluzione {
	
	public static List<String> ex4(List<String> list1, List<String> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
    }

}
