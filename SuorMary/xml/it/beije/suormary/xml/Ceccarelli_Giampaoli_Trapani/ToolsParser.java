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

import org.w3c.dom.Element;

public class ToolsParser {
		
	public static  FileReader fileReader = null;
	public static 	BufferedReader bufferedReader = null;
	public static   List<String> rows = null;
	public static  Element el = null;
		
		
	
	public static Element getRootElement(String pathFile) {

	

		
		try {
			File file = new File(pathFile);
			fileReader = new FileReader(file);
			
			
			bufferedReader = new BufferedReader(fileReader);
			rows = new ArrayList<String>();
			while (bufferedReader.ready()) {
				String r = bufferedReader.readLine();
				
				rows.add(r);
			}
//			for (String s : rows) {
//				System.out.println(s);
//				}
			
			char carattereInizio = '<';
			char carattereFine = '>';
			int i=0;
			
			el = new Element();
			
			for (String s : rows) {
				
				int indiceInizio = s.indexOf(carattereInizio)+1;
				int indiceFine = s.indexOf(carattereFine);
				String str = s.substring(indiceInizio, indiceFine);
				String str2 = s.substring((s.indexOf(carattereFine)+1),s.indexOf(carattereInizio));
				if(i==1) {
					el.setTagName(str);
				}				
				i++;
			}
			
			
			
			
			
			
			

//					
//			int i=0;
//							
//				for (String s : rows) {
//					
//					int indiceInizio = s.indexOf(carattereInizio)+1;
//					int indiceFine = s.indexOf(carattereFine);
//					String str = s.substring(indiceInizio, indiceFine);
//					if(i!=0) {
//						el.add(str);
//					}				
//					i++;
//				}
//				
//			System.out.println(el.get(0));
//				for (String s : el) {
//					System.out.println(s);
//				}
//				

		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	

}
