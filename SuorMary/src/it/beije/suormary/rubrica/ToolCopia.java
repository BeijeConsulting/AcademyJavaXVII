package it.beije.suormary.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToolCopia {
	
	private File fileOrigin;
	private FileWriter fileCopy;

	public ToolCopia(String pathR, String pathW) throws IOException {
		this.fileOrigin = new File(pathR);
		this.fileCopy = new FileWriter(pathW);
	};
	public void copy() {
		
		FileReader fileReader = null;	
		
		BufferedReader bufferedReader = null;  	 
		List<String> rows = new ArrayList<String>();			//"salvo" file in arraylist di stringhe			
	
		try {															//controllo eccezioni
			fileReader = new FileReader(this.fileOrigin);
			bufferedReader = new BufferedReader(fileReader);
			while(bufferedReader.ready()) {								//leggo resto file e salvo in arraylist
				String r = bufferedReader.readLine();
				rows.add(r);
			}
			fileReader.close();
			bufferedReader.close();
			
			
			for (String row : rows) {
				fileCopy.write(row);
				fileCopy.write("\n");
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			try {
				fileCopy.flush();
				fileCopy.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}	

	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(System.in);
		String pathR, pathW;
		String pathCmdR, pathCmdW;
		
		
			System.out.println("Inserisci path file da copiare: ");
			pathR = in.nextLine();
			System.out.println("Inserisci path nuovo file: ");
			pathW = in.nextLine();

//			pathCmdR=args[0];
//			pathCmdW=args[1];

		
		
		ToolCopia tool = new ToolCopia(pathR,pathW);
//		ToolCopia tool1 = new ToolCopia(pathCmdR,pathCmdW);
		tool.copy();
		

	}

}
