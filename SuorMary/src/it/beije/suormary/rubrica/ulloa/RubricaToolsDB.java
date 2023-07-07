package it.beije.suormary.rubrica.ulloa;

import java.util.List;

public class RubricaToolsDB {
	 private RubricaUtils rubricaUtils;
	 
	 public RubricaToolsDB() {
		 this.rubricaUtils = new RubricaUtils();
	 }
	 
	 public void importFromCSV(String csvFilePath, String separator) {
		 // Utilizza il metodo esistente di RubricaUtils per leggere i dati dal file CSV
		 List<Contact> contatti = rubricaUtils.loadRubricaFromCSV(csvFilePath,separator);
		 
		 // Utilizza il metodo esistente per l'inserimento dei record (lista contatti) nel database
		 //rubricaUtils.writeRubricaJDBC(contatti);
		 //rubricaUtils.writeRubricaHBM(contatti);
		 rubricaUtils.writeRubricaJPA(contatti);
	 }
	 
	 public void exportToCSV(String csvFilePath, String separator) {
		 //List<Contact> contatti = rubricaUtils.loadRubricaFromJDBC();
		 //List<Contact> contatti = rubricaUtils.loadRubricaFromHBM();
		 List<Contact> contatti = rubricaUtils.loadRubricaFromJPA();
		 rubricaUtils.writeRubricaCSV(contatti, csvFilePath, separator);
	    }
	 
	 public void importFromXML(String xmlFilePath) {
		 // Utilizza i metodi esistenti di RubricaUtils per leggere i dati dal file XML
		 List<Contact> contatti = rubricaUtils.loadRubricaFromXML(xmlFilePath);

		 // Utilizza il metodo esistente per l'inserimento dei record (lista contatti) nel database
		 //rubricaUtils.writeRubricaJDBC(contatti);
		 //rubricaUtils.writeRubricaHBM(contatti);
		 rubricaUtils.writeRubricaJPA(contatti);
	 }
	 
	 public void exportToXML(String xmlFilePath) {
		 //List<Contact> contatti = rubricaUtils.loadRubricaFromJDBC();
		 //List<Contact> contatti = rubricaUtils.loadRubricaFromHBM();
		 List<Contact> contatti = rubricaUtils.loadRubricaFromJPA();
		 rubricaUtils.writeRubricaXML(contatti, xmlFilePath);
	    }
	 
}
