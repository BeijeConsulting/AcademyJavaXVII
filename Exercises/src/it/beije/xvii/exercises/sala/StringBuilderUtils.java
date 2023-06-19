package it.beije.xvii.exercises.sala;
import java.util.Scanner;

/*
 * StringBuilder append(StringBuilder sb, String str)
StringBuilder insert(StringBuilder sb, int offset, String str)
StringBuilder delete(StringBuilder sb, int start, int end)
StringBuilder deleteCharAt(StringBuilder sb, int index)
StringBuilder reverse(StringBuilder sb)
 */

public class StringBuilderUtils {

	public static void main(String[] args) {
		Scanner ts=new Scanner(System.in);
		System.out.println("inseirsci due stringhe");
		String s = ts.nextLine();
		String s1 = ts.nextLine();
		StringBuilder sb = new StringBuilder(s);
		System.out.println("inseirsci un indice");
		int offset = ts.nextInt();
		int end = ts.nextInt();
		
		//System.out.println("metodo append(StringBuilder sb, String str): "+ StringBuilderUtils.append(sb, s1));
		//System.out.println("metodo insert(StringBuilder sb, int offset, String str): "+ StringBuilderUtils.insert(sb, offset, s1));
		//System.out.println("metodo delete(StringBuilder sb, int start, int end): "+ StringBuilderUtils.delete(sb, offset, end));
		//System.out.println("metodo deleteChar(StringBuilder sb, int index): "+ StringBuilderUtils.deleteChar(sb, offset));
		System.out.println("metodo reverse(StringBuilder sb): "+ StringBuilderUtils.reverse(sb));
		
		ts.close();
	}

	public static StringBuilder append(StringBuilder sb, String str) {
		/*int capacitaSb=sb.length();
		if(capacitaSb<str.length()) {
			sb.ensureCapacity(capacitaSb+sb.length());
		}*/
		
		String nuova=String.valueOf(sb);
		
		nuova=nuova+str;
		StringBuilder sbnuovo = new StringBuilder(nuova);
		return sbnuovo;
	}
	
	
	public static StringBuilder insert(StringBuilder sb, int offset, String str) throws IndexOutOfBoundsException {
		String nuova = String.valueOf(sb);
		String finale="";
		if(offset>=0 && offset<nuova.length()) {
			String inizio="";
			String fine="";
			for(int i=0; i!=offset; i++) {
				inizio=inizio+nuova.charAt(i);
			}
			
			for(int i=offset; i<nuova.length(); i++) {
				fine=fine+nuova.charAt(i);
			}
			
			finale=inizio+str+fine;
		} else {
			throw new IndexOutOfBoundsException("valore non valido per offset");
		}
		return new StringBuilder(finale);
	}
	
	public static StringBuilder delete(StringBuilder sb, int offset, int end) throws IndexOutOfBoundsException{
		String nuova = String.valueOf(sb);
		String finale ="";
		if(offset>=0 && end<=nuova.length() && offset<end) {
			String inizio="";
			String fine="";
			for(int i=0; i<offset; i++) {
				inizio=inizio+nuova.charAt(i);
			}
			
			for(int i=end; i<nuova.length(); i++) {
				fine=fine+nuova.charAt(i);
			}
			
			finale=inizio+fine;
		} else {
			throw new IndexOutOfBoundsException("valore non valido per gli indici");	
		}
		return new StringBuilder(finale);
			
	}
	
	public static StringBuilder deleteChar(StringBuilder sb, int offset) {
		return delete(sb, offset, sb.length());
	}
	
	public static StringBuilder reverse(StringBuilder sb) {
		String nuova=String.valueOf(sb);
		String finale="";
		for(int i=nuova.length()-1; i>=0; i--) {
			finale=finale+nuova.charAt(i);
			//System.out.println(finale);
		}
		return new StringBuilder(finale);
		
	}
}
