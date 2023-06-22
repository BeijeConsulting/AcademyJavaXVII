package it.beije.suormary.rubrica;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ListContents {

	public static ArrayList<String> listContentOf(String pathway, String... indentation){
		
		if (indentation.length == 0) indentation = new String[] {""};
		else indentation[0] += "\t";
		
		File file = new File(pathway);
		ArrayList<String> content = new ArrayList<>();

		if (!file.isDirectory()) {
			content.add(indentation[0] + file.getName());
		}
		else {
			content.add(indentation[0] + file.getName() + " (dir)");
			
			File[] fileEntries = file.listFiles();
			Arrays.sort(fileEntries);
			
			for (File fileEntry : fileEntries) {
				
				String fileEntryPathway = pathway + "\\\\" + fileEntry.getName();
				ArrayList<String> fileEntryContent = new ArrayList<>();
				
				fileEntryContent = listContentOf(fileEntryPathway, indentation[0]);
				content.addAll(fileEntryContent);
				
			}
		}
		return content;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Enter pathway: ");
		String pathway = "C:\\Users\\Chiara\\Desktop\\Università\\ANALISIIIIIIIIIIIII";
//		Scanner in = new Scanner(System.in);
//		String pathway = in.nextLine();
//		in.close();
		
		ArrayList<String> content = listContentOf(pathway);
		for (String s : content) {
			System.out.println(s);
		}

	}

}
