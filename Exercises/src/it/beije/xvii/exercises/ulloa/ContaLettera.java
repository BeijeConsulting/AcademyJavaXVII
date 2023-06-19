package it.beije.xvii.exercises.ulloa;

/*
 * Scrivere il metodo
 * public int contaLettera(char c, String str)
 * che conta le occorrenze della lettera c nella stringa str 
 */

public class ContaLettera {

	public static void main(String[] args) {
		ContaLettera cl = new ContaLettera();
		int contatore;
		contatore = cl.contaLettera('o', "pippO");
		System.out.println(contatore);

	}
	
	public int contaLettera(char c, String str) {
		int contatore = 0;
		int pos = str.indexOf(c);
		while(pos!=-1) {
			contatore++;
			pos = str.indexOf(c,pos+1);
		}
		return contatore;
	}
	
}
