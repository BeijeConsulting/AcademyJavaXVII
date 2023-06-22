package it.beije.suormary.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RubricaCSV {

	public static void main(String[] args) throws Exception {
		
		File file = new File("C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\prova.txt");
		System.out.println("exists? " + file.exists());
		System.out.println("isDirectory? " + file.isDirectory());
		
		FileReader fileReader = new FileReader(file);
//		char c;// = fileReader.read();
//		StringBuilder r = new StringBuilder(); 
//		while (fileReader.ready()) {
//			//System.out.print((char)c);
//			//c = fileReader.read();
//			c = (char) fileReader.read();
//			if (c != '\n') r.append(c);
//			else {
//				System.out.print(r);
//				r = new StringBuilder();
//			}
//		}
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> rows = new ArrayList<String>();
		while (bufferedReader.ready()) {
			String r = bufferedReader.readLine();
			rows.add(r);
			//System.out.println(r);
		}
		bufferedReader.close();
		
		System.out.println("rows number: " + rows.size());
		
		for (String row : rows) {
//			StringTokenizer tokenizer = new StringTokenizer(row, ";");
//			while (tokenizer.hasMoreElements()) {
//				System.out.println(tokenizer.nextToken());
//			}
//			System.out.println("---");
			
			String[] contact = row.split("\";\"");
			System.out.println(Arrays.toString(contact));

		}
		
		System.out.println("FINE");
	}
}
