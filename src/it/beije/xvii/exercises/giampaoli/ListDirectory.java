package it.beije.xvii.exercises.giampaoli;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.io.File;  
import java.io.IOException; 

public class ListDirectory {
	
	public static void main(String args[])   {  
     
		StringBuilder ea = new StringBuilder("123456");
		ea.subSequence(2,4);
		ea.deleteCharAt(3);
		ea.reverse();
		System.out.println(ea);
		
	Path dir = Paths.get("C:\\Users\\Jamp\\Desktop\\Jamp\\Lavoro\\Beije\\Esercizi") ; 

	File fDirectory = new File("C:\\Users\\Jamp\\Desktop\\Jamp\\Lavoro\\Beije\\Esercizi");
	int iFileCount = fDirectory.list().length;
	
	String aNomiFile[];	  
	aNomiFile = new String[iFileCount];
    int iCon = 0;
	
	try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
	    for (Path file: stream) {
	    	
	       System.out.println(file.getFileName());
	    
	       aNomiFile[iCon] = file.getFileName().toString();
	       iCon += 1;
	       
	    }
	} catch (IOException | DirectoryIteratorException x) {
	    // IOException can never be thrown by the iteration.
	    // In this snippet, it can only be thrown by newDirectoryStream.
	    System.err.println(x);
	}
	

	    try {
	    	
	    	String dirName = "C:\\Users\\Jamp\\Desktop\\Jamp\\Lavoro\\Beije\\Esercizi";

	    	String fileName = "test.txt";
	    	File dir2 = new File (dirName);
	    	File actualFile = new File (dir2, fileName);
	    	
	    	
	    	
	      //File myObj = new File("test.txt");
	      if (actualFile.createNewFile()) {
	        System.out.println("File created: " + actualFile.getName());
	        
	        
	        FileWriter myWriter = new FileWriter("test.txt");
	        
	        for (int i = 0; i <= aNomiFile.length; i++) {
	        	 myWriter.write(aNomiFile[i]);
//	        	 myWriter.close();
	        }
	        
//	        myWriter.write("Files in Java might be tricky, but it is fun enough!");
	        myWriter.flush();
	        myWriter.close();
//	        
	        
	        
	      } else {
	        System.out.println("File already exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }	
	
	}
} 
