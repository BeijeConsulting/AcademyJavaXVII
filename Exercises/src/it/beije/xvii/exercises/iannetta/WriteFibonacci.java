package it.beije.xvii.exercises.iannetta;


import java.io.FileWriter;
import java.io.IOException;

public class WriteFibonacci {

	//up to 50 millions it works
	
	public static void main(String[] args) throws IOException {
		
		FileWriter fileWriter = new FileWriter("C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\Fib.txt");
		long[] fib = new long[] {0L, 0L, 0L};
		
		int n = 1_000_000;
		
		for (int i = 1; i <= n; i++) {
			if (i == 1) {
				fib[0] = 1L;
			}
			else if (i == 2) {
				fib[1] = 1L;
			}
			else {
				fib[2] = fib[0] + fib[1];
				fib[0] = fib[1];
				fib[1] = fib[2];
			}
			//System.out.println(fib[2]);
			fileWriter.write("" + fib[2]);
			fileWriter.write("\n");			
		}
		fileWriter.flush();
		fileWriter.close();
		
	}

}
