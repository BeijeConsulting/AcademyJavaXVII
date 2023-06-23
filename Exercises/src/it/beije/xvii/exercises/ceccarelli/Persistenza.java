package it.beije.xvii.exercises.ceccarelli;

import java.util.ArrayList;

public class Persistenza {
	/*
	 * Scrivere la funzione “persistenza”, che prende un parametro intero positivo e ritorna la sua “persistenza moltiplicativa”, 
	 * che è il numero di volte per cui bisogna moltiplicare le cifre fra loro 
	 * fino ad avere un unico carattere.Per esempio:persistence(39) == 3 // perché 3*9 = 27, 2*7 = 14, 1*4=4
	// e 4 è diuna cifra sola
	 * persistence(999) == 4 // perché 9*9*9 = 729, 7*2*9 = 126,
	// 1*2*6 = 12, e 1*2 = 2
	persistence(4) == 0 // perché 4 è già una cifra singola
	 */

	public Persistenza() {
		// TODO Auto-generated constructor stub
	}
	
	public int persistenzaF(int n) {
		String s = String.valueOf(n);
		ArrayList<Integer> interi = new ArrayList<Integer>();
		for(int i=0;i<s.length();i++) {
			int num = Character.getNumericValue(s.charAt(i));
			interi.add(num);
		}
		
		int totale = 1;
		int numeroVolte =0;
		//String s1="";
		if(interi.size()>1) {
		for(int numero: interi) {
			numeroVolte +=1;
		 totale = totale*numero;
		 //s1 += String.valueOf(n);
		}
		
		while(totale>9) {
			totale = persistenzaF(totale);
			numeroVolte++;
			//System.out.println(n);
		}
		}
	
		return numeroVolte;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persistenza p = new Persistenza();
		int n =(p.persistenzaF(4));
		System.out.println(n);

	}

}
