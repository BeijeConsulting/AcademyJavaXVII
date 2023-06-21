package it.beije.xvii.exercises.mancuso;

import java.io.File;
import java.io.FileReader;
import java.util.List;
//import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

public class RubricaCSV {

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Lara Dorren\\Documenti\\myFile.txt");
		//System.out.println("esiste?" + file.exists());
		
		FileReader fileReader = new FileReader(file);
		/*int c;
		while((c = fileReader.read()) > -1) {
			System.out.print((char)c);
		}*/
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> rows = new ArrayList<String>();
		while(bufferedReader.ready()) {
			rows.add(bufferedReader.readLine());
		}
		
		for (String row : rows) {
			/*StringTokenizer token = new StringTokenizer(row, ";");
			while(token.hasMoreElements()) {
				System.out.println(token.nextToken());
			}*/
			String[] contact = row.split(";");
			System.out.println(Arrays.toString(contact));
			System.out.println("---");
		}
		
		System.out.println("Rows number: " + rows.size());
		
		bufferedReader.close();
		
		System.out.println("FINE");
	}
}
