package it.beijeit.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere un programma Concatena che chiede all’utente di inserire tre singole parole e le ristampa 
 * interponendovi un asterisco. Per esempio, se l’utente inserisce “gatto”, “cane” e “topo” il programma 
 * stamperà “gatto*cane*topo”.
 */
public class StringheEs5 {
	public static void main(String [] args) {
		Scanner ts = new Scanner(System.in);
		System.out.println("inserisci 3 parole");
		String s1= ts.nextLine();
		String s2=ts.nextLine();
		String s3=ts.next();
		
		String finale = s1+"*"+s2+"*"+s3;
		System.out.println(finale);
		
		ts.close();
	}
	
	
}
