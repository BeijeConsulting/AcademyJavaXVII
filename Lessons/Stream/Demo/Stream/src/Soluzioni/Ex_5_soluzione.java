package Soluzioni;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Demo.Persona;

public class Ex_5_soluzione {
	
	public static Map<Integer, List<Persona>> ex5(List<Persona> p) {
        Map<Integer, List<Persona>> peopleByAge = p.stream()
                .collect(Collectors.groupingBy(per -> per.getEta()));
        return peopleByAge;
    }

}
