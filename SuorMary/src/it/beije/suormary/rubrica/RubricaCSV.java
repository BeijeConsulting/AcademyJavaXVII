package it.beije.suormary.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class RubricaCSV {

	public static void main(String[] args) throws Exception {
		
		
		File file = new File("/Users/Padawan/eclipse-workspace/File/prova.txt");
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
		fileReader.close();
		
		System.out.println("rows number: " + rows.size());
		
		FileWriter fileWriter = new FileWriter("/Users/Padawan/eclipse-workspace/File/prova.csv");
		//fileWriter.write(str);
		
		for (String row : rows) {
//			StringTokenizer tokenizer = new StringTokenizer(row, ";");
//			while (tokenizer.hasMoreElements()) {
//				System.out.println(tokenizer.nextToken());
//			}
//			System.out.println("---");
			
			row = row.substring(1,row.length()-1);
			String[] contact = row.split("\";\"");
			System.out.println(Arrays.toString(contact));
			
			for (int y=4;y>0;y--) {
				fileWriter.write(contact[y]);
				fileWriter.write('\t');
			}
			fileWriter.write(contact[0]);
			fileWriter.write('\n');
		}
		fileWriter.flush(); // scarica sopra al file le modifiche.
		fileWriter.close();
		
		
		
		System.out.println("FINE");
	}
}
