package it.beije.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere la funzione caffeina(int n) che prende un numero positivo come argomento e:
Se il numero è divisibile per 3, stampa “Java”
Se il numero è divisibile per 3 e per 4, stampa “Coffee”
Se il numero appartiene ad uno dei due casi precedenti ed è pari, aggiunge “Script” in fondo alla stringa
Se non appartiene a nessuno dei casi precedenti stampa “match_missed!”
 */
public class Caffeina {
	//ma se è pari ed è divisibile per 4, per forza è anche divisibile per 2
	//quindi non stamperà mai coffee e basta

	public String caffeina(int n) {
		if(n%3==0 && n%2!=0) {
			return "Java";
		} else if(n%3==0 && n%4==0 && n%2==0){
			return "Coffee"+" Script";
		} 
		
		if(n%3==0 && n%2==0) {
			return "Java Script";
		}
		
		return "match_missed!";
	}
	public static void main(String[] args) {
		Scanner ts=new Scanner(System.in);
		System.out.println("inserire valore intero");
		int n = ts.nextInt();
		Caffeina c = new Caffeina();
		System.out.println(c.caffeina(n));
		ts.close();
	}

}
