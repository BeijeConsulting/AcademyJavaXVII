package it.beije.xvii.exercises.Char;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
public class EsRubrica {

	public static void main(String[] args) throws Exception {
//		loadRubricaFromCSV("/v/rubricacsv.txt",";");

	}
	public static List<Contact> loadRubricaFromCSV(String pathFile, String separator) throws Exception {
		File file = new File(pathFile);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<Contact> contacts = new ArrayList<>();
		Contact con = null;
		List<String> arr = new ArrayList<>();
		while(bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			arr.add(line);
		}
	
		for(String str : arr) {
		    String[] cont = str.split(separator);
		    con = new Contact();
		    con.setSurname(cont[0]);
		    con.setName(cont[1]);
		    con.setPhoneNumber(cont[2]);
		    con.setEmail(cont[3]);
		    con.setNote(cont[4]);
		    contacts.add(con);
		}
		return contacts;
	}
	
}
