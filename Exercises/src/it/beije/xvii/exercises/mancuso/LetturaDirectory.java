package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

import java.io.FileWriter;

import java.io.File;

/*
 * Realizzare un programma che, ottenuto in input il percorso di una directory, ne elenchi il contenuto in un file di testo.
Se all'interno della directory sono presenti ulteriori directories, il programma dovrà elencare anche il contenuto di queste e delle eventuali directories interne, in modo ricorsivo.
Il file dovrebbe chiamarsi come il nome della directory ricevuta in input (estenione va benissimo .txt ma potete dicederla a piacere).
Le informazioni raccolte dovrebbero essere strutturate in questo modo dentro il file:

aaa.txt
bbb.zip
ccc.xls
csv (dir)
    f1.csv
    f2.csv
    f3.csv
    yyy (dir)
        pippo.doc
        pluto.doc
    zzz.txt
mydir (dir)
    ggg.txt
    hhh.txt
qui.css
quo.jpg
qua.png

Per maggiore chiarezza, potete prevedere di riportare non solo il nome del file, ma il suo path completo sul disco.
 * 
 * 
 */

public class LetturaDirectory {

	public static void scanDirectory(FileWriter fWriter, String path, int cycle) throws Exception{
		
		File file = new File(path);
		
		if(file.exists()) {
			if(file.isDirectory()) {
				
				System.out.println("Writing: " + path + " (dir)");
				
				for(int i=0; i<cycle; i++) {
					fWriter.append(' ');
				}
				
				fWriter.append(file.getName() + " (dir)");
				fWriter.append('\n');
				
				File[] directory = file.listFiles();
				
				for(File f : directory) {
					System.out.println("Scanning: " + f.getPath());
					
					
					scanDirectory(fWriter, f.getPath(), (cycle+1));
				}
				
			}else {
				System.out.println("Writing: " + path);
				
				for(int i=0; i<cycle; i++) {
					fWriter.append(' ');
				}
				fWriter.append(file.getName());
				fWriter.append('\n');
				
			}
		}else {
			throw new Exception("File does not exist.");
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		System.out.println("Insert directory path: ");
		Scanner keyboard = new Scanner(System.in);		
		String dirPath = keyboard.nextLine();
		keyboard.close();
		
		FileWriter fWriter = new FileWriter("/Temp/directories.txt");
		
		scanDirectory(fWriter, dirPath, 0);
		
		fWriter.flush();
		fWriter.close();
		 
	}

}
