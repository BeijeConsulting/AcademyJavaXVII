package it.beije.xvii.exercises.ceccarelli.Esercizi_file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ContenutoFile {
	
	public FileWriter fileWriter;
	public File directory;
	
	public ContenutoFile() throws Exception {
		this.fileWriter = new FileWriter("/Users/Padawan/eclipse-workspace/File/elencoContenuto4.csv");
		this.directory = new File("/Users/Padawan/Desktop/");
	};
	
	public FileWriter leggiFile(File f) throws Exception {
				if(f.isFile()) {
					this.fileWriter.write( "\t");
					this.fileWriter.write(f.getName() + "\n");
				}else if (f.isDirectory()) {
					this.fileWriter.write(f.getName() + "(dir)" + "\n");
					File[] files = f.listFiles();
					if(files!=null) {
						
					for(File file2: files) {
						  this.leggiFile(file2);
					}
					}
				         
				}
				return this.fileWriter;
			}
		
		
	
	
	public void close() throws Exception{
		fileWriter.flush();
		this.fileWriter.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		
		ContenutoFile cf = new ContenutoFile();
		//File directory = new File("/Users/Padawan/Desktop/");
		System.out.println("exists? " + cf.directory.exists());
		System.out.println("isDirectory? " + cf.directory.isDirectory());
		
		
		cf.leggiFile(cf.directory);
			
		
		cf.close();
    }
		

		
}
