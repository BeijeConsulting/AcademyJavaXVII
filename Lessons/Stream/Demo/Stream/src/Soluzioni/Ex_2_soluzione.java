package Soluzioni;

import java.util.List;
import java.util.stream.Collectors;

public class Ex_2_soluzione {
	
	public List<String> ex2(List<String> stringsList) {
        List<String> uppercaseStrings = stringsList.stream()
                .map(x -> x.toUpperCase())
                .collect(Collectors.toList());
        return uppercaseStrings;
        
    }

}
