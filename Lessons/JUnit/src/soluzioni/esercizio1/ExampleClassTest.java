package soluzioni.esercizio1;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

/* OUTPUT FINALE:
 * La madre desidera un figlio più piccolo.
 * Nasce Pollicino, ma è minuscolo.
 * I genitori sono preoccupati.
 * I genitori abbandonano Pollicino nel bosco.
 * Pollicino trova una casa di animali.
 * Affronta molte sfide.
 * Alla fine, ritorna a casa con i genitori.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)		/*Testa i metodi in ordine alfabetico*/
public class ExampleClassTest {

	ExampleClass ec = new ExampleClass();
	String message = "";
	
	@Test
	public void c_dotto() {
		
		message = "I genitori sono preoccupati";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	@Test
	public void f_cucciolo() {

		message = "Affronta molte sfide";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	@AfterClass
	public static void _principe() {
		
		String message = "\nFine";
		
		System.out.print(message);
		
	}
	
	@Test
	public void b_gongolo() {

		message = "Nasce Pollicino, ma è minuscolo";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	@Before
	public void _reginaCattiva() {

		message = "\n";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	@Test
	public void e_eolo() {

		message = "Pollicino trova una casa di animali";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	@BeforeClass
	public static void _biancaneve() {

		String message = "Pollicino";
		
		System.out.print(message);
		
	}
	
	@Test
	public void d_brontolo() {

		message = "I genitori abbandonano Pollicino nel bosco";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	@Test
	public void a_mammolo() {

		message = "La madre desidera un figlio più piccolo";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	@Test
	public void g_pisolo() {

		message = "Alla fine, ritorna a casa con i genitori";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}	
	
	@After
	public void _cacciatore() {
		
		message = ".";
		ec.setMessage(message);
		
		System.out.print(message);
		
		assertEquals(message, ec.getMessage());
	}
	
	
}
