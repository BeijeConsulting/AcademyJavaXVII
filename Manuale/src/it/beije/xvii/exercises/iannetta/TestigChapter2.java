package it.beije.xvii.exercises.iannetta;

import java.io.File;

public class TestigChapter2 {

	public static void main(String[] args) {
		long a = 10;
		int b = 5;
		b = b * (int)a;
		System.out.println("b: " + b);
		
		TestClass testClass = new TestClass();
		String ss = testClass.s;
		if(testClass instanceof TestClass) System.out.println(ss);
		
		File x = new File("myFile.txt");
		File y = new File("myFile.txt");
		File z = x;
		System.out.println(x == y); // Outputs false
		System.out.println(x == z); // Outputs true
	}

}
