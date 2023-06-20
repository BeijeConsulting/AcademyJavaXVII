package dateEx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

//  Realizzare un programma che, ricevuta in input
//  una data in formato 13/09/2021 stampi le seguenti informazioni:
//  Lunedì 13 Settembre, giorno 256 dell'anno 2021, settimana numero 37

public class DateEx {

    public static void date() {

        String inputDate = insertDate();

        while (inputDate == null) {
            inputDate = insertDate();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(inputDate, formatter);

        String dayOfWeek = date.getDayOfWeek().toString();
        int dayOfYear = date.getDayOfYear();
        String monthOfYear = date.getMonth().toString();
        int year = date.getYear();
        int weekNumber = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        int week = date.getDayOfWeek().getValue();
        System.out.println(dayOfWeek + " " + week + " " + monthOfYear + ", day " + dayOfYear + " of year " + year + ", week number " + weekNumber);


    }

    public static String insertDate() {
        System.out.println("Inserisci la data nel formato 'giorno/mese/anno/' ad esempio: 13/09/2021, per visualizzare -> Lunedì 13 Settembre, giorno 256 dell'anno 2021, settimana numero 37 ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
