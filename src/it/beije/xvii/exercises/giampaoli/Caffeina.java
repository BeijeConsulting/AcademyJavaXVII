package it.beije.xvii.exercises.giampaoli;
public class Caffeina
{  
	public static StringBuilder caffeina(int n) {  
		
		StringBuilder ritorno = new StringBuilder("");
		
		if (n % 3 == 0 && n % 4 != 0) {
			ritorno.append("Java");
			if(n % 2 == 0) {
				ritorno.append("Script");
			}
		} else if (n % 3 == 0 && n % 4 == 0){
			ritorno.append("Coffee");
			if(n % 2 == 0) {
				ritorno.append("Script");
			}
		} else {
			System.out.print("missed_match!");
		}
	return ritorno;
	}		
	
	
	public static void main(String args[])   {
		System.out.print(caffeina(24));
	}
}  