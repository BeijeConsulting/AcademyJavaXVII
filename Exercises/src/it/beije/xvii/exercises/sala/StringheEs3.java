package it.beije.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere il metodo
1 public int contaLettera(char c, String str)

che conta le occorrenze della lettera c nella stringa str
 */

public class StringheEs3 {

	public static void main(String[] args) {
	Scanner ts = new Scanner(System.in);
	System.out.println("inserisci parola");
	String s = ts.next();
	StringheEs3 s1=new StringheEs3();
	System.out.println(s1.contaLettere('c',s));
	
	
	ts.close();

	}
	
	public int contaLettere(char c, String s) {
		int conto = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='c') {
				conto++;
			}
		}
		return conto;
	}

}
