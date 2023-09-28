package Esercizi;

/*
 * Filtraggio avanzato: 
 * Filtra le persone in base all'età e al nome che inizia con una determinata lettera, ad esempio 'A'.
 * Esempio: [Person("Mario", 10), Person("Luigi", 20), Person("Pippo", 10)] -> 
 * 			[Person("Mario", 10), Person("Pippo", 10)]
 * */

public class Ex_6 {
	public static List<Persona> filteredListOfPeople(List<Persona>) {
		List<Persona> filteredPeople = people.stream()
                .filter(p -> p.getAge() >= 30 && p.getName().startsWith("A"))
                .collect(Collectors.toList());

		
	}
	
}
