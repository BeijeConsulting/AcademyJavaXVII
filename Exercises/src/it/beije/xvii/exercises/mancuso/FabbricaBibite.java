package it.beije.xvii.exercises.mancuso;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class FabbricaBibite {

	private int content;
	private int evapPerDay;
	private int treshold;
	
	public void setContent(int c) {
		if(c >= 0) {
			content = c;
		}
	}
	public int getContent() {return content;}
	
	public void setEvapPerDay(int e) {
		if(e >= 0) {
			evapPerDay = e;
		}
	}
	public int getEvapPerDay() {return evapPerDay;}
	
	public void setTreshold(int t) {
		if(t >= 0) {
			treshold = t;
		}
	}
	public int getTreshold() {return treshold;}
	
	public FabbricaBibite(int c, int e, int t) {
		setContent(c);
		setEvapPerDay(e);
		setTreshold(t);
	}
	
	public LocalDate calculateExpirationDate() {
		LocalDate today = LocalDate.now();
		
		// se evapora l'1% al giorno e la soglia è il 90% in teoria i giorni sono 10 a prescindere dai ml di contenuto
		
		int days = (100 - treshold)/evapPerDay;
		
		LocalDate expirationDate = today.plusDays(days);
		
		return expirationDate;		
	}
	
	public static void main(String[] args) {
		
		FabbricaBibite f = new FabbricaBibite(1500, 2, 88);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("La bibita scade il giorno: " + f.calculateExpirationDate().format(dtf));
		
	}
	
}
