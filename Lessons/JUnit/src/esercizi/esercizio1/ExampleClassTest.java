package esercizi.esercizio1;

import static org.junit.Assert.assertEquals;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

/*
 * Aggiungi le annotazioni sui metodi in modo che l'output sia sensato.
 * Se hai bisogno che alcuni test seguano un ordine preciso, allora
 * aggiungi delle lettere davanti al nome, così da eseguirli in ordine alfabetico.
 * Se necessario puoi modificare il tipo di metodo
 * 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)		/*Testa i metodi in ordine alfabetico*/
public class ExampleClassTest {

	ExampleClass ec = new ExampleClass();
	String message = "";
	
	public void _dotto() {
		
		message = "I genitori sono preoccupati";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _cucciolo() {

		message = "Affronta molte sfide";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _principe() {
		
		message = "Fine";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _gongolo() {

		message = "Nasce Pollicino, ma è minuscolo";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _reginaCattiva() {

		message = "\n";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _eolo() {

		message = "Pollicino trova una casa di animali";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _biancaneve() {

		message = "Pollicino";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _brontolo() {

		message = "I genitori abbandonano Pollicino nel bosco";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _mammolo() {

		message = "La madre desidera un figlio più piccolo";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	public void _pisolo() {

		message = "Alla fine, ritorna a casa con i genitori";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	
	public void _cacciatore() {
		
		message = ".";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
}
