package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * Realizzare un tool che faccia la copia esatta di un file, sia esso di testo che di altro formato.
Il programma dovrà ricevere in input il path del file da duplicare ed il path di destinazione della copia;
deve essere possibile fornire i 2 parametri sia a linea di comando (es: java Duplicator /temp/pippo.txt /temp/pippo2.txt) sia da console tramite scanner.
 */

public class CopyFile {

	public static void main(String[] args) throws Exception {
		
		String filePath;
		String destinationPath;
		
		if(args.length>0) {
			filePath = args[0];
			destinationPath = args[1];
		}else {
			Scanner keyboard = new Scanner(System.in);
			
			System.out.println("Insert file path: ");
			filePath = keyboard.nextLine();
			
			System.out.println("Insert destination path: ");
			destinationPath = keyboard.nextLine();
			
			keyboard.close();
		}
		
		File sourceFile = new File(filePath);
		
		if(sourceFile.exists()) {
			if(!sourceFile.isDirectory()) {
				
				FileReader fr = new FileReader(sourceFile);			
				BufferedReader br = new BufferedReader(fr);
				
				List<String> rows = new ArrayList<>();
				
				while(br.ready()) {
					rows.add(br.readLine());
				}
				
				br.close();
				fr.close();
				
				FileWriter fWriter = new FileWriter(destinationPath);
				
				for (String row : rows) {
					fWriter.write(row + "\n");
				}
				fWriter.flush();
				fWriter.close();
				
			}else {
				System.out.println("Source file is a directory.");
			}
		}else {
			System.out.println("Source file does not exist.");
		}
		
	}

}
