package Soluzioni;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex_7_soluzione {
	public static List<Integer> filteredList(List<Integer> list1, List<Integer> list2) {
		List<Integer> mergedAndSorted = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
		return mergedAndSorted;
	}

}
