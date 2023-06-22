package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;

/*
 * 
 * Gli agenti di borsa della banca Fraudolent compiono operazioni finanziare quotidianamente ed annotano le operazioni su un file, nel seguente formato: “ABC 50.0 210 B”, dove

    “ABC” è il nome dell’azione acquistata/venduta
    50.0 è l’importo della singola azione
    210 è la quantità
    ‘B’ è l’operazione, che può valere ‘B’ (Buy) o ‘S’ (Sell)

Si vuole quindi un programma che:

    legga il file inviato dagli agenti e lo restituisca come array (o List) di stringhe
    per ogni riga calcoli l’importo dell’operazione ed alla fine produca una semplice riga:
        “Op: (nn) Buy: (bb) Sell: (ss)”

    dove al posto di (nn) ho ul numero di operazioni lette, al posto di (bb) l’importo totale delle operazioni di acquisto ed in (ss) l’importo totale delle operazioni di vendita.
 * 
 * 
 * 
 * Complicazione (opzionale):

    alcuni agenti commettono errori nello scrivere il file, pertanto alcune righe potranno non rispondere allo standard (che è molto rigido!). Le righe “sbagliate” 
    non vanno considerate, ma vanno elencate alla fine del processo, dopo la riga di output del programma, in questo modo:
        Op: (nn) Buy: (bb) Sell: (ss)
        Err: (ee)
        (riga sbagliata 1)
        (riga sbagliata 2)
        (riga sbagliata 3)
 * 
 */

public class BancaFraudolent {

	public static void main(String[] args) throws IOException{
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Insert file name (with extension): ");
		String nomeFile = keyboard.nextLine(); //Fraudolent.txt
		keyboard.close();
		
		String path = "/Temp/" + nomeFile;
		File file = new File(path);
		
		if(file.exists()) {
			
			FileReader fReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fReader);
			
			List<String> rows = new ArrayList<>();
			List<String> badRows = new ArrayList<>();
			
			while(bufferedReader.ready()) {
				rows.add(bufferedReader.readLine());
			}
			double buy = 0;
			double sell = 0;
			for (String row : rows) {
				
				String[] splitRow = row.split(" ");
				double unitCost;
				int quantity;
				
				try {
					unitCost = Double.valueOf(splitRow[1]);
					quantity = Integer.valueOf(splitRow[2]);
				}catch(Exception ex) {
					badRows.add(row);
					continue;
				}

				if(splitRow[3].equals("B")) {
					buy += unitCost * quantity;
				}else {
					if(splitRow[3].equals("S")) {
						sell += unitCost * quantity;
					}else{
						badRows.add(row);
					}
				}
			}
			System.out.println("Op: (" + rows.size() + ") Buy: ("+ buy +") Sell: (" + sell + ")");
			System.out.println("Err: (" + badRows.size() +")");
			
			for(String row : badRows) {
				System.out.println(row);
			}
			
			bufferedReader.close();

		}else {
			System.out.println("The specified file does not exist.");
		}
	}

}
