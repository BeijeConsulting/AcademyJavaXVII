package it.beije.xvii.exercises.Char;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BancaFraudolent {

	public static void main(String[] args) throws Exception {
		File file = new File("/v/banca.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> rows = new ArrayList<String>();
		while (bufferedReader.ready()) {
			String r = bufferedReader.readLine();
			rows.add(r);
		}
		
		int nn = 0;
		double bb = 0;
		double ss = 0;
		List<String> righeErrate = new ArrayList<String>();
		for (String row : rows) {
             String newRow = row.substring(1,row.length()-1);

			String[] contact = newRow.split(" ");
		
				if(contact[contact.length - 1].equals("B")) {
					int numBuy = Integer.parseInt(contact[contact.length - 2]) ;
					double azione  = Double.parseDouble(contact[contact.length - 3]);
					bb += numBuy * azione;
					nn++;
				}
					
				else if(contact[contact.length - 1].equals("S")) {
					int numSell = Integer.parseInt(contact[contact.length - 2]) ;
					double azione  = Double.parseDouble(contact[contact.length - 3]);
					ss += numSell * azione;
					nn++;
				}
				else righeErrate.add(row);
				
			System.out.println(Arrays.toString(contact));

		}
		System.out.println("Op : " + nn + " Buy " + bb + " Sell " + ss);
		if(!(righeErrate.isEmpty())){
			System.out.println("Righe errate : ");
			for(String row : righeErrate) {
				System.out.println(row);
			}
		}

	}

}
