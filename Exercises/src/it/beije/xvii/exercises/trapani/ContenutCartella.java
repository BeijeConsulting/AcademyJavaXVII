package it.beije.xvii.exercises.trapani;
import java.io.File;
import java.io.FileWriter;


		

public class ContenutCartella {
	
	public FileWriter contenuto;
	
	
//	creo costruttore scrittura file
	
	public ContenutCartella() throws Exception{
		this.contenuto = new FileWriter("C:\\Users\\marty\\Desktop\\ElencoContenuto.csv");
	}
	
	
	
	public FileWriter leggiFile(File file) throws Exception {
	
		if(file.isFile()) {
			this.contenuto.write(file.getName() + "\n");
			} else if (file.isDirectory()) {
				this.contenuto.write(file.getName() + "(directory)\n");
				File[] files = file.listFiles();
				if(files != null){
					for(File file2 : files) {
					this.leggiFile(file2);
				}	
				}	
			}			
		
	return this.contenuto;		
	}
	
	public void close() throws Exception {
		this.contenuto.close();
	}

	public static void main(String[] args) throws Exception {
		
		File directory = new File("C:\\Users\\marty\\Desktop");
		ContenutCartella cc = new ContenutCartella();
		
		
		File[] files = directory.listFiles();
		for (File file : files) {
			cc.leggiFile(file);
		}
	 cc.close();

	}

}
