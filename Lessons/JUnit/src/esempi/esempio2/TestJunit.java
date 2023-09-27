package esempi.esempio2;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.model.TestTimedOutException;

public class TestJunit {
	OrderAlgorithms oa = new OrderAlgorithms();
	int[] array = {2, 1, 6, 7, 8, 3, 4, 5, 0};
	int[] orderedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
	
	static int count = 0;
	
	@BeforeClass
	static public void startTests() {
		System.out.println("Testing ...\n");
	}
	
	@AfterClass
	static public void endTests() {
		System.out.println("End of all tests\n");
	}
	
	@Before
	public void startTest() {
		System.out.println("Starting test " + ++count);
	}
	
	@After
	public void endTest() {
		System.out.println("End test\n");
	}
	
	@Test
	public void testBubbleSort() {
		//assertEquals(Arrays.toString(orderedArray),Arrays.toString(oa.bubbleSort(array)));
		assertEquals(orderedArray, array);
	}
	   
	@Test(timeout = 500)
	public void testSelectionSort() {
		//assertEquals(Arrays.toString(orderedArray),Arrays.toString(oa.selectionSort(array)));
		assertEquals(orderedArray, array);
	}
	
	@Test
	public void testFakeSort() {
		//assertEquals(Arrays.toString(orderedArray),Arrays.toString(oa.fakeSort(array)));
		assertEquals(orderedArray, array);
	}
	
	@Test(timeout = 500)
	public void testTimeoutSort() {
		//assertEquals(Arrays.toString(orderedArray),Arrays.toString(oa.timeoutSort(array)));
		assertEquals(orderedArray, array);
	}
	
}
