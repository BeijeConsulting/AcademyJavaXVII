package it.beije.xvii.exercises.ceccarelli;

import java.util.Scanner;

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
	
	public int calcoloGiorni() {
		//int evaporazione = (this.content*this.evapPerDay)/100;
		int gasTotale = 100;
		int count=0;
		int gasRimasto = 0;
		while(gasRimasto > (this.threshold/100)) {
			gasRimasto = gasTotale - (this.evapPerDay/100);
			count +=1;
		}
		return count;
		
	}

	public static void main(String[] args) {
		
		System.out.println("Inserisci la quantità di bibita nella cisterna");
		Scanner scan = new Scanner(System.in);
		int quantità = scan.nextInt();
		System.out.println("Inserisci la percentuale di gas che evapora in 24 ore: ");
		int percentuale = scan.nextInt();
		System.out.println("Inserisci la soglia percentuale: ");
		int soglia = scan.nextInt();
		Fabbrica fabbrica = new Fabbrica(quantità, percentuale, soglia);
		System.out.println(fabbrica.calcoloGiorni());
	}

}
