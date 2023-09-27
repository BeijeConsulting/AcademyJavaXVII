package soluzioni.esercizio5;

import java.util.Arrays;

/*
 * https://www.codewars.com/kata/550f22f4d758534c1100025a
 * Ti viene dato un percorso sotto forma di array con direzioni N, S, E, W
 * ottimizza il percorso eliminando le coppie di direzioni opporte vicine
 * es. [E, S, N, E, W, W, S] DIVENTERà [E, W, S] e poi [S]
 * perchè andare prima a sud e immediatamente poi a nord è come rimanere fermi,
 * idem per est e ovest
 * 
 * Dopo aver scritto il programma, scrivi le classi TestJunit e TestRunner
 * 
 * ******test da fare******
 */

public class OptimisePath {

	public String[] optimise(String[] inputPath) {
		
		if (inputPath == null) return null;
		
		StringBuilder sb = new StringBuilder();
		
		for (String s: inputPath) {
			sb.append(s);
		}
		
		int oldLength = sb.length();
		if (oldLength == 0) return null;
		
		int newLength = 0;
		String path = sb.toString();
		
		do{
			if (path.contains("NS")) {
				path = path.replaceAll("NS", ""); 
			}
			if (path.contains("SN")) {
				path = path.replaceAll("SN", ""); 
			}
			if (path.contains("EW")) {
				path = path.replaceAll("EW", ""); 
			}
			if (path.contains("WE")) {
				path = path.replaceAll("WE", ""); 
			}
			oldLength = newLength;
			newLength = path.length();
			
		} while(newLength != oldLength);
		
		return path.split("");
	}
	
	
//	public static void main (String arg[]) {
//		String[] input = {"E", "S", "N", "E","W", "W", "S"};
//		String[] output = optimise(input);
//		
//		
//		System.out.println(Arrays.toString(input));
//		System.out.println(Arrays.toString(output));
//	}
}
