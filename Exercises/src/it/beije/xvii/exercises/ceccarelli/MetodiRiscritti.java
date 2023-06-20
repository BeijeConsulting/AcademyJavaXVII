package it.beije.xvii.exercises.ceccarelli;

import java.util.ArrayList;
import java.util.Scanner;

public class MetodiRiscritti {

	public MetodiRiscritti() {
		// TODO Auto-generated constructor stub
	}
	
	// int indexOf(String s, char c, index fromIndex)
	public int indexof(String s, char c, int fromIndex) {
		//ArrayList<String> indici = new ArrayList<String>(); 
		for(int i=fromIndex;i<s.length();i++) {
			if(s.charAt(i)==c) {
				return i;
			}
		}
		return -1;
	}
	
	//int indexOf(String s, String str)
	public int indexofD(String s, String str) {
		boolean b = false;
		int indice =0;
	//ArrayList<String> indici = new ArrayList<String>(); 
		for(int i=0;i<s.length();i++) { // ciao // telefono
			for(int y=0;y<str.length();y++) { // ia
				if(s.charAt(i)==str.charAt(y)) {
					b = true;
					indice = (s.length()-((s.length())-i));
					}else {
						b = false;
				}
			
		}
		if(b) {
				return indice;
		}
	}
	return -1;
	}
	
	
	//String substring(String s, int beginIndex)
	public String substring(String s, int beginIndex) {
		String sb="";
		for(int i=beginIndex; i< s.length(); i++) {
			sb += s.charAt(i);
		}
		return sb;
	}
	
	//String substring(String s, int beginIndex, int endIndex)
	public String substring(String s, int beginIndex, int endIndex) {
		String sb="";
		for(int i=beginIndex; i< endIndex; i++) {
			sb += s.charAt(i);
		}
		return sb;
	}
	
	//String toLowerCase(String s)
	public String toLowerCase(String s) {
		String st="";
		for(int i=0;i<s.length();i++) {
			if (s.charAt(i)>= 'A' && s.charAt(i) <= 'Z') {
				char c = (char)(s.charAt(i) + 32);
				st+=c;
			}else {
				st+=s.charAt(i);
			}
		}
		return st;
	}
	
	// String toUpperCase(String s)
	public String toUpperCase(String s) {
		String st="";
		for(int i=0;i<s.length();i++) {
			if (s.charAt(i)>= 'a' && s.charAt(i) <= 'z') {
				char c = (char)(s.charAt(i) - 32);
				st+=c;
			}else {
				st+=s.charAt(i);
			}
		}
		return st;
	}
	
	// boolean equals(String s1, String s2)
	public boolean equals(String s1, String s2) {
		if(s1.length()!=s2.length()) {
			return false;
		}
		for(int i=0;i<s1.length();i++) {
			if(s1.charAt(i)!=s2.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	// boolean equalsIgnoreCase(String s1, String s2)
	public boolean equalsIgnoreCase(String s1, String s2) {
		if(s1.length()!=s2.length()) {
			return false;
		}
		String s1m = toLowerCase(s1);
		String s2m = toLowerCase(s2);
		for(int i=0;i<s1m.length();i++) {
			if(s1m.charAt(i)!=s2m.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	// boolean contains(String s, String str)
	public boolean containsR(String s, String str) {
		boolean b=true;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)== str.charAt(0)) {
				int count =0;
				for(int y=i;y<s.length();y++) {
					if(count<str.length()) {
					if(s.charAt(y)!=str.charAt(count)) {
						b= false;
						break;
					}
					count++;
					} else {
						break;
					}
				}
			}
		}
		return b; 
	}
	
	//boolean startsWith(String s, String prefix)
	public boolean startsWith(String s, String prefix) {
		boolean b = false;
		if(prefix.length()>=s.length()) {
			return false;
		}
		for(int i=0;i<prefix.length();i++) {
			if(s.charAt(i)==prefix.charAt(i)) {
				b=true;
			}else {
				b=false;
			}
		}
		return b;
	}
	
	//boolean endsWith(String s, String suffix)
	public boolean endsWith(String s, String suffix) {
		boolean b = false;
		if(suffix.length()>=s.length()) {
			return false;
		}
		for(int i=0;i<suffix.length();i++) {
			if(s.charAt(s.length()-i-1)==suffix.charAt(suffix.length()-i-1)) {
				b=true;
			}else {
				b=false;
			}
		}
		return b;
	}
	
	// String replace(String s, char oldChar, char newChar)
	public String replace(String s, char oldChar, char newChar) {
		String st ="";
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)==oldChar) {
				st+=newChar;
			}else {
				st+=s.charAt(i);
			}
		}
		return st;
	}
	
	// String replace(String s, String oldChar, String newChar)
	public String replace(String s, String old, String newS) {
		String st ="";
		int indice = indexofD(s, old);
		System.out.println("indice:" + indice);
		for(int i=0;i<s.length();i++) {
			if(i==indice) {
				st+=newS;
				i = i+newS.length()-1;
			}else {
				st+=s.charAt(i);
			}
		}
		return st;
	}
	
	// String trim(String s)
	public String trim(String s) {
		String st ="";
		char prec=0;
		System.out.println(s.length());
		for(int i=0;i<s.length();i++) {
			if ( i!=0 ) {
				prec = s.charAt(i-1);
				} else {
					prec = ' ';
					}
			if(s.charAt(i) == ' ' && (prec==' ')) {
			}else {
				st+= s.charAt(i);
			}
		}
		return st;
			
	}
	
	//StringBuilder append(StringBuilder sb, String str)
	public StringBuilder append(StringBuilder sb, String str) {
		String s = sb.toString();
			s+= str;
		sb = new StringBuilder(s);
		return sb;
	}
	//StringBuilder insert(StringBuilder sb, int offset, String str)
	public StringBuilder insert(StringBuilder sb, int offset, String str) {
		String s = sb.toString();
		String s2 = "";
		for(int i=0; i<s.length();i++) {
			s2 += s.charAt(i);
			if(i==offset) {
				for(int y=0;y<str.length();y++) {
					s2+=str.charAt(y);
				}
			}
				
			
		}
		sb = new StringBuilder(s2);
		return sb;
	}
	//StringBuilder delete(StringBuilder sb, int start, int end)
	public StringBuilder delete(StringBuilder sb, int start, int end) {
		String s = sb.toString();
		String s2 ="";
		for(int i=0;i<s.length();i++) {
			if(i<start || i>=end) {
				s2 += s.charAt(i);
			}
		}
		sb = new StringBuilder(s2);
		return sb;
	}
	//StringBuilder deleteCharAt(StringBuilder sb, int index)
	public StringBuilder deleteCharAt(StringBuilder sb, int index) {
		String s = sb.toString();
		String s2 ="";
		for(int i=0;i<s.length();i++) {
			if(i!=index) {
				s2 += s.charAt(i);
			}
		}
		sb = new StringBuilder(s2);
		return sb;
	}
	//StringBuilder reverse(StringBuilder sb)
	public StringBuilder reverse(StringBuilder sb) {
		String s = sb.toString();
		String s2 ="";
		for(int i=s.length()-1;i>=0;i--) {
			s2 += s.charAt(i);
		}
		return sb = new StringBuilder(s2);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MetodiRiscritti m = new MetodiRiscritti();
		Scanner scan = new Scanner(System.in);
		System.out.println("inserisci parametri: ");
		//String s = scan.next();
		//String s1 = scan.next();
		//String s2=scan.next();
		//String s1 = scan.nextLine();
		//String s2=scan.nextLine();
		//int intero = scan.nextInt();
		//int indice = scan.nextInt();
		//int indice2 = scan.nextInt();
		//char c = scan.next().charAt(0);
		//char c2 = scan.next().charAt(0);
		//System.out.println(m.indexof(s,c,intero));
		//System.out.println(m.indexofD(s, sub));
		//System.out.println(m.substring(s, 2));
		//System.out.println(m.substring(s, 2, 6));
		//System.out.println(m.toLowerCase(s));
		//System.out.println(m.toUpperCase(s));;
		//System.out.println(m.equals(s1,s2));
		//System.out.println(m.equalsIgnoreCase(s1,s2));
		//System.out.println(m.containsR(s1,s2));
		//System.out.println(m.startsWith(s1,s2));
		//System.out.println(m.endsWith(s1,s2));
		//System.out.println(m.replace(s,c,c2));;
		//System.out.println(m.replace(s,s1,s2));
		//System.out.println(m.trim(s_));
		StringBuilder ss = new StringBuilder(scan.next());
		//System.out.println(m.insert(ss,indice,r));
		//System.out.println(m.delete(ss,indice,indice2));
		//System.out.println(m.deleteCharAt(ss,indice));
		System.out.println(m.reverse(ss));
	}

}
