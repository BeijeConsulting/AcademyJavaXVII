package it.beije.suormary.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RubricaCSV {

	public static void main(String[] args) {
		

		File file = new File("C:\\Users\\marty\\Desktop\\Marti\\Beije\\Esercizi Academy\\prova.txt");
		System.out.println("exists? " + file.exists());
		System.out.println("isDirectory? " + file.isDirectory());
		
//		FileReader fileReader = new FileReader(file);
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

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		List<String> rows = null;
		try {
			File file = new File("/temp/prova.txt");
			System.out.println("exists? " + file.exists());
			System.out.println("isDirectory? " + file.isDirectory());
			
			fileReader = new FileReader(file);
//			char c;// = fileReader.read();
//			StringBuilder r = new StringBuilder(); 
//			while (fileReader.ready()) {
//				//System.out.print((char)c);
//				//c = fileReader.read();
//				c = (char) fileReader.read();
//				if (c != '\n') r.append(c);
//				else {
//					System.out.print(r);
//					r = new StringBuilder();
//				}

//			}

//		}
		
//		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> rows = new ArrayList<String>();
		while (bufferedReader.ready()) {
			String r = bufferedReader.readLine();
			rows.add(r);
//			System.out.println(r);

			
			bufferedReader = new BufferedReader(fileReader);
			rows = new ArrayList<String>();
			while (bufferedReader.ready()) {
				String r = bufferedReader.readLine();
				rows.add(r);
				//System.out.println(r);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		
		System.out.println("rows number: " + rows.size());
		

		
		FileWriter fileWriter = new FileWriter("/temp/appoggio/prova.csv");
	
		for (String row : rows) {
			StringTokenizer tokenizer = new StringTokenizer(row, ";");
			while (tokenizer.hasMoreElements()) {
				System.out.println(tokenizer.nextToken());
			}
			System.out.println("---");
     		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("/temp/appoggio/prova.csv");

			
//			for (String row : rows) {
//				StringTokenizer tokenizer = new StringTokenizer(row, ";");
//				while (tokenizer.hasMoreElements()) {
//					System.out.println(tokenizer.nextToken());
//				}
//				System.out.println("---");
				
				row = row.substring(1,row.length()-1);
				String[] contact = row.split("\";\"");
				System.out.println(Arrays.toString(contact));
				
				for (int j=4; j > 0; j--) {
					fileWriter.write(contact[j]);
					fileWriter.write('|');
				}
				fileWriter.write(contact[0]);
				fileWriter.write('\n');
				fileWriter.flush();
			}
		 catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("FINE");
	}
}


}
}