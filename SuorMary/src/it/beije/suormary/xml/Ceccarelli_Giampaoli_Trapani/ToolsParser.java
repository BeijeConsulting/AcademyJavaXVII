package it.beije.suormary.xml.Ceccarelli_Giampaoli_Trapani;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ToolsParser {
		
	public  FileReader fileReader = null;
	public  BufferedReader bufferedReader = null;
	public  List<String> rows = null;
	public  Element el = null;
		
	public List<String> readXML (String pathFile){
		try {
			File file = new File(pathFile);
			fileReader = new FileReader(file);
						
			
			bufferedReader = new BufferedReader(fileReader);
			rows = new ArrayList<String>();
			String rIgnorare = bufferedReader.readLine();
			String[] r1 = null;
			while (bufferedReader.ready()) {
				String r = bufferedReader.readLine();
				r1 = r.split("]<>]");			//divido file per ogni valore delimitato da '<' e '>'
				for(String a : r1) {

					rows.add(a.trim());  		//salvo file letto in Arraylist 
				}
			}
			} catch (IOException e) {
				e.printStackTrace();
			}  catch (Exception e) { 
				e.printStackTrace();
			}
		
		return rows;
	}
	
	public void tree(List<String> rows) {	
		Element root = new Element();
		
		StringBuilder str = new StringBuilder();
		str.append(rows.get(0));
		str.insert(1, '/');		
		
		if(rows.get(rows.size()-1).equals(str.toString())) {			//controllo formattazione file e se primo elemento utile e chiuso come ultimo elemento 
			root.setTagName(rows.get(0));					//setto tagName di RootElement 
			System.out.println(root.getTagName());
		} else {
			System.out.println("File non valido");			//root element non chiuso, file non valido
			return;
		}
		
//		for(int i=2; i<rows.size(); i++) {
//			if(rows.get(i).isEmpty()) {
//			String close = "/".concat(rows.get(i));
//			if(rows.get(i).equals(str)) {			 
//				root.setTagName(rows.get(1));					
//				System.out.println(root.getTagName());
//		}
//		}
//		}

	}
		
		
		
	
	
	
	
	
	
	public Element getRootElement(String pathFile) {

//		try {
//			
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}  catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return null;
	}
	
	
	

}
