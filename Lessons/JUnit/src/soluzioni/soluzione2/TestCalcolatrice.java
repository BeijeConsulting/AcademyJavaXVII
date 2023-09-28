package soluzioni.soluzione2;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class TestCalcolatrice {
	private Calcolatrice calcolatrice;
	@Before
	public void setUp() {
	        calcolatrice = new Calcolatrice();
	}
	@Test
	public void testSomma() {
	    assertEquals(5, calcolatrice.somma(2, 3));
	}

	@Test
	public void testSottrazione() {
	    assertEquals(2, calcolatrice.sottrazione(5, 3));
	}

	@Test
	public void testMoltiplicazione() {
	    assertEquals(12, calcolatrice.moltiplicazione(4, 3));
	}

	@Test
	public void testDivisione() {
	    assertEquals(2, calcolatrice.divisione(6, 3), 0.01);
	}

	@Test(expected = ArithmeticException.class)
	public void testDivisionePerZero() {
	    calcolatrice.divisione(5, 0);
	}
	
	@Test
	public void testRadiceQuadrata() {
	    assertEquals(4, calcolatrice.radiceQuadrata(16),0.01);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testRadiceQuadrataMinoreDiZero() {
	    calcolatrice.radiceQuadrata(-5);
	}
}
