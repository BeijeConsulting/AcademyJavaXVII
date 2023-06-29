package it.beije.suormary.exercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Gli agenti di borsa della banca Fraudolent compiono operazioni finanziare 
 * quotidianamente ed annotano le operazioni su un file, nel seguente formato: 
 * “ABC 50.0 210 B”, dove
 * 		“ABC” è il nome dell’azione acquistata/venduta
 * 		50.0 è l’importo della singola azione
 * 		210 è la quantità
 * 		‘B’ è l’operazione, che può valere ‘B’ (Buy) o ‘S’ (Sell)
 * Si vuole quindi un programma che:
 * 		-legga il file inviato dagli agenti e lo restituisca come array (o List) 
 * 		 di stringhe
 * 		-per ogni riga calcoli l’importo dell’operazione ed alla fine produca 
 * 		 una semplice riga:
 * 			“Op: (nn) Buy: (bb) Sell: (ss)”
 * 		 dove al posto di (nn) ho ul numero di operazioni lette, 
 * 		 al posto di (bb) l’importo totale delle operazioni di acquisto ed 
 * 		 in (ss) l’importo totale delle operazioni di vendita.
 */

public class BancaFraudolent {

	public static void main(String[] args) throws Exception {
		final String path_file = "/Users/Padawan/git/file/op_finanziare_banca_Fraudolent.txt";
		File file = new File(path_file);
		//FileReader fileReader;
		
		if (!file.exists()) {
			System.out.println("File non trovato!");
		}
		else {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> rows = new ArrayList<String>();
			while (bufferedReader.ready()) {
				String r = bufferedReader.readLine();
				rows.add(r);
			}
			//System.out.println("rows number: " + rows.size());
			
			int nn = 0; //numero di operazioni lette
			double bb = 0.0; //l’importo totale delle operazioni di acquisto
			double ss = 0.0; //l’importo totale delle operazioni di vendita
			for (String row : rows) {				
				String[] operazioni = row.split(" ");
				double importoTot = Double.parseDouble(operazioni[1]) * Integer.parseInt(operazioni[2]);
			
				if (operazioni[3].equals("B")) {
					bb = bb + importoTot;
					nn++;
				} else if (operazioni[3].equals("S")) {
					ss = ss + importoTot;
					nn++;
				}

			}
			System.out.println("Op: " + nn + " Buy: " + bb + " Sell: "+ ss);
			bufferedReader.close();
		}
	}

}
