package it.beije.xvii.exercises.mancuso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
	
	public static List<String[]> readCSV(File file, String separator){
		FileReader fReader = null;
		List<String[]> csv = new ArrayList<>();
		BufferedReader bReader = null;
		try {
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			while(bReader.ready()) {
				String line = bReader.readLine();
				String[] values = line.split(separator, -1);
				csv.add(values);
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fReader.close();
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return csv;
	}
	
	public static void writeCSV(String pathFile, String separator, boolean append, String text) {
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(pathFile, append);
			
			writer.write(text);
			
		}catch (IOException e) {
			e.printStackTrace();
		} finally {			
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
