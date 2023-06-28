package it.beije.xvii.exercises.Char;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class ToolsRubrica {
       public static void main(String[] args) {
//    	   exportDbToXML("/v/jdd2.xml");
       }
       public static void exportDbToCSV () {
    	   Scanner scanner = new Scanner(System.in);
    	   System.out.print("Indica il path del file CSV : ");
    	   String pathCSV = scanner.nextLine();
    	   System.out.print("Indica il tipo di separatore per i campi : ");
    	   String separator = scanner.nextLine();
    	  List<Contact> contacts = RubricaJDBC.loadRubricaJDBC();
    	  EsRubrica.writeRubricaCSV(contacts, pathCSV, separator);
       }
       public static void exportDbToXML () {
    	   Scanner scanner = new Scanner(System.in);
    	   System.out.print("Indica il path del file XML : ");
    	   String pathXML = scanner.nextLine();
    	  List<Contact> contacts = RubricaJDBC.loadRubricaJDBC();
    	  EsRubrica.writeRubricaXML(contacts,pathXML);
       }
       public static void exportCSVtoDb() {
    	   Scanner scanner = new Scanner(System.in);
    	   System.out.print("Indica il path del file CSV : ");
    	   String pathFile = scanner.nextLine();
    	   System.out.print("Indica il tipo di separatore per i campi : ");
    	   String separator = scanner.nextLine();
    	  List<Contact> contacts = EsRubrica.loadRubricaFromCSV(pathFile, separator);
    	  RubricaJDBC.writeRubricaJDBC(contacts);
       }
       public static void exportXMLtoDb() {
    	   Scanner scanner = new Scanner(System.in);
    	   System.out.print("Indica il path del file CSV : ");
    	   String pathFile = scanner.nextLine();
     	  List<Contact> contacts = EsRubrica.loadRubricaFromXML(pathFile);
     	  RubricaJDBC.writeRubricaJDBC(contacts);
        }
       
}
