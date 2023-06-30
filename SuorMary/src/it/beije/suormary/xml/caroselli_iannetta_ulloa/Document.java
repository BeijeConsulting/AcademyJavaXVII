package it.beije.suormary.xml.caroselli_iannetta_ulloa;

import com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Document {


    private List<Node> nodes;

//    public Document(RootNode root, List<Node> childNodes) {
//        this.root = root;
//        this.childNodes = childNodes;
//    }
//
//	  public void addChild(Node childNode) {
//	    this.childNodes.add(childNode);
//	  }
//
//      public List<Node> getChildNodes() {
//        return childNodes;
//      }
//
//      public void setRoot(RootNode node) {
//        this.root = node;
//      }
//
//      public RootNode getRoot() {
//        return root;
//      }

    public static Node findNodes(String s) {
		List<Node> nodes = new ArrayList<>();
		StringBuilder tagName = new StringBuilder();
		StringBuilder content = new StringBuilder();
		
		char c = s.charAt(0);
		if (c == '<') {
			
			tagName.setLength(0);
			char ch;
			int i;
			for (i = 1; i < s.length() && (ch = s.charAt(i)) != '>'; i++) {
				ch = s.charAt(i);
				tagName.append(ch);
			}
			i++;
			String endTagName = "</" + tagName.toString() + ">";
			int index = s.indexOf(endTagName);
			
			content.append(s.substring(i, index -1));
			System.out.println(content.toString());
			
			if (content.toString().contains("<")) {
				Node node = findNodes(content.toString());
				nodes.add(node);
			}
			else nodes.add(new Node (tagName.toString(), content.toString()));
			
			
			//Element el  = new Element (tagName, attributes, contents)
		}
		else if (c == '\n' || c == '\t' || c == '\r'){ //consider space later
			if (nodes.isEmpty() || !nodes.get(nodes.size() - 1).isBlank()) {
				Node blankNode = new Node();
				nodes.add(blankNode);
				System.out.println("sono bianco");
				}
		}
		//System.out.println(nodes.toString());
		
		Node node;
		if(nodes.size() != 0){
			node = new Node(tagName.toString(), nodes);
		}
		else node = new Node(tagName.toString(), content.toString());
		
		return node;
	}
    public void createDocument() {

//        RootNode rootElement = root.getRootElement();


    }


}
