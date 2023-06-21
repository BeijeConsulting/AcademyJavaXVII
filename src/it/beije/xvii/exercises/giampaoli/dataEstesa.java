package it.beije.xvii.exercises.giampaoli;
import java.time.*;

public class dataEstesa {

public static void main(String args[])   {
		
	LocalDate data =  LocalDate.of(2021, Month.MAY, 13);
	
//	System.out.print(data.getDayOfWeek() + "\n");
//	System.out.print(data.getMonth() + "\n");
//	System.out.print(data.getYear() + "\n");
//	System.out.print(data.getDayOfYear() + "\n");
//	
	System.out.print(data.getDayOfWeek() + " " + data.getDayOfMonth() + " " + data.getMonth() +", day " + data.getDayOfYear() + " of year " + data.getYear() + ", week number " );
		
	}
	
}
