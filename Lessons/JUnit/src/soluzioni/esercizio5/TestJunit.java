package soluzioni.esercizio5;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class TestJunit {
	
	String[][] allTests = {{},
						   {},
						   {},
						   {},
						   {},
						   {},
						   {},
						   {},
						   {},
						   {},
						   {},
						   {},
						   {},
							};
	
	String[][] allSolutions = {{},
							   {},
							   {},
							   {},
							   {},
							   {},
							   {},
							   {},
							   {},
							   {},
							   {},
							   {},
							   {},
								};
	OptimisePath op = new OptimisePath();
	
	@Ignore
	@Test
	public void test() {
		for (int i  = 0; i < allTests.length; i++) {
			
			assertArrayEquals(allSolutions[i], op.optimise(allTests[i]));
		}
	}
	
	@Test
	public void nullTest() {
		assertNull(op.optimise(null));
	}
	
	@Test
	public void emptyTest() {
		assertNull(op.optimise(new String[] {}));
	}

	@Test
	public void emptyTest2() {
		assertNull(op.optimise(new String[] {""}));
		assertNull(op.optimise(new String[] {"", ""}));
		assertNull(op.optimise(new String[] {"", ""}));
		assertNull(op.optimise(new String[] {"", ""}));
	}
}
