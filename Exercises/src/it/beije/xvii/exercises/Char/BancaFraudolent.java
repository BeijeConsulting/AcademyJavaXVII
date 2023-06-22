package it.beije.xvii.exercises.Char;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BancaFraudolent {

	public static void main(String[] args) throws Exception {
		File file = new File("/v/prova2.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> rows = new ArrayList<String>();
		while (bufferedReader.ready()) {
			String r = bufferedReader.readLine();
			rows.add(r);
			//System.out.println(r);
		}
		
		int nn = 0;
		double bb = 0;
		double ss = 0;
		List<String> righeErrate = new ArrayList<String>();
		for (String row : rows) {

			String[] contact = row.split(" ");
		
				String op = contact[contact.length - 1].substring(0,1);
			
				if(op.equals("B")) {
					int numBuy = Integer.parseInt(contact[contact.length - 2]) ;
					double azione  = Double.parseDouble(contact[contact.length - 3]);
					bb += numBuy * azione;
					nn++;
				}
					
				else if(op.equals("S")) {
					int numSell = Integer.parseInt(contact[contact.length - 2]) ;
					double azione  = Double.parseDouble(contact[contact.length - 3]);
					ss += numSell * azione;
					nn++;
				}
				else righeErrate.add(row);
				
			System.out.println(Arrays.toString(contact));

		}
		System.out.println("Op : " + nn + " Buy " + bb + " Sell " + ss);
		System.out.println("Righe errate : ");
		for(String row : righeErrate) {
			System.out.println(row);
		}

	}

}
