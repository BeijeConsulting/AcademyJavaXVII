package it.beije.xvii.exercises.Char;
import java.util.List;
import java.util.ArrayList;
public class ToolsRubrica {
       public static void main(String[] args) {
//    	   exportDbToXML("/v/jdd2.xml");
       }
       public static void exportDbToCSV (String pathCSV, String separator) {
    	  List<Contact> contacts = RubricaJDBC.loadRubricaJDBC();
    	  EsRubrica.writeRubricaCSV(contacts, pathCSV, separator);
       }
       public static void exportDbToXML (String pathXML) {
    	  List<Contact> contacts = RubricaJDBC.loadRubricaJDBC();
    	  EsRubrica.writeRubricaXML(contacts,pathXML);
       }
       public static void importCSVtoDb(String pathFile, String separator) {
    	  List<Contact> contacts = EsRubrica.loadRubricaFromCSV(pathFile, separator);
    	  RubricaJDBC.writeRubricaJDBC(contacts);
       }
       public static void importXMLtoDb(String pathFile) {
     	  List<Contact> contacts = EsRubrica.loadRubricaFromXML(pathFile);
     	  RubricaJDBC.writeRubricaJDBC(contacts);
        }
       
}
