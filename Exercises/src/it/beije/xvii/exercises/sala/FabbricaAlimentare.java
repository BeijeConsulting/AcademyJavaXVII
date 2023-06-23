package it.beije.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Una fabbrica alimentare produce una bibita gassata molto apprezzata. Sfortunatamente, il gas utilizzato per fare le 
 * caratteristiche bollicine è molto volatile ed ogni giorno ne evapora un po’.
Si vuole un programma che calcoli il numero di giorni utile per l’imbottigliamento della bibita stessa, conoscendo:
La quantità di bibita pronta nella cisterna (‘content‘, espresso in ml)
la percentuale di gas che evapora in 24 ore (‘evapPerDay‘)
la soglia percentuale oltre la quale la bibita non è più apprezzabile (‘threshold‘) più
Tutti i numeri sono interi positivi. Il programma restituirà il numero del giorno oltre il quale la bibita non 
sarà più vendibile (non più sufficientemente gasata)
 */


public class FabbricaAlimentare {
	int content;
	int evapPerDay;
	int threshold;
	
	public FabbricaAlimentare(int content, int evapPerDay, int threshold){
	setContent(content);
	setEvapPerDay(evapPerDay);
	setThreshold(threshold);
	}
	
	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public int getEvapPerDay() {
		return evapPerDay;
	}

	public void setEvapPerDay(int evapPerDay) {
		this.evapPerDay = evapPerDay;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	
	public int giorniAccettabilitaProp(int percGas, int percLiquido) {
		//se diminuisce la percentuale di gas, aumenta quella di liquido
				//int percGas=30;
				//int percLiquido=70;
				//se la loro diff percentuale scende sotto il 10 non va bene
				int giorni=0;
				double soglia=0;
				//double quantitaEvaporata=0.0;
				
				while(soglia<threshold) {
					percGas=percGas-evapPerDay;
					percLiquido=percLiquido+evapPerDay;
					soglia=percLiquido-percGas;
					giorni++;
					
				}
				
				return giorni;
	}
	
	//RIVEDERE RAGIONAMENTO
	public int giorniAccettabilitaGas(int qGas, int threshold, int evapPerDay) {
		int giorni=0;
		int perRimasta=100-evapPerDay;
		int gasRimasto=qGas;
		while(gasRimasto>threshold) {
			gasRimasto=gasRimasto-((gasRimasto/100)*perRimasta);
			giorni++;
		}
		return giorni;
	}
	
	public static void main(String[] args) {
		Scanner ts=new Scanner(System.in);
		System.out.println("inserire quantità cisterna");
		int capacita=ts.nextInt();
		System.out.println("inserire quantità percentuale di gas in perdita ogni giorno");
		int evapPerDay=ts.nextInt();
		System.out.println("inserire quantità percentuale soglia accettabilità");
		int threshold=ts.nextInt();
		
		System.out.println("inserire quantità percentuale di gas");
		int percGas=ts.nextInt();
		
		System.out.println("inserire quantità percentuale di liquido");
		int perLiq=ts.nextInt();
		
		System.out.println("inserire quantità gas");
		int totGas=ts.nextInt();
		
		FabbricaAlimentare fa = new FabbricaAlimentare(capacita, evapPerDay, threshold);
		
		System.out.println("giorni: "+fa.giorniAccettabilitaProp(percGas, perLiq));

		
		//double content=10000;
		//int evapPerDay= 2;
		//int threshold=60;
		
		
		
		/*while(soglia<10) {
			quantitaEvaporata=(/100)*evapPerDay;
			System.out.println(quantitaEvaporata);
			
			soglia=soglia+quantitaEvaporata;
			System.out.println(soglia);
			content=content-quantitaEvaporata;
			System.out.println(content);
			giorni++;
		}
		*/
		
		
		
	}


}
