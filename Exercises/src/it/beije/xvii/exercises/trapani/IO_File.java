package it.beije.xvii.exercises.trapani;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;



public class IO_File {
	public static void main(String[] args) throws Exception {
		
		File file = new File("\\Users\\marty\\Desktop\\Marti\\Beije\\Esercizi Academy\\FILE ESERCIZI\\rubrica.csv");  //Apertura file eq in c a file opener
		
		FileReader fileReader = new FileReader(file);			
		BufferedReader bufferedReader = new BufferedReader(fileReader); 	 //lettura file stringa
		List<String> rows = new ArrayList<String>();						 //creazione arraylist per stringhe che leggo
		while(bufferedReader.ready()) {										 //bufferedReader.ready(), controlla che il file si possa ancora leggere
			String r = bufferedReader.readLine();
			rows.add(r);
			//System.out.println(r);
		}
		for (String row : rows) {
//			StringTokenizer tokenizer = new StringTokenizer(row, ";");		 //definisce i limitatori
//			while (tokenizer.hasMoreElements()) {
//				System.out.println(tokenizer.nextToken());
//			}
//			System.out.println("---");
//			

		String[] contact = row.split("\";\"");
		System.out.println(Arrays.toString(contact));
		}
		

		
		fileReader.close();
		bufferedReader.close();
		
		
		
	}
	
	

}
