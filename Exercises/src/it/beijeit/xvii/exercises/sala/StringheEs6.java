package it.beijeit.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere un metodo che, data una stringa in input, assuma questa come un nome di variabile e stampi 
 * per questa variabile il suo metodo “setter”
 */

public class StringheEs6 {
	public static void main(String [] args) {
		Scanner ts=new Scanner(System.in);
		System.out.println("inserisci stringa");
		String s=ts.nextLine();
		StringheEs6 st = new StringheEs6();
		String finale = st.stampa(s);
		System.out.println(finale);
		ts.close();
	}
	
	
	
	//comporre la parola setter che sarà il nome: setParolacontenutains
	public String stampa(String s) {
		s=s.trim();
		s=s.replaceAll(" ","");
		System.out.println(s);
		String s3 = s.toLowerCase().substring(1);
		System.out.println(s);
		String s1 = s.toUpperCase().substring(0, 1);
		String setter = "set"+s1+s3;
		return setter;
		
		
	}
}
