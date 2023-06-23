package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.List;
//import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;


/*
 * Realizzare un tool che faccia la copia esatta di un file, sia esso di testo che di altro formato.
Il programma dovrà ricevere in input il path del file da duplicare ed il path di destinazione della copia;
deve essere possibile fornire i 2 parametri sia a linea di comando (es: java Duplicator /temp/pippo.txt /temp/pippo2.txt) sia da console tramite scanner.
 */

public class CopyFile {
	
	public static void copyFile(String filePath, String destinationPath) throws IOException{
		Path sourcePath = Paths.get(filePath);
		Path destPath = Paths.get(destinationPath);
			
		Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static void main(String[] args) throws Exception {
		
		String filePath;
		String destinationPath;
		Scanner keyboard = new Scanner(System.in);
		if(args.length>0) {
			filePath = args[0];
			destinationPath = args[1];
		}else {
			
			
			System.out.println("Insert file path: ");
			filePath = keyboard.nextLine();
			
			System.out.println("Insert destination path: ");
			destinationPath = keyboard.nextLine();
			
			
		}
		
		File sourceFile = new File(filePath);
		
		if(sourceFile.exists()) {
			if(!sourceFile.isDirectory()) {
				
				File destFile = new File(destinationPath);
				boolean copy = false;
				if(destFile.exists()) {
					
					String answer = "abc";
					String values = "yn";
					while(!values.contains(answer)) {
						System.out.println("Destination file already exists, do you want to overwrite it? (Y/n)");
						answer = keyboard.nextLine();
						answer = answer.toLowerCase();
					}
					keyboard.close();
					if(answer.equals("n")) {
						System.out.println("Programma terminato.");
					}else {
						copy = true;				
					}
				}else {
					copy=true;
				}
				
				if(copy) {
					try {
						copyFile(filePath,destinationPath);
					}catch(IOException ex){
						ex.getStackTrace();
					}
				}
				/*FileReader fr = new FileReader(sourceFile);			
				BufferedReader br = new BufferedReader(fr);
				
				List<String> rows = new ArrayList<>();
				
				while(br.ready()) {
					rows.add(br.readLine());
				}
				
				br.close();
				fr.close();
				
				try {
					FileWriter fWriter = new FileWriter(destinationPath);
					
					for (String row : rows) {
						fWriter.write(row + "\n");
					}
					fWriter.flush();
					fWriter.close();
				}catch(Exception e) {
					System.out.println("Could not write in the destination file path.");
				}*/
				
			}else {
				System.out.println("Source file is a directory.");
			}
		}else {
			System.out.println("Source file does not exist.");
		}
		keyboard.close();
	}

}
