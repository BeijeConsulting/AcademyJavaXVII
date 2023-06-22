package it.beije.suormary.rubrica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duplicator {

	
//	private static Path newFilePath(Path newFilePath) throws IOException {
//		Scanner in = new Scanner(System.in);
//		System.out.println("New file path already exists.\nDo you want to replace it? Y / N");
//		String answer = in.nextLine();
//		in.close();
//		
//		if (answer.equals("Y")) {
//			System.out.println("Ok, file will be replaced");
//	        Files.delete(newFilePath);
//			return newFilePath;
//		}
//		else if (answer .equals("N")) System.out.println("Ok, file not replaced");
//		return Paths.get(newFilePath.toString() + "newcopy");
//	}
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner (System.in);
		Path oldFilePath = Paths.get("C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\vecchiofile.xlsx");
		Path newFilePath = Paths.get("C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\nuovofile.xlsx");
		
		
//		Path oldFilePath;
//		Path newFilePath;
//		if (args.length == 0) {
//			System.out.println("Enter path of old file: ");
//			oldFilePath = Paths.get(in.nextLine());
//			System.out.println("Enter path of new file: ");
//			newFilePath = Paths.get(in.nextLine());
//		}
//		else {
//			oldFilePath = Paths.get(args[0]);
//			oldFilePath = Paths.get(args[1]);
//			System.out.println("Old file path: " + oldFilePath);
//			System.out.println("New file path: " + newFilePath);
		
		
		if (!(new File(oldFilePath.toString())).exists()) System.out.println("Old file path does not exist");
		else {
			if ((new File(newFilePath.toString())).exists()) {
				System.out.println("New file path already exists.\nDo you want to replace it? Y / N");
				String answer = in.nextLine();
				
				if (answer.equals("Y")) {
					System.out.println("Ok, file will be replaced");
			        Files.delete(newFilePath);
				}
				else if (answer .equals("N")) {
					System.out.println("Ok, file not replaced");
					String temp = newFilePath.toString();
					int index = temp.indexOf(".");
					newFilePath = Paths.get(temp.substring(0, index) + "newcopy" + temp.substring(index));
				}
				else newFilePath = null;
			}
			Files.copy(oldFilePath, newFilePath);	
		}
		in.close();
	}

}
