package it.beije.xvii.exercises.iannetta;

public class Drink {
	
	public static int daysLog(int evapPerDay, int threshold) {
		double den = Math.log(1 - 1.0 * evapPerDay / 100);
		double num = Math.log(1.0 * threshold / 100);
		return (int)Math.ceil(num / den);
		}
		
	public static int daysLoop(int evapPerDay, int threshold) {
		double gas = 100;
		int days = 0;
		boolean isSaleable = true;
		
		while (isSaleable) {
			days++;
			gas = gas * (1 - 1.0 * evapPerDay / 100);
			if (threshold >= gas) isSaleable = false;
		}
		return days; 
	}
	
	
	public static void main(String [] args) {
		
		int evapPerDay = 5;
		int threshold = 50;
		
		int days = daysLog(evapPerDay, threshold);
		System.out.println("Log: The drink can be sold up to " + days);
		days = daysLoop(evapPerDay, threshold);
		System.out.println("Loop: The drink can be sold up to " + days);
		
	}

}
