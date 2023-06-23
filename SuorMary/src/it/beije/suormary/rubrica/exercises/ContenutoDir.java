package it.beije.suormary.rubrica.exercises;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Realizzare un programma che, ottenuto in input il percorso di una directory, 
 * ne elenchi il contenuto in un file di testo.
 * Se all'interno della directory sono presenti ulteriori directories, 
 * il programma dovrà elencare anche il contenuto di queste e delle eventuali 
 * directories interne, in modo ricorsivo.
 * Il file dovrebbe chiamarsi come il nome della directory ricevuta in input 
 * (estenione va benissimo .txt ma potete dicederla a piacere).
 * Le informazioni raccolte dovrebbero essere strutturate in questo modo dentro il file:
 * aaa.txt
 * bbb.zip
 * ccc.xls
 * csv (dir)
 *     f1.csv
 *     f2.csv
 *     f3.csv
 *     yyy (dir)
 *     		pippo.doc
 *     		pluto.doc
 *     zzz.txt
 * mydir (dir)
 * 		ggg.txt
 * 		hhh.txt
 * qui.css
 * quo.jpg
 * qua.png
 * 
 * Per maggiore chiarezza, potete prevedere di riportare non solo il nome del file, 
 * ma il suo path completo sul disco.
 */

public class ContenutoDir {
	private File directory;
	private File[] files;
	
	public ContenutoDir(String pathname) {
		this.directory = new File(pathname);
		this.files = directory.listFiles();
	}
	
	public File getDirectory() {
		return directory;
	}

	public File[] getFiles() {
		return files;
	}
	
	private String getIndentation(int level) {
	    StringBuilder indentation = new StringBuilder();
	    for (int i = 0; i < level; i++) {
	        indentation.append("\t"); // Utilizza una tabulazione per ogni livello di indentazione
	    }
	    return indentation.toString();
	}
	
	private void listDirectoryContents(File[] files, FileWriter fileWriter, int level) throws IOException{
		if(files != null) {
			for (File file : files) {
				if(file.isFile()) {
					System.out.println("file " + file.getName());
					// Scrivi il nome del file con l'indentazione appropriata
	                fileWriter.write(getIndentation(level));
	                fileWriter.write(file.getName());
					fileWriter.write("\n");
					
				} else if (file.isDirectory()) {
					System.out.println("Directory " + file.getName());
					// Scrivi il nome della directory con l'indentazione appropriata
	                fileWriter.write(getIndentation(level));
					fileWriter.write(file.getName());
					fileWriter.write("(dir)\n");
					// Richiama ricorsivamente il metodo per la directory interna con un livello superiore di indentazione
					listDirectoryContents (file.listFiles(),fileWriter, level+1);
				}
				fileWriter.flush();
			}
		}
	}
	
	public void writeContentToFile() {
        try {
        	StringBuilder outputPath = new StringBuilder("C:/Users/Padawan/git/file/");
        	outputPath.append(directory.getName()).append(".txt");
            FileWriter writer = new FileWriter(outputPath.toString());
            listDirectoryContents(files, writer,0); // Chiamata iniziale con livello di indentazione 0;
            writer.close();
            System.out.println("Scrittura del file completata: " + directory.getName() + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

	public static void main(String[] args) {
		ContenutoDir cd = new ContenutoDir("/Users/Padawan/OneDrive - Università degli Studi di Milano-Bicocca/Academy");
		cd.writeContentToFile();
		System.out.println(cd.directory.getPath()+".txt");
	}

	


}
