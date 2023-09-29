package Demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemoStream {
	
	public static void main(String[] args) {
		List<Persona> persone = Arrays.asList(
				new Persona("Alice", 25, "Roma"),
				new Persona("Bob", 30, "Milano"),
				new Persona("Charlie", 17, "Napoli"),
				new Persona("David", 21, "Firenze")
				);
		
		//Filtraggio persone maggiori di 18 anni
		List<Persona> maggiorenni = persone.stream()
										.filter(persona -> persona.getEta() > 18)
										.collect(Collectors.toList());
		
		//Estrazione nomi persone
		List<String> nomi = maggiorenni.stream()
								.map(Persona::getNome)
								.collect(Collectors.toList());
		
		//Riduzione nomi in una singola stringa separata da virgole
		String nomiConcatenati = nomi.stream()
									.collect(Collectors.joining(", "));
		
		//Stampa risultati
		System.out.println("Persone maggiori di 18 anni " + maggiorenni );
		System.out.println("Nomi delle persone maggiori di 18 anni " + nomiConcatenati);
	}

}
