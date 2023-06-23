package it.beije.xvii.exercises.mancuso;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


public class AddressBook {
	
	public List<Contact> contacts;

	private static List<String[]> readCSV(File file, String separator) throws IOException{
		FileReader fReader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(fReader);
		List<String[]> csv = new ArrayList<>();
		
		while(bReader.ready()) {
			String line = bReader.readLine();
			String[] values = line.split(separator, -1);
			csv.add(values);
		}
		
		bReader.close();
		fReader.close();
		
		return csv;
	}
	
	public List<Contact> loadAddressesFromCSV(String pathFile, String separator) throws Exception{
		
		File file = new File(pathFile);
		
		if(file.exists()) {
			if(!file.isDirectory()) {
				
				String filename = file.getName();

				
				if(filename.contains(".csv")) {
					
					List<Contact> newContacts = new ArrayList<Contact>();
					List<String[]> csv = readCSV(file, separator);
					
					Contact c = null;
					for(int i=1; i<csv.size(); i++) {
						c = new Contact();
						c.setFirstName(csv.get(i)[0]);
						c.setLastName(csv.get(i)[1]);
						c.setPhoneNumber(csv.get(i)[2]);
						c.setEmail(csv.get(i)[3]);
						c.setNotes(csv.get(i)[4]);
						
						newContacts.add(c);
					}
					return newContacts;
				}
				throw new Exception("Specified file is not a .CSV.");
			}
			throw new Exception("File path leads to a directory.");
		}	
		throw new Exception("File does not exist.");
		
	}
	
	public List<Contact> loadAddressesFromXML(String pathFile){
		return null;
	}
	
	public void writeAddressBook(String pathFile, String separator) {
		return;
	}
	
	public String toString() {
		String myString = "";
		for(Contact c : contacts) {
			myString += c.toString();
			myString += "\n";
		}
		return myString;
	}
	
	public static void main(String[] args) throws Exception {
		AddressBook addressBook = new AddressBook();
		List<Contact> newContacts = addressBook.loadAddressesFromCSV("/Temp/addressBook.csv", ";");
		addressBook.contacts = newContacts;
		
		System.out.println(addressBook.toString());
		
	}
	
	
}
