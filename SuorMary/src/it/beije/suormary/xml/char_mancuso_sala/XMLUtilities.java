package it.beije.suormary.xml.char_mancuso_sala;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
public class XMLUtilities {
	public static void main(String[] args) {
		readXML("/v/test_parser1.xml");
	}
	
	public static Document readXML(String pathFile) {
		StringBuilder fileStr = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			File file = new File(pathFile);
			 fileReader = new FileReader(pathFile);
			 bufferedReader = new BufferedReader(fileReader);
			 fileStr = new StringBuilder();
			while(bufferedReader.ready()) {
				fileStr.append(bufferedReader.readLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println(fileStr);
		Document doc = new Document();
		doc.document = fileStr.toString();
		return doc;
	}
	
}
