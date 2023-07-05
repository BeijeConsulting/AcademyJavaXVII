package it.beije.suormary.rubrica.sala;

import java.util.List;

public class ToolImportExport {
	private String pathFile = "/Users/Padawan/Desktop/fileAcademy/rubrica.csv";
	
	private String pathFileXML = "/Users/Padawan/Desktop/fileAcademy/rubrica.xml";
	
	private String pathFileNuovoCSV = "/Users/Padawan/Desktop/fileAcademy/rubricaNuovo.csv";
	private String pathFileNuovoXML = "/Users/Padawan/Desktop/fileAcademy/rubricaNuovo.xml";
	
	private String separator = ";";
	
	
	public static void main(String[] args) {
		
		RubricaUtils ru = new RubricaUtils();
		ToolImportExport tie = new ToolImportExport();
		
		String nomeDB = "jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET";
		String account = "root";
		String password = "Arlabunakti";	
		
		tie.fromCVStoDB(ru);
		
	}
	
	public void fromCVStoDB(RubricaUtils ru){
		//leggo da CSV e scrivo si DB
				List<Contatto> contatti = ru.loadRubricaFromCSV(pathFile, separator);
				
				if(contatti!=null && contatti.size()>0) {
					//scrivo su DB dopo aver letto da CSV
					ru.writeRubricaDBInsert(contatti);
				} else {
					System.out.println("no contatti da inserire");
				}
	}
	
	public void fromXMLtoDB(RubricaUtils ru) {
		//leggo da XML e scrivo su DB
				List<Contatto> c = ru.loadRubricaFromXML(pathFileXML);
				
				if(c!=null && c.size()>0) {
					//scrivo su DB dopo aver letto da CSV
					ru.writeRubricaDBInsert(c);
				} else {
					System.out.println("no contatti da inserire");
				}
	}
	
	public void fromDBtoCSV(RubricaUtils ru) {
		List<Contatto> cDB = ru.loadRubricaFromDB();
				//leggo da DB e scrivo su CSV
				if(cDB!=null && cDB.size()>0) {
					//scrivo su DB dopo aver letto da CSV
					ru.writeRubricaCSV(cDB, pathFileNuovoCSV, separator);
				} else {
					System.out.println("no contatti da inserire");
				}			
	}
	
	public void fromDBtoXML(RubricaUtils ru) {
		List<Contatto> cDB = ru.loadRubricaFromDB();
		//leggo da DB e scrivo su XML
				if(cDB!=null && cDB.size()>0) {
					//scrivo su DB dopo aver letto da CSV
					ru.writeRubricaXML(cDB, pathFileNuovoXML);
				} else {
					System.out.println("no contatti da inserire");
				}
	}
}
