package it.beije.xvii.exercises.giampaoli;
public class FabbricaAlimentare
{  
	public static void main(String[] args) { 
		
		int content = 1000;
		int evapPerDay = 2;
		int threshold = 4;
		
		int tempEvap = 0;
		int giorni = 0;
		
		for (; ;) {
			tempEvap = tempEvap + evapPerDay;
			if (tempEvap > threshold ) {
				break;				
			} else {
				giorni++;
			}
		}
					
		System.out.print(giorni);
	} 
}  