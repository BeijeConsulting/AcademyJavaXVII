package it.beije.xvii.exercises.ceccarelli.Esercizi_file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Banca {
	/*
	 Gli agenti di borsa della banca Fraudolent compiono operazioni finanziare quotidianamente ed annotano le operazioni su un file, 
	 nel seguente formato: “ABC 50.0 210 B”, dove
	“ABC” è il nome dell’azione acquistata/venduta
	50.0 è l’importo della singola azione
	210 è la quantità
	‘B’ è l’operazione, che può valere ‘B’ (Buy) o ‘S’ (Sell)
	Si vuole quindi un programma che:
	
	legga il file inviato dagli agenti e lo restituisca come array (o List) di stringhe
	per ogni riga calcoli l’importo dell’operazione ed alla fine produca una semplice riga:
	“Op: (nn) Buy: (bb) Sell: (ss)”
	dove al posto di (nn) ho ul numero di operazioni lette, al posto di (bb) l’importo totale delle operazioni di acquisto ed in (ss) 
	l’importo totale delle operazioni di vendita.
	
	Complicazione (opzionale):
	
	alcuni agenti commettono errori nello scrivere il file, pertanto alcune righe potranno non rispondere allo standard (che è molto rigido!). 
	Le righe “sbagliate” non vanno considerate, ma vanno elencate alla fine del processo, dopo la riga di output del programma, in questo modo:
	Op: (nn) Buy: (bb) Sell: (ss)
	Err: (ee)
	(riga sbagliata 1)
	(riga sbagliata 2)
	(riga sbagliata 3)
	…
	 */

	public Banca() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception{
		int numOperazioni =0;
		double totaleAcquisto =0;
		double totaleVendita =0;
		int numErrors =0;
		// prendi file file
		File file = new File("/Users/Padawan/eclipse-workspace/File/banca.txt");
		if(file.exists()) {
			// leggi file
			FileReader fileReader = new FileReader(file);
			// creazione buffer
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// creazione lista 
			List<String> rows = new ArrayList<String>();
			while (bufferedReader.ready()) {
				//metti nella stringa una riga del file
				String r = bufferedReader.readLine();
				// aggiungila alla lista
				rows.add(r);
				
			}
			
			bufferedReader.close();
			fileReader.close();
			
			List<String> errors = new ArrayList<String>();
			
			for(String row:rows) {
				numOperazioni+=1;
				String[] op = row.split(" ");
				System.out.println(Arrays.toString(op));
				//System.out.println((op[3].getClass()));
				if(op[3].equals("B")) {
					
					totaleAcquisto += Float.parseFloat(op[1].trim());
				}else if(op[3].equals("S")){
					totaleVendita += Float.parseFloat(op[1].trim());
				}else {
					numErrors++;
					errors.add(row);
				}
			}
			
			System.out.println("Op: " + numOperazioni + " Buy: " + totaleAcquisto + " Sell: " + totaleVendita);
			System.out.println("Err: " + numErrors);
			if(numErrors!=0) {
				for(String r : errors) {
					System.out.println(r);
				}
			}
		}else {
			System.out.println("File non presente");
			return;
		}

	}

}
