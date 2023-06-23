package it.beije.suormary.rubrica.exercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RubricaUtils {
	public List<Contact> loadRubricaFromCSV(String pathFile, String separator) throws Exception {
		File file = new File(pathFile);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<Contact> contatti = new ArrayList<Contact>();
		Contact contatto = null;
		String line = null;
		String[] fields = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		// Ignora la prima riga (intestazione)
        bufferedReader.readLine();
        
		while (bufferedReader.ready()) {
			line = bufferedReader.readLine();
			fields = line.split(separator);
			if (fields.length == 5) {
                contatto = new Contact();
                
                stringBuilder.setLength(0);
                stringBuilder.append(fields[0].trim());
                contatto.setSurname(stringBuilder.toString());
                
                stringBuilder.setLength(0);
                stringBuilder.append(fields[1].trim());
                contatto.setName(stringBuilder.toString());
                
                stringBuilder.setLength(0);
                stringBuilder.append(fields[2].trim());
                contatto.setPhoneNumber(stringBuilder.toString());
                
                stringBuilder.setLength(0);
                stringBuilder.append(fields[3].trim());
                contatto.setEmail(stringBuilder.toString());
                
                stringBuilder.setLength(0);
                stringBuilder.append(fields[4].trim());
                contatto.setNote(stringBuilder.toString());
                
                contatti.add(contatto);
            } else {
                System.out.println("Riga non valida: " + line);
            }
			//System.out.println(r);
		}
		
		bufferedReader.close();
		fileReader.close();
		System.out.println("rows number: " + contatti.size());
		return contatti;
	}
	//public List<Contact> loadRubricaFromXML(String pathFile) {...}
	//public void writeRubricaCSV(List<Contact> contatti, String pathFile, String separator) {...}

}
