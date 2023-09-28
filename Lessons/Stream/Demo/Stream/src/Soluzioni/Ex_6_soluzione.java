package Soluzioni;

import java.util.List;
import java.util.stream.Collectors;

import Demo.Persona;

/*
 * Filtraggio avanzato:
 * (fare riferimento alla classe persona nel package Demo) 
 * 
 * Filtra le persone in base all'età (ad esempio eta' >= 30) e al nome che inizia con una determinata lettera (ad esempio 'A', case sensitive)
 * raccogli in una nuova lista gli elementi filtrati.
 * 
 * Esempio: [Persona("Andrea", 40, "Firenze"), Persona("Luigi", 20, "Antrodoco"), Persona("Antonio", 10, "Domodossola")] -> 
 * 			[Persona("Andrea", 40, "Firenze")]
 * */

public class Ex_6_soluzione {
	
	public static List<Persona> filteredListOfPeople(List<Persona> people) {
		List<Persona> filteredPeople = people.stream() // // crea uno stream a partire dalla lista di input "people"
                .filter( // il metodo filter serve per definire il criterio di filtraggio. 
                		
                		p -> p.getEta() >= 30 && p.getNome().startsWith("A")// La lambda expression specifica che vogliamo tenere solo le persone con età maggiore o uguale a 30 anni e il cui nome inizia con la lettera 'A'.
                		)
                .collect( // collect serve per raccogliere in una nuova lista gli elementi filtrati. 
                		Collectors.toList() // Collectors.toList() è utilizzato per creare una lista contenente gli elementi filtrati.
                		);
		return filteredPeople;
	}

}
