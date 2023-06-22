package it.beije.xvii.exercises.Char;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedReader;
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
		for(String a : arrayFiles) {
			File newfile = new File(percorso + "/" + a);
			if(newfile.isDirectory()) {
			 fileWriter.write(a + " (dir)" + "\n");
				String[] arr = newfile.list();
				for(String b : arr) {
					checkDirectory(newfile,b,percorso, fileWriter);
				}
			}
			else fileWriter.write(a + "\n");
		}
		fileWriter.close();

       
	}
	public static void checkDirectory(File pt,String a, String percorso, FileWriter fileWriter) throws Exception {
		File newfile = new File(pt.getAbsolutePath() + "/"+ a);
		if(newfile.isDirectory()) {
			fileWriter.write(a + " (dir)" + "\n");
			String[] arr = newfile.list();
			for(String b : arr) {
				checkDirectory(newfile,b,percorso, fileWriter);
			}
		}
		else fileWriter.write(a + "\n");
	}
	
}
