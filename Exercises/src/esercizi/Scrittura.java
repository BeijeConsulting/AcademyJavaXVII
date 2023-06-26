package esercizi;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
public class Scrittura {
	
	
	

	public static void main(String[] args) throws Exception{
		
		//da Scanner inserisco percorso file
		//controllo che sia una directory
		//prendo il nome della directory dal percorso e lo uso per nominare un file
		//all'interno di questo file salvo i nomi dei file già presenti nella directory
		System.out.println("inserire nome directory");
		Scanner ts = new Scanner(System.in);
		String nomeDir = ts.next();
		File directory = new File(nomeDir);
		System.out.println(directory.isDirectory());
		
		String nome = directory.getName();
		String percorso = directory.getParent();
		String percorsoFinale =percorso+"\\"+nome+".txt";
		
		/*
		//salva da lì fino alla fine e quindi solo la parola dopo gli ultimi \\
		String nuova = nomeDir.substring(nomeDir.lastIndexOf("\\") + 1);
		//salva dall'inizio fino agli ultimi \\ compresi
		String n = nomeDir.substring(0, nomeDir.lastIndexOf("\\")+1);
		System.out.println(n);
		*/
		
		
		//String path = nomeDir+ File.separator + nuova+".txt";
		//String path = n+nuova+".txt";
		//System.out.println(path);
		// Use relative path for Unix systems
		FileWriter f1 = new FileWriter(percorsoFinale);
		//System.out.println(f1);
		//cosi lo legge
		//File f2 = new File(percorso);
		//System.out.println(f2.exists());
		
		//SENZA RICORSIONE
		File folder = new File(nomeDir);
		
		//creo un array dove salvo i file della cartella
		File[] listOfFiles = folder.listFiles();
		
		Scrittura s = new Scrittura();
		s.ricorsiva(listOfFiles,f1, 0);
		
		
		
		
		f1.flush();
		f1.close();
		
		//CON RICORSIONE
		
		
		
	}
	
public void ricorsiva(File [] listOfFiles, FileWriter f1, int livello) throws Exception {
	
	//creo lista di files a partire dal folder attuale

	/*for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}*/
	//va out of bound
	/*for(int i =0; i<listOfFiles.length; i++) {
		if(listOfFiles[i].isFile()) {
			
			f1.write(listOfFiles[i].getName());
			f1.write('\n');
			f1.flush();
		} 
		if(listOfFiles[i].isDirectory()) {
			//aggiorno la lista di file a partire dalla directory corrente
			listOfFiles = listOfFiles[i].listFiles();
			f1.write(listOfFiles[i].getName() + "(dir)");
			ricorsiva(folder, f1);
			
		}*/
	
	
	
	for(File f : listOfFiles) {
		if(f!=null) {
			if(f.isFile()) {
				f1.write(getIndentation(livello));
				f1.write(f.getName());
				f1.write('\n');
				f1.flush();
			} else if(f.isDirectory()) {
				f1.write(getIndentation(livello));
				f1.write(f.getName()+"(dir)");
				f1.write('\n');
				livello++;
				listOfFiles=f.listFiles();
				ricorsiva(listOfFiles,f1, livello);
			}
		}
	
	}
	
}

public String getIndentation(int livello) {
	StringBuilder sb = new StringBuilder();
	for(int i=0; i<livello; i++) {
		sb.append('\t');
	}
	return sb.toString();
}
	
}
