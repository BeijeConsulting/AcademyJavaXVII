package esercizi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public class RubricaUtils {

	public static void main(String[] args) throws Exception{
	
		
		String pathFile = "/Users/Padawan/Desktop/fileAcademy/rubrica.csv";
		
		String separator = ";";
		RubricaUtils ru = new RubricaUtils();
		
		ru.loadRubricaFromCSV(pathFile, separator);
		

	}
	
	public List<Contact> loadRubricaFromCSV(String pathFile, String separator) throws Exception {
		File file = new File(pathFile);
		System.out.println("exists? " + file.exists());
		
		FileReader fr = new FileReader(file);
		
		BufferedReader br = new BufferedReader(fr);
		List<Contact> contatti = new ArrayList<Contact>();
		Contact c = null;
		String riga = null;
		//array dove si salvano i campi che si generano ad ogni linea dopo averli separati all'interno della linea 
		//stessa
		String [] campi =null;
		//lo stringbuilder è meglio inizializzarlo e poi svuotarlo con apposito metodo dentro al ciclo in cui lo si 
		//utilizza perchè è un oggetto di appoggio e quindi sarebbe uno spreco di risorse inizializzarlo da zero ogni
		//volta (non ha altre utilità come invece un oggetto ContactCSV deve avere, in quanto ho degli oggetti indipendenti)
		StringBuilder sb = new StringBuilder();
		
		//serve per salatare la prima linea del file cvs, in quanto contiene l'intestazione con i nomi dei campi e non
		//i valori dei contatti
		br.readLine();
		
		while (br.ready()) {
			riga = br.readLine();
			
			//non serve farlo ora perchè toglie spazi solo all'inizio e alla fine 
			//riga = riga.trim();
			campi=riga.split(separator);
			
			//controllo che dopo lo split l'array sia della
			//dimensione corretta, ovvero pari al numero di
			//campi che devono esserci (5 nel nostro caso)
			if(campi.length==5) {
				//in base al riferimento dell'oggetto che ho creato fuori dal while, vado a costruire di volta in volta
				//l'oggetto corrente
				c=new Contact();
				
				//in questo modo a ogni iterazione svuoto lo sb precendente
				//viene utilizzato come operazione di pulizia
				sb.setLength(0);
				//funzione per inserire valori all'interno dello stringbuilder
				sb.append(campi[0].trim());
				//ora setto il valore di c passandogli lo stringbuilder convertito in stringa tramire toString()
				c.setCognome(sb.toString());
				
				//di nuovo azzero lo stringbuilder, lo faccio prima di scriverci ogni nuova volta
				sb.setLength(0);
				sb.append(campi[1].trim());
				c.setNome(sb.toString());
				
				sb.setLength(0);
				sb.append(campi[2].trim());
				c.setTelefono(sb.toString());
				
				sb.setLength(0);
				sb.append(campi[3].trim());
				c.setEmail(sb.toString());
				
				sb.setLength(0);
				sb.append(campi[4].trim());
				c.setNote(sb.toString());
				
				//VERSIONE CON STRING
				/*c.setCognome(campi[0].trim());
				c.setNome(campi[1].trim());
				c.setTelefono(campi[2].trim());
				c.setEmail(campi[3].trim());
				c.setNote(campi[4].trim());
				*/
				
				//DEVO AGGIUNGERE l'oggetto dopo averlo settato per poterlo avere nella mia lista che cotiene contatti
				contatti.add(c);
			} else {
				//è buona norma tenere una traccia di tutte le linee che sono sbagliate
				System.out.println("La seguente linea non è corretta: "+campi);
			}
			
			for(Contact c1 : contatti) {
				if(c1!=null) {
					if(c1.getCognome()==null) {
						System.out.println("cognome mancante!");
					} else {
						System.out.println(c1.getCognome());
					}
					
					if(c1.getNome()==null) {
						System.out.println("nome mancante!");
					} else {
						System.out.println(c1.getNome());
					}
					
					if(c1.getTelefono()==null) {
						System.out.println("telefono mancante!");
					} else {
						System.out.println(c1.getTelefono());
					}
					
					if(c1.getEmail()==null) {
						System.out.println("email mancante!");
					} else {
						System.out.println(c1.getEmail());
					}
					if(c1.getNote()==null) {
						System.out.println("nota mancante");
					} else {
						System.out.println(c1.getNote());
					}
					
					System.out.println("*********");
				}
				
			}
			
		}
		
		//prima si chiude il bufferreader
		br.close();
		//poi chiudo il filereader
		fr.close();
		return contatti;
		
	}
	//public List<Contatto> loadRubricaFromXML(String pathFile) {...}
	
	public void writeRubricaCSV(List<Contact> contatti, String pathFile, String separator) {
		
	}
}
