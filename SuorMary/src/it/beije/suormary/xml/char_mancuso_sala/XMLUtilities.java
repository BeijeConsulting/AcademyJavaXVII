package it.beije.suormary.xml.char_mancuso_sala;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
public class XMLUtilities { 

	public static Document readXML(String pathFile) {
		StringBuilder fileStr = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			 fileReader = new FileReader(pathFile);
			 bufferedReader = new BufferedReader(fileReader);
			 fileStr = new StringBuilder();
			while(bufferedReader.ready()) {
				fileStr.append(bufferedReader.readLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Document doc = new Document();
		doc.document = fileStr.toString();
		return doc;
	}
	
}
