package Esercizi;

/*
 * Raggruppa una lista di persone per età.
 * Esempio: [Person("Mario", 10), Person("Luigi", 20), 
 * 			 Person("Pippo", 10)] -> {10: [Person("Mario", 10), 
 * 			 Person("Pippo", 10)], 20: [Person("Luigi", 20)]}
 * */

public class Ex_5 {
	
	 public static Map<Integer, List<Persona>> ex5(List<Persona> p) {
	        Map<Integer, List<Persona>> peopleByAge = p.stream()
	                .collect(Collectors.groupingBy(per -> per.getEta()));
	        return peopleByAge;
	    }
}
