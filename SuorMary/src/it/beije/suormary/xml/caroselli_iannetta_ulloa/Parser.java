package it.beije.suormary.xml.caroselli_iannetta_ulloa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

	public static Document parse(String pathFile) {
		
		 BufferedReader bufferedReader = null;
		 FileReader fileReader = null;
		 
	     Document document = null;
	     StringBuilder s = new StringBuilder();

	     try {
	    	 File file = new File(pathFile);
		     fileReader = new FileReader(file);
		     bufferedReader = new BufferedReader(fileReader);
		     
			 bufferedReader.readLine();
			 while (bufferedReader.ready()) s.append(bufferedReader.readLine());

		     document = new Document(s.toString());
		     
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	     return document;
	}
}
