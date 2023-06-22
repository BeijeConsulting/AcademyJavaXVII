package it.beije.xvii.exercises.ceccarelli;

public class Caffeina {
	//Scrivere la funzione caffeina(int n) che prende un numero positivo come argomento e:
	//Se il numero è divisibile per 3, stampa “Java”
	//Se il numero è divisibile per 3 e per 4, stampa “Coffee”
	//Se il numero appartiene ad uno dei due casi precedenti ed è pari, aggiunge “Script” in fondo alla stringa
	//Se non appartiene a nessuno dei casi precedenti stampa “match_missed!”

	public Caffeina() {
		// TODO Auto-generated constructor stub
	}
	
	public void caffeina(int n) {
		if(n%3==0 && n%4==0) {
			System.out.print("Java");
			if(n%2==0) {
				System.out.println("Script");
			}
		}else if(n%3==0) {
			System.out.print("Coffee");
			
		}else  {
			System.out.println("match_missed!");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Caffeina c= new Caffeina();
		c.caffeina(12);

	}

}
