package it.beije.xvii.exercises.ceccarelli.Esercizi_file.Rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


public class Rubrica_CSV {
	/*Implementate metodi analoghi a questi:
	 * public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) {...}
public List<Contatto> loadRubricaFromXML(String pathFile) {...}
public void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) {...}

Dopo i metodi base per la scrittura, fate in modo che se indicate un file xml o csv già esistente, 
i nuovi contatti non vadano a sovrascrivere quelli già presenti, bensì vengano aggiunti in coda.
	 */

	public List<Contatto> loadRubricaFromCSV(String pathFile, String separator){
		//System.out.println("exists? " + file.exists());
		File file = null;
		try {
			file = new File(pathFile);
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
			List<Contatto> contacts = new ArrayList<Contatto>();
			//leggi file
			FileReader fileReader=null;
			try {
				fileReader = new FileReader(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			
			// crea lista di righe
			List<String> rows = new ArrayList<String>();
			
			// aggiungi righe al buffer
			try {
				while (bufferedReader.ready()) {
					String r = bufferedReader.readLine();
					rows.add(r);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Contatto c = null;
			for (String row : rows) {
				String[] contact = row.split(separator);
				//System.out.println(Arrays.toString(contact));
					c = new Contatto();
					if(contact[0]!=null) {
						c.setCognome(contact[0].trim());
					} else {
						c.setCognome("\t");
					}
					if(contact[1]!= null) {
						c.setNome(contact[1].trim());
					}else {
						c.setNome("\t");
					}
					if(contact[2]!=null) {
						c.setTelefono(contact[2].trim());
					}else {
						c.setTelefono("\t");
					}
					if(contact[3]!=null) {
						c.setMail(contact[3].trim());
					}else {
						c.setMail("\t");
					}
					if(contact[4]!= null) {
						c.setNote(contact[4].trim());
					}else {
						c.setNote("\t");
					}
					
					contacts.add(c);
			}
			
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return contacts;
		
	}
	
	private static String formatRow(String  columns) {
        StringBuilder formattedRow = new StringBuilder();
        
            formattedRow.append(columns);
    
        formattedRow.deleteCharAt(formattedRow.length() - 1); // Rimuove l'ultimo separatore
        formattedRow.append(System.lineSeparator()); // Aggiunge un nuovo rigo alla fine della riga formattata
        return formattedRow.toString();
    }
	
	public static void main(String[] args)  {
		// ESERCIZIO CON FILE TXT
		String path = "/Users/Padawan/eclipse-workspace/File/rubrica.csv";
		String separator = ";";
		Rubrica_CSV txtRubrica = new Rubrica_CSV();
		List<Contatto> c = txtRubrica.loadRubricaFromCSV(path, separator);
		FileWriter fileWriter= null;
		try {
			fileWriter = new FileWriter("/Users/Padawan/eclipse-workspace/File/rubricaCsv.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Contatto cr : c) {
			//System.out.println(cr.toString());
			try {
				fileWriter.write(formatRow(cr.getNome()));
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write(formatRow(cr.getCognome()));
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write(formatRow(cr.getTelefono()));
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write(formatRow(cr.getMail()));
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write(formatRow(cr.getNote()));
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write('\t');
				fileWriter.write('\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		
		System.out.println("FINE");
		
		
		// ESERCIZIO CON FILE XML
		
		/*DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse("/Users/Padawan/eclipse-workspace/File/rubrica.xml");
		
		// prende tag "rubrica"
		Element docEl = document.getDocumentElement();
		System.out.println(docEl.getTagName());
		*/
	}
	}
}
