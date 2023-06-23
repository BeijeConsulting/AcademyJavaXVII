package it.beije.xvii.exercises.ulloa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Scanner;

/*
 * Realizzare un programma che, ricevuta in input una data in formato 
 * 13/09/2021 stampi le seguenti informazioni:
 * Lunedì 13 Settembre, giorno 256 dell'anno 2021, settimana numero 37
 * in italiano od inglese, va bene comunque
 */

public class FormatoData {
	public static void main(String[] args) {
		String data;
		GetterSetter gs = new GetterSetter();
		Scanner tastiera = new Scanner(System.in);
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.print("Inserisci una data in formato dd/MM/yyyy: ");
		data = tastiera.next();
		
		LocalDate d = LocalDate.parse(data,f);
		System.out.println(gs.capitalize(d.getDayOfWeek().toString()) 
				+ " " + d.getDayOfMonth() + " " + gs.capitalize(d.getMonth().toString())
				+ ", day " + d.getDayOfYear() + " of the year " + d.getYear()
				+ ", week number " + d.get(ChronoField.ALIGNED_WEEK_OF_YEAR)
				);

	}
}
