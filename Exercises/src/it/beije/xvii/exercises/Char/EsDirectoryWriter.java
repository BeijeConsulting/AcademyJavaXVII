package it.beije.xvii.exercises.Char;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
public class EsDirectoryWriter  {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci il percorso");
		String percorso = scanner.nextLine();
		File file = new File(percorso);
		scanner.close();
		String[] arrayFiles = file.list();
		 FileWriter fileWriter = new FileWriter("/v/" + file.getName() + ".txt");
		for(String name : arrayFiles) {
			File newfile = new File(percorso + "/" + name);
			if(newfile.isDirectory()) {
			 fileWriter.write(name + " (dir)" + "\n");
				String[] arr = newfile.list();
				for(String fileName : arr) {
					checkDirectory(newfile,fileName, fileWriter);
				}
			}
			else fileWriter.write(name + "\n");
		}
		fileWriter.close();
     
	}
	public static void checkDirectory(File path,String fileName,  FileWriter fileWriter) throws Exception {
		File newfile = new File(path.getAbsolutePath() + "/"+ fileName);
		if(newfile.isDirectory()) {
			fileWriter.write(fileName + " (dir)" + "\n");
			String[] arr = newfile.list();
			for(String b : arr) {
				checkDirectory(newfile,b, fileWriter);
			}
		}
		else fileWriter.write(" " + fileName + "\n");
	}
	
}
