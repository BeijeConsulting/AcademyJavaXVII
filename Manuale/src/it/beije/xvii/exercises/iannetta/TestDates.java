package it.beije.xvii.exercises.iannetta;


import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class TestDates {

	public static void main(String[] args) throws ParseException {
		Scanner in = new Scanner (System.in);
		System.out.println("Inserisci data: dd/MM/yyyy");
		String input = in.nextLine();
		in.close();
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(input);
		String dayWeekText = new SimpleDateFormat("EEEE").format(date); 
		String dayOfMonth = new SimpleDateFormat("dd").format(date);
		String monthText = new SimpleDateFormat("MMMM").format(date);
		String dayOfYear = new SimpleDateFormat("D").format(date);
		String year = new SimpleDateFormat("yyyy").format(date);
		String weekOfYear = new SimpleDateFormat("w").format(date);
		
		String output = ("" + dayWeekText.charAt(0)).toUpperCase() + dayWeekText.substring(1)
						+ " " + dayOfMonth + " " + monthText + ", "
						+ "giorno " + dayOfYear + " dell'anno " + year + ", "
						+ "settimana numero " + weekOfYear;
		System.out.println(output);
	}

}
