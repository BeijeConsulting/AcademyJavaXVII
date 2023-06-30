package it.beije.suormary.xml.caroselli_iannetta_ulloa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserXML {
	//
	public static Document parserXML(String pathFile) {
		FileReader fileReader = null;
		Document document = new Document();
		try {
			File file = new File(pathFile);
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//<pippo>
			// = fileReader.read();
			StringBuilder r = new StringBuilder();
			//skip intestazione
			
			bufferedReader.readLine();
			
			while (bufferedReader.ready()) {
				r.append(bufferedReader.readLine() + "\n");
			}
			
			Node node = document.findNodes(r.toString());
			System.out.println(node.toString());
			
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			//bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		
		return null;
	}
	
	
	
	public static void main (String[] args) {
		
		ParserXML.parserXML("C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser1.xml");
	}

}
