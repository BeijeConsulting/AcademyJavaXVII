package it.beije.xvii.exercises.Char;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
public class EsCopiaFile {

	public static void main(String[] args) throws Exception {
		System.out.println("Inserisci il percorso del file da copiare e il percorso del file nuovo");
		Scanner scanner = new Scanner(System.in);
		String files = scanner.nextLine();
		scanner.close();
		String[] paths = files.split(" ");
		File oldFile;
		FileReader fileReader;
		FileWriter newFileWriter;
		if(files.isBlank()) {
			 oldFile = new File(args[0]);
			 fileReader = new FileReader(oldFile);
			 newFileWriter = new FileWriter(args[1]);
		}
		else {
			 oldFile = new File(paths[0]);
			 fileReader = new FileReader(oldFile);
		 newFileWriter = new FileWriter(paths[1]);
		}
		
	
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while(bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			newFileWriter.write(line + "\n");
		}
		newFileWriter.close();
		

	}

}
