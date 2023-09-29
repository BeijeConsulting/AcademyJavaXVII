package soluzioni.esercizio5;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

public class TestJunit {
	
	String[][] allTests = {{"S", "S", "N", "E"},
						   {"S", "N"},
						   {"E"},
						   {"W", "W", "N", "S", "N"},
						   {"W", "E", "E", "E", "S"},
						   {"N", "N", "E", "W", "S"},
						   {"E", "E", "W", "E", "S","N", "W", "E", "E", "S"}};
	
	String[][] allSolutions = {{"S", "E"},
							   {""},
							   {"E"},
							   {"W", "W", "N"},
							   {"E", "E", "S"},
							   {"N"},
							   {"E", "E", "E", "S"}};
	
	OptimisePath op = new OptimisePath();
	
	@Test
	public void test() {
		for (int i  = 0; i < allTests.length; i++) {
//			System.out.println("ehi");
//			System.out.println(Arrays.toString(allSolutions[i]));
//			System.out.println(Arrays.toString(op.optimise(allTests[i])));
			assertArrayEquals(allSolutions[i], op.optimise(allTests[i]));
		}
	}
	
	@Test
	public void nullTest() {
		assertNull(op.optimise(null));
	}
	
	@Test
	public void emptyTest() {
		assertArrayEquals(new String[] {}, op.optimise(new String[] {""}));
		assertArrayEquals(new String[] {}, op.optimise(new String[] {"", ""}));
		assertArrayEquals(new String[] {}, op.optimise(new String[] {"", "", ""}));
		assertArrayEquals(new String[] {}, op.optimise(new String[] {"", "", "", ""}));
	}
}
