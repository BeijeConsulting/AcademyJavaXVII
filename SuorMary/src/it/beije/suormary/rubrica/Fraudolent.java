package it.beije.suormary.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Fraudolent {

	public static boolean isValid(String s) throws Exception{
		String[] row = s.split(" ");
		if (row.length != 4) return false; //not enough fields 
		row[0] = row[0].replaceAll("[^A-Z]", ""); //name in lower case or not 
		if (row[0].length() != 3) return false; //name too long or too short
		double d;
		int n;
		try {
			d = Double.parseDouble(row[1]); //no number
			n = Integer.parseInt(row[2]); //no number
		} catch(Exception e) {
			return false;
		}
		if (d < 0 || n < 0) return false; //negative number
		if (!row[3].equals("B") && !row[3].equals("S")) return false;
		return true;
		}
		
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("C:\\Users\\Chiara\\git\\AcademyJavaXVII\\SuorMary\\src\\it\\beije\\suormary\\rubrica\\bankprocedures.txt");
		FileReader fileReader = new FileReader(file);
		
		List<String[]> validRows = new ArrayList<>();
		List<String> invalidRows = new ArrayList<>();
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.ready()) {
			String row = bufferedReader.readLine().replace("\"", "");
			if (isValid(row)) validRows.add(row.split(" "));
			else invalidRows.add(row);
			
		}
		bufferedReader.close();
		
		for (int i = 0; i < validRows.size(); i++) {
			String[] row = validRows.get(i);
			Double bb = 0.0;
			Double ss = 0.0;
			int multiplier = Integer.parseInt(row[2]);
 			if (row[3].equals("B")) {
				bb = Double.parseDouble(row[1])  * multiplier;
				ss = 0.0;
			}
			else if (row[3].equals("S")) {
				bb = 0.0;
				ss = Double.parseDouble(row[1])  * multiplier;
			}
			System.out.println("Op: " + i + "\tBuy: " + bb + "\tSell: " + ss);	
		}
		System.out.println("Err: " + invalidRows.size());
		
		for(String row : invalidRows) System.out.println(row);
	}

}
