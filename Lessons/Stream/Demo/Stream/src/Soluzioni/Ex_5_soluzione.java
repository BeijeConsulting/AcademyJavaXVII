package Soluzioni;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Demo.Persona;

/*
 * (fare riferimento alla classe persona nel package Demo) 
 * Raggruppa una lista di persone per età.
 * Esempio: [Person("Mario", 10), Person("Luigi", 20), 
 * 			 Person("Pippo", 10)] -> {10: [Person("Mario", 10), 
 * 			 Person("Pippo", 10)], 20: [Person("Luigi", 20)]}
 * */

public class Ex_5_soluzione {
	
	public static Map<Integer, List<Persona>> ex5(List<Persona> p) {
        Map<Integer, List<Persona>> peopleByAge = p.stream() // creato uno stream a partire dalla lista di oggetti "Persona" p
                .collect( //  il metodo collect con Collectors.groupingBy serve a raggruppare le persone in base all'età.
                		Collectors.groupingBy(per -> per.getEta())
                		);
        return peopleByAge;
    }

}
