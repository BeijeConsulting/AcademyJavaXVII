package Esercizi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Demo.Persona;

/*
 *1- Raggruppare, utilizzando lo stream(), le persone in base all'attributo citta' e raccogli in una nuova lista gli elementi.
 *	 Esempio: [Persona("Mario", 10, "Roma"), Persona"Luigi", 20, "Milano"), Persona("Pippo", 10, "Roma")] -> 
 *			  {Persona{nome=Mario, eta=40, citta=Roma} = Citta [nome=Roma, capoluogo=true, diMare=false]}
 *
 *2- Raggruppare le persone in base all'attributo booleano diMare della classe Citta creata precedentemente utilizzando lo stream()
 *	 e raccogli in una nuova lista gli elementi.
 *	 Esempio: [Persona("Mario", 10, "Roma")), Persona("Luigi", 20, "Napoli")))] -> 
 *	 {true: [Persona("Luigi", 10, "Napoli")], false: [Persona("Mario", 10, "Roma")]}
 *
*3- Utilizza lo stream con le classi Persona e Citta' per ottenere una lista limitata di 3 città uniche
 * 	 in cui vive almeno una persona di età superiore a 30 anni e raccogli in una nuova lista gli elementi filtrati.
 * 	 Esempio: [Persona("Mario", 40, "Roma"), Persona("Luigi", 10, "Roma")] ->
 *            [Citta [nome=Roma, capoluogo=true, diMare=false]]
 * */


public class Ex_9 {

    public static Map<Persona, String> personsByCity(List<Persona> inputList) {
    	//TODO
        return null;
    }

    public static Map<String, List<Persona>> personsByCityOfSea(List<Persona> inputList) {
    	//TODO
        return null;
    }
    
    public static List<String> getListOfCities(List<Persona> ps) {
    	//TODO
    	return null;
    }
    
}