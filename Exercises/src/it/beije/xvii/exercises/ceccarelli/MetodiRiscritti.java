package it.beije.xvii.exercises.ceccarelli;

import java.util.ArrayList;
import java.util.Scanner;

public class MetodiRiscritti {

	public MetodiRiscritti() {
		// TODO Auto-generated constructor stub
	}
	/*
	int indexOf(String s, char c, index fromIndex)
	int indexOf(String s, String str)
	int indexOf(String s, String str, index fromIndex)
	String substring(String s, int beginIndex)
	String substring(String s, int beginIndex, int endIndex)
	String toLowerCase(String s)
	String toUpperCase(String s)
	boolean equals(String s1, String s2)
	boolean equalsIgnoreCase(String s1, String s2)
	boolean contains(String s, String str)
	boolean startsWith(String s, String prefix)
	boolean endsWith(String s, String suffix)
	String replace(String s, char oldChar, char newChar)
	String replace(String s, String oldChar, String newChar)
	String trim(String s)
	*/
	
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
		for(int i=0;i<s.length();i++) { // ciao
			for(int y=0;y<str.length();y++) { // ia
				if(s.charAt(i)==str.charAt(y)) {
					b = true;
					indice = (s.length()-1)-i;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MetodiRiscritti m = new MetodiRiscritti();
		Scanner scan = new Scanner(System.in);
		System.out.println("inserisci stringa: ");
		String s = scan.next();
		System.out.println("inserisci intero: ");
		int intero = scan.nextInt();
		System.out.println("inserisci carattere: ");
		char c = scan.next().charAt(0);
		System.out.println("inserisci substringa: ");
		String sub = scan.next();
		System.out.println(m.indexof(s,c,intero));
		System.out.println(m.indexofD(s, sub));
		

	}

}
