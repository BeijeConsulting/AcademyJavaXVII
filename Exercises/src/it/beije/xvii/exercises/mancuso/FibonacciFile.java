package it.beije.xvii.exercises.mancuso;

import java.io.FileWriter;
import java.io.IOException;

public class FibonacciFile {
	
	public static Long generateNumber(Long n1, Long n2) {		
		Long n = n1 + n2;		
		return n;							
	}
	
	public static void main(String[] args) throws IOException {
		Long n1 = 1L;
		Long n2 = 1L;
		
		System.out.println(n1);
		System.out.println(n2);
		
		FileWriter fw = new FileWriter("/Temp/fibonacci.txt");
		
		/*for(int i=0; i<20000000; i++) {
			Long n = generateNumber(n1,n2);
			n2 = n1;
			n1 = n;
			System.out.println(n);
			fw.write(n + "\n");
		}*/
		//fw.flush();
		fw.close();
	}

}
