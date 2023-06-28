package it.beije.suormary.rubrica;
		

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MetodiListContact{
		//LETTURA FILE CSV/XML/DB
	public static List<Contact> loadContactListFromCSV(String pathFile, String separator) {

		File file = new File(pathFile);  //apertura file da pathFile
		
		FileReader fileReader = null;	
		BufferedReader bufferedReader = null;  	 
		List<String> rows = new ArrayList<String>();			//"salvo" file in arraylist di stringhe	
		List<Contact> contacts = new ArrayList<Contact>();		//creo lista contatti
		Contact c = null;		
		
	
		try {															//controllo eccezioni
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			String prima = bufferedReader.readLine();					//leggo prima riga usata come "guida"
			
			while(bufferedReader.ready()) {								//leggo resto file e salvo in arraylist
				String r = bufferedReader.readLine();
				rows.add(r);
			}
			
			String[] campi = prima.split(separator);					// campi di riferimento in campi[]
			
			for (String row : rows) {									//row ogni riga di arraylist file
				String[] cont = row.split(separator);
				  c = new Contact();
				for (int i=0; i<campi.length; i++) {							//controllo e salvo info corretta a colonna corretta
					switch(campi[i]) {
						case "NOME": c.setName(cont[i]); break;
						case "COGNOME": c.setSurname(cont[i]); break;
						case "TELEFONO": c.setPhoneNumber(cont[i]); break;
						case "EMAIL": c.setEmail(cont[i]); break;
						case "NOTE": c.setNote(cont[i]); break;
					}
				}			
				contacts.add(c);	
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {	
			
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contacts;
	}			
	
	public static List<Contact> loadContactListFromXML(String pathFile) {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		List<Element> cont = null;
		List<Contact> contactsList = null;
		List<Element> atrCont = null;
		DocumentBuilder documentBuilder;	
		
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document rubrica = documentBuilder.parse(pathFile);
			Element docEl = rubrica.getDocumentElement();
			cont = getChildElements(docEl);		
			contactsList = new ArrayList<Contact>();
			
			Contact c = null;
			for (Element elCon : cont) {				//elemento per ogni contatto 
				atrCont = getChildElements(elCon);		//prendo attributi di ogni contatto
				c = new Contact();
				for(Element e : atrCont) {				//elemento per ogni attr
					switch(e.getTagName()) {								//setto info
					case "nome": c.setName(e.getTextContent());
					break;
					case "cognome": c.setSurname(e.getTextContent());
						break;
					case "telefono": c.setPhoneNumber(e.getTextContent());
						break;
					case "email": c.setEmail(e.getTextContent());
						break;
					case "note": c.setNote(e.getTextContent());
						break;
					default: System.out.println("TagName non riconosciuto!");
						break; 
					}
				} contactsList.add(c);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			
			e.printStackTrace();
		}
		return contactsList;
	}

	public static List<Element> getChildElements(Element el) {				//trovo elementi figlio contatto
		NodeList nodeList = el.getChildNodes();
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		return elements;
	}

	public static List<Contact> loadContactListFromDB(String pathDB, String user, String pw) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		Connection connection = DriverManager.getConnection(pathDB, user, pw);
		Statement statement =connection.createStatement(); ;
		
		List<Contact> contacts = new ArrayList<>();
		Contact c = null;
		
		ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				c = new Contact();
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				c.setPhoneNumber(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				c.setNote(rs.getString("note"));
				contacts.add(c);
			}
		return contacts;
	}

		//SCRITTURA CONTATTI SU FILE CSV/XML/DB
	public static void writeContactsInRubricaCSV(List<Contact> contatti, String pathFile, String separator) throws IOException {
		StringBuilder row=null;
		FileWriter fileAgg = null;
		
		if(new File(pathFile).exists()) {
			fileAgg = new FileWriter(pathFile,true); //se il file esiste continuo sotto
		}
			
		for (Contact c : contatti) {
			row = new StringBuilder (c.getSurname() + separator + c.getName() + separator 
					+ c.getPhoneNumber() + separator + c.getEmail() + separator + c.getNote() + "\n");
			fileAgg.write(row.toString());
		}
		fileAgg.flush();
		fileAgg.close();
				
		
	}
	
	public static void writeContactsInRubricaXML(List<Contact> contatti, String pathFile) {
		//da rivedere
	}

	public static void writeContactsInRubricaDB(List<Contact> contatti, Connection connection) throws SQLException {
		
		Statement statement = connection.createStatement();
		StringBuilder agg = null;
		for(Contact c : contatti) {
			agg = new StringBuilder("INSERT INTO rubrica (`nome`, `cognome`, `telefono`, `email`, `note`) VALUES('")
					.append(c.getName()).append("', '").append(c.getSurname()).append("', '")
					.append(c.getPhoneNumber()).append("', '").append(c.getEmail())
					.append("', '").append(c.getNote()).append("')");
			statement.executeUpdate(agg.toString());
		}
		
		connection.close();
		statement.close();
	}

	public static void main(String[] args) throws Exception {
		
		List<Contact> prova = new ArrayList<>();
		prova=loadContactListFromCSV("/Users/marty/Desktop/Marti/Beije/Esercizi Academy/FILE ESERCIZI/nuovo 1.csv", ";");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "Rick&Morty63!!");
		
		writeContactsInRubricaDB(prova,connection);
		
//		prova=loadRubricaFromXML("/Users/marty/Desktop/Marti/Beije/Esercizi Academy/FILE ESERCIZI/rubrica.xml");
//		prova=loadRubricaFromDB("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "Rick&Morty63!!");
//		System.out.println(prova.toString());
//		writeContactsInRubricaCSV(prova,"/Users/marty/Desktop/Marti/Beije/Esercizi Academy/FILE ESERCIZI/rubrica.csv", ";");

	}

}
