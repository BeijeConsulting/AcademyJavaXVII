package it.beije.xvii.exercises.ulloa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MioRubricaCSV {

	public static void main(String[] args) throws Exception {
		
		//File file = new File("/temp/prova.txt");
		File file = new File("C:/Users/Padawan/git/file/rubrica.csv");
		//C:\Users\Padawan\git\file
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
		
		System.out.println("rows number: " + rows.size());
		
		for (String row : rows) {
//			StringTokenizer tokenizer = new StringTokenizer(row, ";");
//			while (tokenizer.hasMoreElements()) {
//				System.out.println(tokenizer.nextToken());
//			}
//			System.out.println("---"); "\";\""
			
			String[] contact = row.split(";");
			System.out.println(Arrays.toString(contact));
			System.out.println(contact.length);

		}
		bufferedReader.close();
		System.out.println("FINE");
	}
}
