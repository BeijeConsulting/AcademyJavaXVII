package Soluzioni;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Demo.Persona;
import Esercizi.Citta;

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


 class Ex_9_soluzione {
	
	public static Map<Persona, String> personsByCity(List<Persona> inputList) {
        return inputList.stream() // crea uno stream a partire dalla lista di input "inputList"
        		.collect( //  il metodo collect serve per raccogliere gli elementi dello stream in una mappa
        				Collectors.toMap( // crea una mappa in cui le chiavi sono le persone e i valori sono le città corrispondenti
        						p -> p, p -> p.getCitta())
        				);
    }

    public static Map<String, List<Persona>> personsByCityOfSea(List<Persona> inputList) {
        return inputList.stream()//  // crea uno stream a partire dalla lista di input "inputList"
        		.collect(
        				Collectors.groupingBy( // raggruppa le persone in base alla città delle persone
        						p -> p.getCitta())
        				); // La mappa risultante ha le città come chiavi e le liste di persone che vivono in ciascuna città come valori
    }
    
    public static List<String> getListOfCities(List<Persona> ps) {
    	List<String> listOfCities = ps.stream()
                .filter(p -> p.getEta() > 30) //filtra le persone in base all'eta' superiore a 30 anni
                .map(p -> p.getCitta()) //prende ciascun elemento rimanente dello stream e mappa l'oggetto Citta associato a quella persona
                .distinct()//rimuove i duplicati dallo stream risultante, in modo che ogni numero appaia solo una volta
                .limit(3) //limita il numero di citta'
                .collect(Collectors.toList()); //raccoglie gli elementi dello stream risultanti e li converte in una lista di tipo List<Citta>
        return listOfCities;
    }
}
