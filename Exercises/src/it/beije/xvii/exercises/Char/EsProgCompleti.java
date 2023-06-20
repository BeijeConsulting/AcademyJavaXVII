package it.beije.xvii.exercises.Char;

public class EsProgCompleti {
	
	public static void main(String[] args) {
//		fabbricaBibita(700,8,30);
		caffeina(6);
	
	}
	public static void fabbricaBibita(int content, int evapPerDay, int threshold) {
        int days = 0;
        while (content > 0) {
            int numEvap = (content * evapPerDay) / 100;
            content -= numEvap;
            double contentPerc = ((double) content / 1000) * 100;
            if (contentPerc <= threshold) {
                System.out.println("La bibita scade dopo : " + days + " giorni");
                break;
            }

            days++;
        }

	}
	public static void caffeina(int n) {
		if(n % 3 == 0 && n % 4 != 0) {
			System.out.print("Java");
			if(n % 2 == 0)
				System.out.print("Script");
		}
			
		else if(n % 3 == 0 && n % 4 == 0) {
			System.out.print("Coffee");
			if(n % 2 == 0)
				System.out.print("Script");
		}
		else 
			System.out.println("match_missed!");
			
	}
}
