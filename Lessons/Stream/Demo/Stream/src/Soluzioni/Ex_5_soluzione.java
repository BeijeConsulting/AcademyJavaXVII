package Soluzioni;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Demo.Persona;

/*
 * (fare riferimento alla classe persona nel package Demo) 
 * Raggruppa una lista di persone per età e raccogli in una nuova lista gli elementi.
 * Esempio: [Persona("Mario", 4, "Roma"), Persona("Luigi", 30, "Milano")] ->
 * 			 {4=[Persona [nome=Mario, eta=4, citta=Roma]], 30=[Persona [nome=Luigi, eta=30, citta=Milano]]}
 * */

public class Ex_5_soluzione {
	
	public static Map<Integer, List<Persona>> ex5(List<Persona> p) {
        Map<Integer, List<Persona>> peopleByAge = p.stream() // creato uno stream a partire dalla lista di oggetti "Persona" p
                .collect( //  il metodo collect con Collectors.groupingBy serve a raggruppare le persone in base all'età.
                		Collectors.groupingBy( // crea una mappa in cui le chiavi sono le età e i valori sono le liste delle persone con quella età.
                				per -> per.getEta() // lambda utilizzata per estrarre l'età da ciascuna persona e usarla come chiave nel raggruppamento.
                				)
                		);
        return peopleByAge;
    }

}
