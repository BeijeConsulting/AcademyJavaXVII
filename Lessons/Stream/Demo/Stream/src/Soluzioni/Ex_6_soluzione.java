package Soluzioni;

import java.util.List;
import java.util.stream.Collectors;

import Demo.Persona;

public class Ex_6_soluzione {
	
	public static List<Persona> filteredListOfPeople(List<Persona> people) {
		List<Persona> filteredPeople = people.stream()
                .filter(p -> p.getEta() >= 30 && p.getNome().startsWith("A"))
                .collect(Collectors.toList());

		return filteredPeople;
	}

}
