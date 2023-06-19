package it.beije.xvii.exercises.mancuso;

/*
 * Scrivere la funzione caffeina(int n) che prende un numero positivo come argomento e:

    Se il numero è divisibile per 3, stampa “Java”
    Se il numero è divisibile per 3 e per 4, stampa “Coffee”
    Se il numero appartiene ad uno dei due casi precedenti ed è pari, aggiunge “Script” in fondo alla stringa
    Se non appartiene a nessuno dei casi precedenti stampa “match_missed!”
    
    Da come è scritta la consegna sembra che le varie condizioni non siano mutualmente esclusive (a parte l'ultima), quindi
    ho deciso che tratterò la seconda come conseguente in parte alla prima. Ovvero, la seconda non è possibile se non è vera la
    prima quindi Coffee non può essere stampato senza stampare Java.
    
    Potrebbe anche essere interpretato come esclusivo (quindi se è vera la seconda non stampare la prima) ma non è chiaro
    dalla consegna perchè non sono usate parole come "altrimenti" quindi mi permetto di interpretare.
 */

public class Caffeina {
	
	public void caffeina(int n) {
		String s = "";
		if(n%3 == 0) {
			s += "Java ";
			if(n%4 == 0) {
				s += "Coffee ";
			}
			if(n%2 == 0) {
				s += "Script ";
			}
			System.out.println(s);
		}else {
			System.out.println("Match missed!");
		}
	}
	
	public static void main(String[] args) {
		
		Caffeina c = new Caffeina();
		
		c.caffeina(6);
		c.caffeina(9);
		c.caffeina(12);

	}

}
