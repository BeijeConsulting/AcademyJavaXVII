package it.beije.xvii.exercises.ceccarelli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Scanner;

public class Data {
	//Realizzare un programma che, ricevuta in input una data in formato 13/09/2021 
	//stampi le seguenti informazioni:
	//Lunedì 13 Settembre, giorno 256 dell'anno 2021, settimana numero 37
	//in italiano od inglese, va bene comunque
	public Data() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("scrivi data in formato 13/09/2021:");
		DateTimeFormatter formato= DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataS = LocalDate.parse(scan.next(), formato);
	
		System.out.println(dataS.getDayOfWeek().toString() + " " + dataS.getDayOfMonth() + " " +  dataS.getMonth().toString() + " " );
		System.out.println("giorno dell'anno: " + dataS.getDayOfYear() + ", settimana numero: " + dataS.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()));
		scan.close();
	}

}
