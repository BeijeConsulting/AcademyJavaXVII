package it.beije.xvii.exercises.ceccarelli;

public class Fabbrica {
	// Una fabbrica alimentare produce una bibita gassata molto apprezzata. 
	//Sfortunatamente, il gas utilizzato per fare le caratteristiche bollicine è molto volatile ed ogni giorno ne evapora un po’.
	//Si vuole un programma che calcoli il numero di giorni utile per l’imbottigliamento della bibita stessa, conoscendo:
		//La quantità di bibita pronta nella cisterna (‘content‘, espresso in ml)
		//la percentuale di gas che evapora in 24 ore (‘evapPerDay‘)
		//la soglia percentuale oltre la quale la bibita non è più apprezzabile (‘threshold‘) più
		//Tutti i numeri sono interi positivi. Il programma restituirà il numero del giorno oltre il quale 
		//la bibita non sarà più vendibile (non più sufficientemente gasata)
	
	private int content;
	private int evapPerDay;
	private int threshold;
	
	public Fabbrica(int content, int evapPerDay, int threshold) {
		// TODO Auto-generated constructor stub
		this.content = content;
		this.evapPerDay = evapPerDay;
		this.threshold = threshold;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
