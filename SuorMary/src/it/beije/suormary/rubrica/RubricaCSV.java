package it.beije.suormary.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class RubricaCSV {

	public static void main(String[] args) throws Exception {
		
		File file = new File("/v/prova2.txt");
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
		
		for (String row : rows) {

			String[] contact = row.split("\";\"");
			System.out.println(Arrays.toString(contact));

		}
		
		System.out.println("FINE");
	}
}
