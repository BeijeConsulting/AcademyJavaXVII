package it.beije.xvii.exercises.ceccarelli.Esercizi_file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tool {
	
	/*
	 * Realizzare un tool che faccia la copia esatta di un file, sia esso di testo che di altro formato.
	Il programma dovrà ricevere in input il path del file da duplicare ed il path di destinazione della copia; 
	deve essere possibile fornire i 2 parametri sia a linea di comando (es: java Duplicator /temp/pippo.txt /temp/pippo2.txt) 
	sia da console tramite scanner.
	 */
	
	private File fileOrigin;
	private FileWriter fileCopy;

	public Tool(String file, String path) throws IOException {
		this.fileOrigin = new File(file);
		this.fileCopy = new FileWriter(path);
	};
	
	public void copy() throws IOException {
		FileReader fileReader = new FileReader(this.fileOrigin);
		BufferedReader br = new BufferedReader(fileReader);
		List<String> rows = new ArrayList<String>();
		while (br.ready()) {
			String r = br.readLine();
			rows.add(r);
		}
		
		br.close();
		fileReader.close();
		for(String row : rows) {
			fileCopy.write(row);
			fileCopy.write("\n");
		}
		fileCopy.flush(); // scarica sopra al file le modifiche.
		fileCopy.close();
	}

	public static void main(String[] args) throws IOException {
		//new File("/Users/Padawan/eclipse-workspace/File/banca.txt");
		System.out.println("inserisci path: ");
		Scanner scan = new Scanner(System.in);
		// path da scanner
		String file = scan.nextLine();
		String path = scan.nextLine();
		Tool tool = new Tool(file, path);
		
		
		//path da riga di comando
		//String fileR = args[0];
		//String pathR = args[1];
		//Tool tool = new Tool(fileR, pathR);
		tool.copy();
		System.out.println("FINE");
		
	}

}
