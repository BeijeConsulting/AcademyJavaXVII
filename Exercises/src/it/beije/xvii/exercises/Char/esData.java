package it.beije.xvii.exercises.Char;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class esData {
	public static void main(String[] args) {
	insDate("13/09/2021");
	}
	public static void insDate(String dateString) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateString, format);
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
		System.out.println(date.getDayOfWeek() + " " + date.getDayOfMonth() + " " + date.getMonth() + "," + "day "  +  date.getDayOfYear() + " of the year " + date.getYear() + " " + "Week number " + weekNumber);
	}
}
