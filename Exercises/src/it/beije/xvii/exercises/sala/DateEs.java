package it.beije.xvii.exercises.sala;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/*
 * Realizzare un programma che, ricevuta in input una data in formato 13/09/2021 stampi le seguenti informazioni:
Lunedì 13 Settembre, giorno 256 dell'anno 2021, settimana numero 37
in italiano od inglese, va bene comunque
 */

public class DateEs {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner ts=new Scanner(System.in);
		System.out.println("inserisci data");
		String data=ts.next();
		if(data.charAt(2)!='/' && data.charAt(5)!='/'){
			System.out.println("data non valida, inserire /");
			
			//parse a intero e poi controllo che siano in un range
		}
		Date d = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		
		//codice alternativo
		//Format f = new SimpleDateFormat("EEEE");
		//String giorno = f.format(d);
		
		String giornoSettimana = new SimpleDateFormat("EEEE", Locale.ITALIAN).format(d);
		String giorno = new SimpleDateFormat("dd").format(d);
		String mese = new SimpleDateFormat("MMMM", Locale.ITALIAN).format(d);
		//int giornoAnno = LocalDate.now().get(ChronoField.DAY_OF_YEAR);
		String anno = new SimpleDateFormat("yyyy").format(d);
		//int dayOfYear = LocalDate.now().getDayOfYear();
		
		
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(d);
				int n = calendar.get(Calendar.DAY_OF_YEAR);
				int w = calendar.get(Calendar.WEEK_OF_YEAR);
		
		System.out.println(giornoSettimana+" "+giorno+" "+mese+", giorno "+n+ " dell'anno "+anno+", settimana numero "+w);
	}

}
