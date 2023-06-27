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

	public static void main(String[] args) throws Exception {
		

		File file = new File("/v/prova.txt");
		System.out.println("exists? " + file.exists());
		System.out.println("isDirectory? " + file.isDirectory());
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> rows = new ArrayList<String>();
		while (bufferedReader.ready()) {
			String r = bufferedReader.readLine();
			rows.add(r);
			//System.out.println(r);
		}
		
		
		System.out.println("rows number: " + rows.size());


		FileWriter fileWriter = new FileWriter("/temp/appoggio/prova.csv");

		for (String row : rows) {

			row = row.substring(1,row.length()-1);

			String[] contact = row.split("\";\"");
			System.out.println(Arrays.toString(contact));
	
			for (int j=4; j > 0; j--) {
				fileWriter.write(contact[j]);
				fileWriter.write('|');

			}



		fileWriter.close();

		System.out.println("FINE");

	}
}
}
