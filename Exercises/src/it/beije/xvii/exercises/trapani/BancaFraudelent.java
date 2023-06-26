package it.beije.xvii.exercises.trapani;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;



public class BancaFraudelent {
	
//	LETTURA FILE:
//	Formattazione file; ABC 50.0 210 B/S
//	ABC nome azione venduta/acquistata
//	50.0 importo singola azione
//	210 quantita'
//	B/S buy o sell
//	-------------------------------------
//	OUTPUT CONSOLE:
//	OP:## BUY: $$ SELL: $$
//	-------------------------------------
	
	
	
	

	public static void main(String[] args) throws Exception{
		
		float totB=0;
		float totS=0;
		
		File file = new File("\\Users\\marty\\Desktop\\Marti\\Beije\\Esercizi Academy\\File Banca.txt");
		//System.out.println("exists? " + file.exists());
		
		FileReader fileReader = new FileReader(file);
		BufferedReader read = new BufferedReader(fileReader);
		List<String> elenco = new ArrayList<String>();
		while(read.ready()) {										//NOTA xME: fare while(BUFFEREDREADER.READY()) equivale a !=EOF in c
			String s = read.readLine();
			elenco.add(s);
			//System.out.println(s);
		}
		int tot = elenco.size();
		
		for(String riga : elenco) {
			String[] operazione = riga.split(" ");	
			System.out.println(Arrays.toString(operazione));
				if(operazione[3].equals("S")) {
					float temp = Float.parseFloat(operazione[1]);
					totS+=temp;
				}
				if(operazione[3].equals("B")) {
					float temp = Float.parseFloat(operazione[1]);
					totB+=temp;
				}
			}
		
		
		
		System.out.println("OP: " + tot + "totB: " + totB +" totS: " + totS);
		
		}
				
		
	}
