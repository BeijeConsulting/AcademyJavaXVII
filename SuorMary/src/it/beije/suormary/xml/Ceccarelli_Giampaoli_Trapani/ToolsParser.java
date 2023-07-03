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
import java.util.Stack;



public class ToolsParser {
		
	public  FileReader fileReader = null;
	public  BufferedReader bufferedReader = null;
	public  List<String> rows = null;
	public  Element el = null;
	public List<Node> tree = null;
	public  Node root = null;
		
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
				 r1 = r.split(">");		//divido file per ogni valore delimitato da '>'
				 						// solo valori interni -> <([^<]*)>
	                if(r1.length>1 && !r1[1].isEmpty()) {

	                    rows.add(r1[0].trim() + ">"); // <nome>
	                    rows.add(r1[1].split("</")[0].trim()); // Pippo
	                    rows.add("</" + r1[1].split("</")[1].trim()+ ">"); // </nome>
	                } else {
//	                    System.out.println("RRRRRR: " + r);
	                    rows.add(r.trim());
	                }
	            }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }  catch (Exception e) { 
	                e.printStackTrace();
	            }

	        return rows;
	    }
	
	public Node tree(List<String> rows) {
        root = new Node();
        Node n = null;
        Element e = null;
        Stack<Node> stack = new Stack<Node>();
        tree = new ArrayList<Node>();

        StringBuilder str = new StringBuilder();
        str.append(rows.get(0));
        str.insert(1, '/');

        if(rows.get(rows.size()-1).equals(str.toString())) {            //controllo formattazione file e se primo elemento utile e chiuso come ultimo elemento 
            root.setTagName(rows.get(0));    //setto tagName di RootElement 
            stack.push(root);
            //System.out.println(root.getTagName());
        } else {
            System.out.println("File non valido");            //root element non chiuso, file non valido
            return null;
        }

        for(int i=1; i<rows.size(); i++) {
            if(rows.get(i).startsWith("</")) {
                stack.pop();
                continue;
            }
            if(!rows.get(i).startsWith("<")) {

                ((Element)(stack.peek())).setValues(rows.get(i));
                continue;
            }
            if (rows.get(i+1).startsWith("<")) {
                n = new Node(rows.get(i));
                stack.peek().getChildEl().add(n);
                stack.push(n);
            } else {
                e = new Element(rows.get(i));
                stack.peek().getChildEl().add(e);
                stack.push(e);
            }
    }
        return root;
    }
	
	public void getRootElement(Node root) {

      root.printNode();

  }
	
	// prendi solo i nodi 
	public void getChildNode(String name){
//		List<Node> child = new ArrayList<>();
		if(name.equals(root.getTagName())) {
			if(root.getChildEl().isEmpty()) {
			System.out.println("Non esistono Child Nodes");
			} else {
				int i = 0;
				for(Node m : root.getChildEl()) {
	
					System.out.println("Figlio " + i + ": " + m.getTagName());
					i++;
				}
			}
			} else {
				for(Node m : root.getChildEl()) {
					
					int ii=0;
					if(name.equals(m.getTagName())) {
						//System.out.println("Padre: " + name);
						int count =0;
						for(Node nodeChild : m.getChildEl()) {
							if(!(nodeChild instanceof Element )) {
								//System.out.println("Figlio " + ii + ": " + nodeChild.getTagName());
								nodeChild.getTagName();
								count++;
							ii++;
							};
							
							}
						if(count==0) {
							System.out.println("Padre " + name + "Non ha nodi figli");
						}
					}
				}
		}
	}
		
		// prende gli elementi figli
		public void getChildElement(String name){
//			List<Node> child = new ArrayList<>();
			if(name.equals(root.getTagName())) {
				if(root.getChildEl().isEmpty()) {
				System.out.println("Non esistono Child Nodes");
				} else {
					int i = 0;
					int count =0;
					for(Node m : root.getChildEl()) {
						
						if((m instanceof Element )) {
							System.out.println("Figlio " + i + ": " + m.getTagName());
							i++;
							count++;
						}
					}
					if(count==0) {
						System.out.println("Padre " + name + " Non ha nodi figli");
				}
				}
				} else {
					for(Node m : root.getChildEl()) {
						
						int ii=0;
						if(name.equals(m.getTagName())) {
							System.out.println("Padre: " + name);
							//int count =0;
							for(Node nodeChild : m.getChildEl()) {
								if((nodeChild instanceof Element )) {
									System.out.println("\tFiglio " + ii + ": " + nodeChild.getTagName());
									//count++;
								ii++;
								};
								
								}
							//if(count==0) {
								//System.out.println("Padre " + name + "Non ha nodi figli");
							}
						}
					}
			}

		
	}
